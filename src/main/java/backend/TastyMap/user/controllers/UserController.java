package backend.TastyMap.user.controllers;

import backend.TastyMap.user.dto.JoinRequestDto;
import backend.TastyMap.user.dto.UpdateRequestDto;
import backend.TastyMap.user.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/join")
    public ResponseEntity<Void> join(@RequestBody JoinRequestDto request) {
        log.info("회원가입 요청 ID: {}", request.getUserId());
        userService.join(request);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/update")
    public ResponseEntity<Void> update(@RequestBody UpdateRequestDto request) {
        log.info("사용자 설정 업데이트 요청 ID: {}", request.getUserId());
        userService.update(request);
        return ResponseEntity.noContent().build();
    }
}