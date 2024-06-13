
package com.QuanLyChungCu_v2.QuanLyChungCu.configs;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.QuanLyChungCu_v2.QuanLyChungCu.models.User;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class JwtTokenUtil {
    @Autowired
    private Environment env;

    public String generateToken(User user) {
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 50 * 60 * 1000))
                .sign(Algorithm.HMAC256(env.getProperty("secret.key").getBytes()));
    }
}
