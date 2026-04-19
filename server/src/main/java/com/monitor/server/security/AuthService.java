package com.monitor.server.security;

import com.monitor.shared.model.Role;
import com.monitor.shared.model.User;
import java.util.*;

public class AuthService {
    private final Map<String, User> users = new HashMap<>();

    public AuthService() {
        users.put("admin", new User("1", "admin", "admin123", Role.ADMIN));
        users.put("viewer", new User("2", "viewer", "viewer123", Role.VIEWER));
    }

    public Optional<User> login(String username, String password) {
        return Optional.ofNullable(users.get(username))
            .filter(u -> u.password().equals(password));
    }

    public boolean checkRole(String username, Role required) {
        return Optional.ofNullable(users.get(username))
            .map(u -> u.role() == required || u.role() == Role.ADMIN)
            .orElse(false);
    }
}
