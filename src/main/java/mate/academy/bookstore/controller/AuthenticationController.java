package mate.academy.bookstore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mate.academy.bookstore.dto.user.UserDto;
import mate.academy.bookstore.dto.user.UserLoginDto;
import mate.academy.bookstore.dto.user.UserLoginRequestDto;
import mate.academy.bookstore.dto.user.UserRegistrationRequestDto;
import mate.academy.bookstore.security.AuthenticationService;
import mate.academy.bookstore.service.UserService;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User management", description = "Endpoints for managing users")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public UserLoginDto login(@RequestBody UserLoginRequestDto requestDto) {
        return authenticationService.authenticate(requestDto);
    }

    @PostMapping("/register")
    @Operation(summary = "Register a new user", description = "Registering new user")
    public UserDto register(@RequestBody @Valid UserRegistrationRequestDto requestDto) {
       return userService.register(requestDto);
    }
}
