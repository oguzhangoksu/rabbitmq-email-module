package com.notificationSystem.core.utils;

import com.notificationSystem.services.dtos.PostDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPostRequest {
    private Long userId;
    private PostDto postDto;

}