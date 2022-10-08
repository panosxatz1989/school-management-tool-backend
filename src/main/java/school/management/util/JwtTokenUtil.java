package school.management.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;
import school.management.entity.Role;
import school.management.entity.User;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtTokenUtil {

    private final String secret = "cCBhb9db8D9d98dBKBd98DfD2";
    private final String issuer = "school.management";

    public String generateAccessToken(User user) {
        Algorithm alg = Algorithm.HMAC512(secret.getBytes());
        List<String> authorities = user.getRoles().stream().map(Role::getName).collect(Collectors.toList());

        return JWT.create()
                .withSubject(user.getUsername())
                .withIssuer(issuer)
                .withClaim("roles", authorities)
                .withExpiresAt(new Date(System.currentTimeMillis() + 5 * 60 * 1000))
                .sign(alg);
    }

    public String generateRefreshToken(User user) {
        Algorithm alg = Algorithm.HMAC512(secret.getBytes());
        return JWT.create()
                .withSubject(user.getUsername())
                .withIssuer(issuer)
                .withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
                .sign(alg);
    }

    public boolean validate(String token) {
        Long expiresAt = JWT.decode(token).getExpiresAt().getTime();
        Calendar calendar = Calendar.getInstance();
        return expiresAt >= calendar.getTime().getTime();
    }

    public String getUsername(String token) {
        return JWT.decode(token).getSubject();
    }
}
