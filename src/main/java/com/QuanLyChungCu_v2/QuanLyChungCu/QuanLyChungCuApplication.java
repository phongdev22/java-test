package com.QuanLyChungCu_v2.QuanLyChungCu;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.UserEntity;
import com.QuanLyChungCu_v2.QuanLyChungCu.repositories.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
@SpringBootApplication
public class QuanLyChungCuApplication implements CommandLineRunner {

	@Autowired
	private UserEntityRepository userRepository;

	@Autowired
    PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(QuanLyChungCuApplication.class, args);
	}

	@Override
    public void run(String... args) throws Exception {
		if(userRepository.findByUsername("admin").isEmpty()){
			UserEntity admin =
					new UserEntity("admin", passwordEncoder.encode("admin"), "ADMIN");
            userRepository.save(admin);
        }
	}
}
