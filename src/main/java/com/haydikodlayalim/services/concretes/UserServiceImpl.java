package com.haydikodlayalim.services.concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.haydikodlayalim.entities.Post;
import com.haydikodlayalim.entities.User;
import com.haydikodlayalim.repositories.PostsRepository;
import com.haydikodlayalim.repositories.UserRepository;
import com.haydikodlayalim.services.abstracts.UserService;
import com.haydikodlayalim.services.dtos.PostDto;
import com.haydikodlayalim.services.dtos.UserDto;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostsRepository postRepository;


    @Override
    public UserDto save(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setEmail(userDto.getEmail());
        user.setCity(userDto.getCity());
        final User userDb=userRepository.save(user);
        if(userDto.getPostList() == null || userDto.getPostList().isEmpty()){
            userDb.setPostList(new ArrayList<>());
            userRepository.save(userDb);
            userDto.setPostList(new ArrayList<>());
            userDto.setId(userDb.getId());
            return userDto;
        }
        
        List <Post> posts = new ArrayList<>();
        userDto.getPostList().forEach(item ->{
            Post post = new Post();
            post.setTitle(item.getTitle());
            post.setContent(item.getContent());
            post.setUser(userDb);
            posts.add(post);
        });
        postRepository.saveAll(posts);
        userDto.setId(userDb.getId());
        return userDto;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
    @Override

    public List<UserDto> getAll() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>(); 
        users.forEach(item ->{
            UserDto userDto = new UserDto();
            userDto.setId(item.getId());
            userDto.setName(item.getName());
            userDto.setCity(item.getCity());
            userDto.setSurname(item.getSurname());
            userDto.setEmail(item.getEmail());
            List<PostDto> postList = new ArrayList<>();

            item.getPostList().forEach(post ->{
                PostDto postDto = new PostDto();
                postDto.setId(post.getId());
                postDto.setTitle(post.getTitle());
                postDto.setContent(post.getContent());

                postList.add(postDto);
            });
            userDto.setPostList(postList);
            userDtos.add(userDto);
        }
        
        );

        return userDtos;
    }

    public UserDto savePost(PostDto postDto,Long userId) {
        User userdb = userRepository.findById(userId).get();
        //Post saved
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setUser(userdb);
        postRepository.save(post);
        //Hadle dto id
        postDto.setId(post.getId());
        //Add post to user
        userdb.getPostList().add(post);
        final User user=userRepository.save(userdb);
        //Handle dto post list
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setSurname(user.getSurname());
        userDto.setEmail(user.getEmail());
        userDto.setCity(user.getCity());
        userDto.setPostList(new ArrayList<>());
        user.getPostList().forEach(item->{
            userDto.getPostList().add(new PostDto(item.getId(),item.getTitle(),item.getContent()));
        });
        

        return userDto;
    }

        
}
