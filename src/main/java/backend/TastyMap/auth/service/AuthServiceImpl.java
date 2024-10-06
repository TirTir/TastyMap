package backend.TastyMap.auth.service;

import backend.TastyMap.auth.dto.AuthTokens;
import backend.TastyMap.auth.dto.LoginRequest;
import backend.TastyMap.auth.token.AccessToken;
import backend.TastyMap.auth.token.RefreshToken;
import backend.TastyMap.common.constants.ErrorCode;
import backend.TastyMap.common.exception.GeneralException;
import backend.TastyMap.common.exception.RefreshTokenException;
import backend.TastyMap.user.entity.User;
import backend.TastyMap.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.NoSuchElementException;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final TokenService tokenService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public AuthTokens login(LoginRequest loginRequest) {
        User user = userRepository.findByUserId(loginRequest.getUsername())
                .orElseThrow(() -> new GeneralException(ErrorCode.WRONG_USERNAME));

        // 비밀번호 일치 여부 확인
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new GeneralException(ErrorCode.WRONG_PASSWORD);
        }

        // Security Context 저장
        tokenService.setAuthentication(user.getId(), user.getUserId());

        // Access Token, Refresh Token 생성
        AccessToken accessToken = tokenService.generateAccessToken(user.getId(), user.getUserId());
        RefreshToken refreshToken = tokenService.generateRefreshToken(user.getId(), user.getUserId());

        return new AuthTokens(refreshToken.getToken(), accessToken.getToken(), user.getUserId());
    }

    @Override
    @Transactional(readOnly = true)
    public String reissueToken(String refreshToken) {
        return tokenService.refreshAccessToken(refreshToken).getToken();
    }

    @Override
    @Transactional
    public void logOut(String refreshToken) {
        if(!StringUtils.hasText(refreshToken)) {
            throw new RefreshTokenException(ErrorCode.NO_REFRESH_TOKEN);
        }

        tokenService.deleteRefreshToken(refreshToken);
    }
}
