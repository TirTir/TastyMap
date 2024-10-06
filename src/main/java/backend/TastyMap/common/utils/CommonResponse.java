package backend.TastyMap.common.utils;

import backend.TastyMap.common.constants.SuccessCode;
import lombok.*;

@Data
public class CommonResponse<T> {
    private final Boolean success;
    private final String message;

    protected CommonResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public static CommonResponse res(Boolean success, SuccessCode status) {
        return new CommonResponse(success, status.getMessage());
    }
}
