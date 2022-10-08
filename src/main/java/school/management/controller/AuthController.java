package school.management.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.management.dto.request.LoginRequest;
import school.management.dto.request.RegisterRequest;
import school.management.service.UserService;
import school.management.util.JwtTokenUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Validated @RequestBody RegisterRequest req) {
        return ResponseEntity.ok().body(userService.createUser(req));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Validated @RequestBody LoginRequest req) {
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword());
        Authentication auth = authenticationManager.authenticate(token);
        User userDetails = (User) auth.getPrincipal();
        school.management.entity.User user = userService.getUserByUsername(userDetails.getUsername());
        String accessToken = jwtTokenUtil.generateAccessToken(user);
        String refreshToken = jwtTokenUtil.generateRefreshToken(user);
        Map<String, String> tokens = new HashMap();
        tokens.put("access_token", accessToken);
        tokens.put("refresh_token", refreshToken);

        return ResponseEntity.ok().body(tokens);
    }

    @PostMapping("/refresh")
    public ResponseEntity refresh(HttpServletRequest request) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String refreshToken = authHeader.substring("Bearer ".length());
            if (jwtTokenUtil.validate(refreshToken)) {
                User userDetails = (User) userService.loadUserByUsername(jwtTokenUtil.getUsername(refreshToken));
                school.management.entity.User user = userService.getUserByUsername(userDetails.getUsername());

                String accessToken = jwtTokenUtil.generateAccessToken(user);
                Map<String, String> tokens = new HashMap();
                tokens.put("access_token", accessToken);
                return ResponseEntity.ok().body(tokens);
            }
        }

        return ResponseEntity.badRequest().body(null);
    }
}
