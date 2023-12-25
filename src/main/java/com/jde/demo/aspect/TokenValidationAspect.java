package com.jde.demo.aspect;

import java.util.Objects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;

import com.jde.demo.model.UserModel;
import com.jde.demo.repository.UserRepository;

@Aspect
@Component
public class TokenValidationAspect {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Environment environment;

    @Pointcut("execution(* com.example.springsecurityexample.controller..*(..))")
    public void controllerMethods() {
    }

    @Before("controllerMethods() && args(token,..)")
    public void beforeControllerMethod(@RequestHeader("Authorization") String token) {
        if (shouldIgnoreValidation()) {
            return; // Ignore validation for specified paths
        }

        if (token != null) {
            UserModel user = userRepository.findByLoginToken(token);
            if (user == null) {
                throw new UnauthorizedException("Invalid token");
            }
        } else {
            throw new UnauthorizedException("Token is missing");
        }
    }

    private boolean shouldIgnoreValidation() {
        String ignorePaths = environment.getProperty("token.validation.ignore-paths");
        if (ignorePaths != null) {
            String[] paths = ignorePaths.split(",");
            for (String path : paths) {
                if (path.endsWith("/**")) {
                    path = path.substring(0, path.length() - 3);
                }
                if (path.equals(getRequestPath())) {
                    return true;
                }
            }
        }
        return false;
    }

    private String getRequestPath() {
        String requestPath = Objects.requireNonNull(
                environment.getProperty("spring.application.name"),
                "spring.application.name is not configured"
        );
        return "/" + requestPath;
    }

    private static class UnauthorizedException extends RuntimeException {
        public UnauthorizedException(String message) {
            super(message);
        }
    }
}