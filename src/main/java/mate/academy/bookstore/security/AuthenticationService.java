package mate.academy.bookstore.security;

import lombok.RequiredArgsConstructor;
import mate.academy.bookstore.dto.user.UserLoginDto;
import mate.academy.bookstore.dto.user.UserLoginRequestDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthenticationService {
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    public UserLoginDto authenticate(UserLoginRequestDto requestDto) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(requestDto.email(), requestDto.password()));

        String token = jwtUtil.generateToken(authentication.getName());
        return new UserLoginDto(token);
    }
}
