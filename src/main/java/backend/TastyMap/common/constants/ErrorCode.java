package backend.TastyMap.common.constants;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorCode {
    WRONG_USERNAME(HttpStatus.UNAUTHORIZED, "아이디를 확인해주세요"),
    WRONG_PASSWORD(HttpStatus.UNAUTHORIZED, "비밀번호를 확인해주세요"),
    NO_REFRESH_TOKEN(HttpStatus.BAD_REQUEST, "리프레쉬 토큰이 포함되지 않았습니다"),
    REFRESH_TOKEN_NOT_FOUND(HttpStatus.NOT_FOUND, "저장된 리프레쉬 토큰이 없습니다"),
    ACCESS_TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "액세스 토큰이 만료되었습니다. 재발급해주세요"),
    INVALID_ACCESS_TOKEN(HttpStatus.UNAUTHORIZED, "유효하지 않은 액세스 토큰입니다. 다시 로그인해주세요."),
    EXPIRED_JWT_TOKEN(HttpStatus.UNAUTHORIZED, "만료된 JWT 토큰입니다."),
    UNEXPECTED_ERROR_OCCUR(HttpStatus.INTERNAL_SERVER_ERROR, "예기치 못한 오류가 발생했습니다. 잠시 뒤 다시 시도해주세요"),
    PLEASE_LOGIN(HttpStatus.UNAUTHORIZED, "로그인이 필요한 엔드포인트입니다."),
    REFRESH_TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "리프레쉬 토큰이 만료되었습니다. 다시 로그인해주세요."),

    USER_ALREADY_EXIST(HttpStatus.CONFLICT, "이미 존재하는 계정입니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당하는 사용자를 찾을 수 없습니다."),

    ACCESS_DENIED(HttpStatus.FORBIDDEN, "접근 권한이 없습니다."),
    FORBIDDEN_RESOURCE(HttpStatus.FORBIDDEN, "이 리소스에 접근할 권한이 없습니다."),

    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "입력 값이 유효하지 않습니다."),
    MISSING_REQUIRED_FIELD(HttpStatus.BAD_REQUEST, "필수 입력 필드가 누락되었습니다."),

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러입니다. 관리자에게 문의하세요.");

    private final HttpStatus status;
    private final String message;
}
