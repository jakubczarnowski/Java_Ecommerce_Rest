package com.example.ecommerce.dto.authorization;

import javax.validation.constraints.NotBlank;

public class TokenRefreshRequest {
    @NotBlank
    String refreshToken;

    public TokenRefreshRequest() {
    }

    public TokenRefreshRequest(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
