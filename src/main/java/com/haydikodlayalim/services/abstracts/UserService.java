package com.haydikodlayalim.services.abstracts;

import java.util.List;

import com.haydikodlayalim.core.utils.UserNotSaved;
import com.haydikodlayalim.services.dtos.PostDto;
import com.haydikodlayalim.services.dtos.UserDto;

public interface UserService {
    UserDto save(UserDto userDto) throws UserNotSaved;
    void delete(Long id);
    List<UserDto> getAll();
    UserDto savePost(PostDto postDto, Long userId);


}
