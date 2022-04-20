package com.example.finalproject_clinic.service.impl.security;

import com.example.finalproject_clinic.persistence.entity.security.RolesUser;
import com.example.finalproject_clinic.persistence.entity.security.User;
import com.example.finalproject_clinic.persistence.repository.security.IUserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private final IUserRepository userRepository;

    public DataLoader(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = encoder.encode("password");
        String password2 = encoder.encode("password");

        userRepository.save(new User("4299918", "gianAdmin", password, RolesUser.ADMIN));
        userRepository.save(new User("583843234", "gianUser", password2, RolesUser.USER));

    }
}
