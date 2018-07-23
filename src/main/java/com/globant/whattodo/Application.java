package com.globant.whattodo;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.globant.whattodo.entities.User;
import com.globant.whattodo.repository.UsersRepository;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner init(UsersRepository usersRepository) {
		return args -> Arrays.asList("jhoeller", "dsyer", "pwebb", "ogierke", "rwinch", "mfisher", "mpollack", "jlong")
				.forEach(username -> {
					User user = usersRepository.save(new User(username, "password"));
					// bookmarkRepository.save(new Bookmark(account,
					// "http://bookmark.com/1/" + username, "A description for
					// bookmark 1"));
					// bookmarkRepository.save(new Bookmark(account,
					// "http://bookmark.com/2/" + username, "A description for
					// bookmark 2"));
				});
	}
}