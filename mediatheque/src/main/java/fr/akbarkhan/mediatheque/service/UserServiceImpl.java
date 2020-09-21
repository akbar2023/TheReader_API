package fr.akbarkhan.mediatheque.service;

import fr.akbarkhan.mediatheque.dto.ConnectedUserDto;
import fr.akbarkhan.mediatheque.dto.UserDto;
import fr.akbarkhan.mediatheque.dto.UserRegisterDto;
import fr.akbarkhan.mediatheque.entity.MyUser;
import fr.akbarkhan.mediatheque.entity.Role;
import fr.akbarkhan.mediatheque.repository.RoleRepository;
import fr.akbarkhan.mediatheque.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean saveUser(UserRegisterDto registerDto) {

        Optional<MyUser> userDB = userRepository.findByEmail(registerDto.getEmail());
            if(userDB.isEmpty()) {
                MyUser user = new MyUser();
                user.setFirstName(registerDto.getFirstName());
                user.setLastName(registerDto.getLastName());
                user.setEmail(registerDto.getEmail());
                user.setPassword(passwordEncoder.encode(registerDto.getPassword())); // pwd encoded
                Collection<Role> roles = new ArrayList<Role>();
                Role role = roleRepository.findByRole("USER").orElse(null);
                roles.add(role);
                user.setRoles(roles);
                user.setEnabled(true);
                userRepository.save(user);
                return true;
            } else {
            return false;
        }

    }

    @Override
    public MyUser findByUsername(String username) {
        Optional<MyUser> user = userRepository.findByEmail(username);
        return user.orElse(null);
    }

    @Override
    public ConnectedUserDto findByEmail(String username) {
        Optional<MyUser> user = userRepository.findByEmail(username);
        ConnectedUserDto detailsDto = new ConnectedUserDto();
        if (user.isPresent()) {
            detailsDto.setId(user.get().getId());
            detailsDto.setFirstName(user.get().getFirstName());
            detailsDto.setLastName(user.get().getLastName());
            detailsDto.setEmail(user.get().getEmail());
            return detailsDto;
        } else {
            return null;
        }
    }

    @Override
    public boolean updateUser(UserDto userDto, int userId) {
        MyUser user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setEmail(userDto.getEmail());
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            Collection<Role> roles = new ArrayList<Role>();
            Role role = roleRepository.findByRole(userDto.getRole()).orElse(null);
            roles.add(role);
            user.setRoles(roles);
            userRepository.save(user);
            return true;
        } else {
            return false;
        }

    }
}
