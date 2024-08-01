package com.haydikodlayalim.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.haydikodlayalim.entities.Post;


public interface PostsRepository extends JpaRepository<Post,Long>{
    
    
}
