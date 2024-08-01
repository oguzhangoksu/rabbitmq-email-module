package com.notificationSystem.controller;

import org.springframework.web.bind.annotation.RestController;

import com.notificationSystem.core.utils.UserPostRequest;
import com.notificationSystem.services.abstracts.MessageService;
import com.notificationSystem.services.abstracts.UserService;
import com.notificationSystem.services.dtos.UserDto;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    MessageService messageService;


    @GetMapping("/alluser")
    public ResponseEntity<List<UserDto>> getall() {
        List<UserDto> userDtos=userService.getAll();

        return ResponseEntity.ok(userDtos);
    }
    @PostMapping("/save")
    public ResponseEntity<UserDto> save(@Valid @RequestBody UserDto userDto) {
        UserDto savedUserDto =userService.save(userDto);
        
        return ResponseEntity.ok(savedUserDto);
    }
    @PostMapping("/savePost")
    public ResponseEntity<UserDto> post(@RequestBody  UserPostRequest userPostRequest) {
        
        
        UserDto userDto =userService.savePost(userPostRequest.getPostDto(), userPostRequest.getUserId());
        messageService.publishMessage(userDto.getEmail(), userPostRequest.getPostDto().getContent(), userDto.getCity());
       
        return ResponseEntity.ok(userDto);
    }
    
    
}
