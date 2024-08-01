package com.notificationSystem.services.abstracts;

import java.util.List;

import com.notificationSystem.core.utils.UserNotSaved;
import com.notificationSystem.services.dtos.PostDto;
import com.notificationSystem.services.dtos.UserDto;

public interface UserService {
    UserDto save(UserDto userDto) throws UserNotSaved;
    void delete(Long id);
    List<UserDto> getAll();
    UserDto savePost(PostDto postDto, Long userId);


}
