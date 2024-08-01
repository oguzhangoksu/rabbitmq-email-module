## RabbitMQ Notification System

## Overview
This project demonstrates a notification system using RabbitMQ. Messages are published to a fanout exchange and routed to queues based on the city. The application sends emails to users based on the received messages.

## Features
- RabbitMQ configuration with fanout exchange and queues
- Message publishing with city information
- Listeners for processing messages in specific queues
- Email sending functionality

According to the run file, ensure that the ${MAIL_USERNAME} and ${MAIL_PASSWORD} variables are set to your own email credentials.
