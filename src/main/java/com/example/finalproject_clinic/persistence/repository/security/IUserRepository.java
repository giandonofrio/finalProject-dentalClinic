package com.example.finalproject_clinic.persistence.repository.security;

import com.example.finalproject_clinic.persistence.entity.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

}
