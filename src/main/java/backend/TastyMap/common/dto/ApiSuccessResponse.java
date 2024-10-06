package backend.TastyMap.common.dto;

import backend.TastyMap.common.constants.SuccessCode;
import backend.TastyMap.common.utils.CommonResponse;
import lombok.Getter;

@Getter
public class ApiSuccessResponse<T> extends CommonResponse {

    private final T data;

    private ApiSuccessResponse(String message, T data) {
        super(true, message);
        this.data = data;
    }

    public static<T> ApiSuccessResponse<T> res(SuccessCode status, T data) {
        return new ApiSuccessResponse<>(status.getMessage(), data);
    }
}
