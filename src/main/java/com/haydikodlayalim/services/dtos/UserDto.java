package com.haydikodlayalim.services.dtos;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDto{
    
    private Long id;
    private String name;
    private String surname;
    private String city;
    private String email;
    private List<PostDto> postList;

    

}