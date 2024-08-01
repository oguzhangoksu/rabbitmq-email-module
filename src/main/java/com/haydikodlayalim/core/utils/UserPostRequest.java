package com.haydikodlayalim.core.utils;

import com.haydikodlayalim.services.dtos.PostDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPostRequest {
    private Long userId;
    private PostDto postDto;

}