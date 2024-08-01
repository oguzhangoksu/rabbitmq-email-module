package com.notificationSystem.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.notificationSystem.entities.Post;


public interface PostsRepository extends JpaRepository<Post,Long>{
    
    
}
