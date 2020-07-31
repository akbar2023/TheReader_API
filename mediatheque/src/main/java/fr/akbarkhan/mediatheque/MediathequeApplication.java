package fr.akbarkhan.mediatheque;

import fr.akbarkhan.mediatheque.controller.BookController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
@SpringBootApplication
public class MediathequeApplication {
	@Autowired
	private BookController bookController;

	public static void main(String[] args) {
		SpringApplication.run(MediathequeApplication.class, args);
//		BookController bookController = new BookController();
//		bookController.test();
	}



//	@Bean
//	public CommandLineRunner commandLineRunner() {
//		return args -> {
//		};
//	}

}
