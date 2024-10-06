package backend.TastyMap.auth.service;

import backend.TastyMap.auth.dto.AuthTokens;
import backend.TastyMap.auth.dto.LoginRequest;

public interface AuthService {
    AuthTokens login(LoginRequest loginRequest);
    String reissueToken(String refreshToken);
    void logOut(String refreshToken);
}
