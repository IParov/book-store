package mate.academy.bookstore.service.impl;

import lombok.RequiredArgsConstructor;
import mate.academy.bookstore.dto.user.UserDto;
import mate.academy.bookstore.dto.user.UserRegistrationRequestDto;
import mate.academy.bookstore.exception.RegistrationException;
import mate.academy.bookstore.mapper.UserMapper;
import mate.academy.bookstore.model.Role;
import mate.academy.bookstore.model.RoleName;
import mate.academy.bookstore.model.User;
import mate.academy.bookstore.repository.role.RoleRepository;
import mate.academy.bookstore.repository.user.UserRepository;
import mate.academy.bookstore.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserDto register(UserRegistrationRequestDto requestDto) throws RegistrationException {
        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new RegistrationException("Enable to complete registration");
        }
        User user = new User();
        user.setFirstName(requestDto.getFirstName());
        user.setLastName(requestDto.getLastName());
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        user.setEmail(requestDto.getEmail());
        user.setShippingAddress(requestDto.getShippingAddress());
        user.setRoles(Set.of(roleRepository.getReferenceById(2L)));
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }
}
