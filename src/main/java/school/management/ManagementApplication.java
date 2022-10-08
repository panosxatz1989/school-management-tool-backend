package school.management;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import school.management.entity.Role;
import school.management.entity.User;
import school.management.repository.RoleRepository;
import school.management.repository.UserRepository;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class ManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagementApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CommandLineRunner populate(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder encoder) {
        return args -> {
            Role adminRole = Role.builder().name("admin").build();
            Role userRole = Role.builder().name("user").build();

            roleRepository.saveAll(List.of(adminRole, userRole));

            User user = User.builder()
                    .username("panos1989")
                    .password(encoder.encode("str@1989"))
                    .name("Panos")
                    .email("example@gmail.com")
                    .roles(List.of(adminRole, userRole))
                    .lastName("Chatz").build();

            userRepository.save(user);
        };
    }
}
