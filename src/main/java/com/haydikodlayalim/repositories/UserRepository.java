package com.haydikodlayalim.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.haydikodlayalim.entities.User;


public interface UserRepository extends JpaRepository<User, Long> {
    
}
