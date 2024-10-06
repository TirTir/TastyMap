package backend.TastyMap.user.services;

import backend.TastyMap.common.constants.ErrorCode;
import backend.TastyMap.common.exception.GeneralException;
import backend.TastyMap.user.dto.JoinRequestDto;
import backend.TastyMap.user.dto.UpdateRequestDto;
import backend.TastyMap.user.entity.User;
import backend.TastyMap.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private static final GeometryFactory GEOMETRY_FACTORY = new GeometryFactory(new PrecisionModel(), 4326);

    private final BCryptPasswordEncoder encoder;
    private final UserRepository userRepository;

    @Transactional
    public void join(JoinRequestDto request){
        // 계정 중복 확인
        if (userRepository.existsByUserId(request.getUserId())) {
            throw new GeneralException(ErrorCode.USER_ALREADY_EXIST);
        }

        // 유저 생성
        User newUser = User.builder()
                .userId(request.getUserId())
                .password(encoder.encode(request.getPassword()))
                .isLunchRecommend(false)
                .build();

        userRepository.save(newUser);
    }

    @Transactional
    public void update(UpdateRequestDto request){
        // 계정 확인
        User user = userRepository.findByUserId(request.getUserId())
                .orElseThrow(() -> new GeneralException(ErrorCode.USER_NOT_FOUND));

        // 위치 정보 생성
        Double latitude = request.getLatitude();
        Double longitude = request.getLongitude();
        Point point = (latitude != null && longitude != null) ? createPoint(latitude, longitude) : null;

        user.updateUser(point, request.getIsLunchRecommend());
    }

    public Point createPoint(double latitude, double longitude) {
        // 경도, 위도로 Point 생성
        return GEOMETRY_FACTORY.createPoint(new Coordinate(longitude, latitude));
    }
}
