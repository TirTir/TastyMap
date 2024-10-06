package backend.TastyMap.auth.filter;

import backend.TastyMap.common.constants.ErrorCode;
import backend.TastyMap.common.dto.ApiErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Spring Security 예외처리
 */
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ErrorCode errorCode = ErrorCode.PLEASE_LOGIN; // 적절한 ErrorCode 사용
        ApiErrorResponse errorResponse = ApiErrorResponse.res(errorCode.getMessage(), errorCode.getStatus());

        response.setStatus(errorCode.getStatus().value());
        response.setContentType("application/json; charset=UTF-8");

        // ApiErrorResponse를 JSON으로 변환하여 응답으로 전달
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}