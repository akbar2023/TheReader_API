package fr.akbarkhan.mediatheque;

import fr.akbarkhan.mediatheque.controller.BookController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
