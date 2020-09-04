package fr.akbarkhan.mediatheque;

import fr.akbarkhan.mediatheque.entity.MyUser;
import fr.akbarkhan.mediatheque.entity.Role;
import fr.akbarkhan.mediatheque.repository.RoleRepository;
import fr.akbarkhan.mediatheque.repository.UserRepository;
import fr.akbarkhan.mediatheque.service.UserService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;

@SpringBootApplication
//@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
public class MediathequeApplication {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(MediathequeApplication.class, args);
	}

//	@Bean
//	@Transactional
//	CommandLineRunner start(RoleRepository roleRepository, UserRepository userRepository) {
//		return args -> {
//					roleRepository.save(new Role("ADMIN"));
//					roleRepository.save(new Role("USER"));
//			MyUser user = new MyUser();
//			user.setFirstName("Laurent");
//			user.setLastName("DOE");
//			user.setEmail("laurent@email.com");
//			user.setPassword(passwordEncoder.encode("password"));
//			Collection<Role> roles =  new ArrayList<Role>() ;
//			roles.add(roleRepository.findByRole("USER"));
//			user.setRoles(roles);
//					userRepository.save(user);
//		};
//	}

}



