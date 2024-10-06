package backend.TastyMap.common.exception;

import backend.TastyMap.common.constants.ErrorCode;
import lombok.Getter;

@Getter
public class RefreshTokenException extends GeneralException {
    public RefreshTokenException(ErrorCode errorCode) {
        super(errorCode);
    }
}