package com.example.springsecurity.properties;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * AuthProperties
 *
 * @author 李二帅
 * @since 2023/4/25 11:01
 */
@Data
@ConfigurationProperties(prefix = "les.security")
public class AuthProperties {

    private JWT jwt;

    private List<String> permitStatic;

    private List<String> permitMethod;

    @Data
    public static class JWT {

        private Claims claims = new Claims();
        private String authHeader;
        private String secret;
        private Type type = Type.RANDOM;

        public void setAuthHeader(String authHeader) {
            this.authHeader = authHeader;
        }

        public String getAuthHeader() {
            return authHeader;
        }

        public Claims getClaims() {
            return claims;
        }

        public void setSecret(String secret) {
            this.secret = secret;
        }

        public String getSecret() {
            return secret;
        }

        public void setType(Type type) {
            this.type = type;
        }

        public Type getType() {
            return type;
        }

        public enum Type {
            RANDOM, FOREVER
        }

        @Setter
        @Getter
        public static class Claims {
            private String issuer = "SpringSecurity";
            private String audience = "Web";
            private String subject = "Auth";
            private Long expirationTimeMinutes = 60L;
        }

    }
}