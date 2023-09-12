package mate.academy.bookstore.service;

import mate.academy.bookstore.dto.user.UserDto;
import mate.academy.bookstore.dto.user.UserRegistrationRequestDto;
import mate.academy.bookstore.exception.RegistrationException;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    UserDto register(UserRegistrationRequestDto requestDto) throws RegistrationException;
}
