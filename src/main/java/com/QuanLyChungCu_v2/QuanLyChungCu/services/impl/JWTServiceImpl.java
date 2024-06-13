
package com.QuanLyChungCu_v2.QuanLyChungCu.services.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.QuanLyChungCu_v2.QuanLyChungCu.models.User;
import com.QuanLyChungCu_v2.QuanLyChungCu.services.JWTService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:application.properties")
public class JWTServiceImpl implements JWTService {

    @Autowired
    private Environment env;

    @Override
    public String generateToken(User user) {
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 50 * 60 * 1000))
                .sign(Algorithm.HMAC256(env.getProperty("secret.key").getBytes()));
    }

}
