package sapoCasaPrincesas.registro_login.config;

import org.springframework.stereotype.Component;

@Component
public class AdminKeyValidator {

    private static final String ADMIN_KEY = "%spcAdmin#1804";

    public boolean isValid(String key) {
        return ADMIN_KEY.equals(key);
    }
}

