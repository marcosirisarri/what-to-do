package com.globant.whattodo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	// @Bean
	// CommandLineRunner init(UsersRepository usersRepository) {
	// return args -> Arrays.asList("jhoeller", "dsyer", "pwebb", "ogierke",
	// "rwinch", "mfisher", "mpollack", "jlong")
	// .forEach(username -> {
	// User user = usersRepository.save(new User(username, "password"));
	// });
	// }
}