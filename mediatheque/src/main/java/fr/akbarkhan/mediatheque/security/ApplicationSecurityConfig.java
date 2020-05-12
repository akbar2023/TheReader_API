package fr.akbarkhan.mediatheque.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static fr.akbarkhan.mediatheque.security.ApplicationUserPermission.BOOK_WRITE;
import static fr.akbarkhan.mediatheque.security.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
                .antMatchers("/book/**").hasRole(USER.name())
                .antMatchers(HttpMethod.DELETE,"/management/book/**").hasAuthority(BOOK_WRITE.getPermission())
                .antMatchers(HttpMethod.POST,"/management/book/**").hasAuthority(BOOK_WRITE.getPermission())
                .antMatchers(HttpMethod.GET, "/management/book/**").hasAnyRole(ADMIN.name(), ADMINTRAINEE.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails akbar = User.builder()
                .username("akbar")
                .password(passwordEncoder.encode("Mybooks20"))
//                .roles(ADMIN.name()) // ROLE_USER
                .authorities(ADMIN.getGrantedAuthorities())
                .build();

        UserDetails jhon = User.builder()
                .username("jhon")
                .password(passwordEncoder.encode("jojo123"))
//                .roles(USER.name())
                .authorities(USER.getGrantedAuthorities())
                .build();

        UserDetails barry = User.builder()
                .username("barry")
                .password(passwordEncoder.encode("barry123"))
//                .roles(ADMINTRAINEE.name())
                .authorities(ADMINTRAINEE.getGrantedAuthorities())
                .build();

        return new InMemoryUserDetailsManager(
                akbar,
                jhon,
                barry
        );
    }

}
