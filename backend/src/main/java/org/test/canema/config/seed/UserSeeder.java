package org.test.canema.config.seed;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.test.canema.entity.Role;
import org.test.canema.entity.User;
import org.test.canema.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class UserSeeder {

    private final UserRepository userRepository;

    public void seed(){
        User user = new User();
        user.setEmail("canaran26@gmail.com");
        user.setPassword("123456");
        user.setRole(Role.ROLE_ADMIN);

        User user2 = new User();
        user2.setRole(Role.ROLE_CUSTOMER);
        user2.setEmail("canaran@gmail.com");
        user2.setPassword("123456");

        userRepository.save(user);
        userRepository.save(user2);

    }

}
