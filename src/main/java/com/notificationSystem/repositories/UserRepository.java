package com.notificationSystem.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.notificationSystem.entities.User;


public interface UserRepository extends JpaRepository<User, Long> {
    
}
