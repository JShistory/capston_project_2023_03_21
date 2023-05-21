package com.example.dtxeyelogin2.repository;

import com.example.dtxeyelogin2.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * JpaRepository를 확장함으로써, UserRepository는 User 엔티티에 대한 CRUD(Create, Read, Update, Delete) 기능을 제공합니다.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}