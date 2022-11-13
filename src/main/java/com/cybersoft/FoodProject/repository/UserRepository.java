package com.cybersoft.FoodProject.repository;

import com.cybersoft.FoodProject.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    public List<UserEntity> findByEmailAndPassword(String email, String password);
}
