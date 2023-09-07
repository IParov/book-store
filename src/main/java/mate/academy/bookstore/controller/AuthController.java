package mate.academy.bookstore.controller;

import jakarta.validation.Valid;
import mate.academy.bookstore.dto.user.UserRegistrationRequestDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/register")
    public void register(@RequestBody @Valid UserRegistrationRequestDto requestDto) {

    }
}
