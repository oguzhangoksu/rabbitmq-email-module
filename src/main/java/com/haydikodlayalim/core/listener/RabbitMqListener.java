package com.haydikodlayalim.core.listener;

import java.util.List;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.haydikodlayalim.core.configurations.RabbitMqConfig;
import com.haydikodlayalim.entities.User;
import com.haydikodlayalim.repositories.UserRepository;
import com.haydikodlayalim.services.abstracts.SendEmailService;

@Component
public class RabbitMqListener {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SendEmailService sendEmailService;

    @RabbitListener(queues = RabbitMqConfig.QUEUE_BURSA)
    public void listenerBursa(Message message){
        try{
            List<String> parsedMessage=parseMessage(message);
            String publisherEmail = parsedMessage.get(0);
            String receivedMessage = parsedMessage.get(1);
            String city = parsedMessage.get(2);
            System.out.println("Received message in Bursa : " + receivedMessage);
            if (city.equals("Bursa")) {
                sendMessage(receivedMessage, publisherEmail,city );
            }
            else{
                System.out.println("Message is not for Bursa");
            }

        }
        catch (Exception e) {
            System.err.println("Error processing message in Bursa: " + e.getMessage());
            e.printStackTrace();
        }

    }

    @RabbitListener(queues = RabbitMqConfig.QUEUE_ISTANBUL)
    public void listenerIstanbul(Message message){
        try {
            List<String> parsedMessage=parseMessage(message);
            String publisherEmail = parsedMessage.get(0);
            String receivedMessage = parsedMessage.get(1);
            String city = parsedMessage.get(2);
            
            System.out.println("Received message in İstanbul : " + receivedMessage);
            if (city.equals("İstanbul")){

                sendMessage(receivedMessage, publisherEmail,city );
            }
            else{
                System.out.println("Message is not for İstanbul");
            }

        }
        catch (Exception e) {
            System.err.println("Error processing message in Istanbul: " + e.getMessage());
            e.printStackTrace();
        }
     
    }

    public void sendMessage(String message,String publisherEmail,String city){
        try{
            List<User> users = userRepository.findAll();
            for(User user : users){
                if( !user.getEmail().equals(publisherEmail) && user.getCity().equals(city)){
                    sendEmailService.sendEmail(user.getEmail(), "Notification from " + city, message);
                    System.out.println("Sent message to " + user.getEmail() + " in " + city);
                }
            }
        }catch (Exception e) {
            System.err.println("Error sending message: " + e.getMessage());
            e.printStackTrace();
        }

    }
    public List<String> parseMessage(Message message){
        String publisherEmail = (String) message.getMessageProperties().getHeaders().get("publisherEmail");
        String receivedMessage = new String(message.getBody());
        String city = (String) message.getMessageProperties().getHeaders().get("city");
        List<String> parsedMessage = List.of(publisherEmail, receivedMessage, city);
        return  parsedMessage;
    }
}
