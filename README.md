# YaMukJa

### 프로젝트 소개
위치 기반 맛집 추천 서비스로, 사용자의 현재 위치 또는 지정한 지역을 기준으로 맛집을 추천하는 기능을 제공합니다. 데이터 파이프라인을 통해 공공 데이터 API를 사용해 맛집 정보를 수집하고 전처리하며, 캐싱을 활용해 대규모 트래픽에 대응합니다. RESTful API와 스케줄러를 이용해 자동으로 데이터를 갱신하며, 유저가 원하는 조건으로 맛집을 검색할 수 있는 기능을 구현했습니다.

<br>

## 💻 개발환경
- **Version** : Java 17
- **IDE** : IntelliJ
- **Framework** : SpringBoot 3.3.3
- **ORM** : JPA

<br>
<br>

## 👉Quick Start

### 1. Git Clone
```shell
https://github.com/TirTir/TastyMap.git
```
   
### 2. application.properties
`application.properties` 파일을 설정해주세요.

### 3. Run Application

<br>

## 📌 프로젝트 구조
- API 공통 응답 형식
  - 오류 및 공통 응답을 표준화하여 API 응답을 체계적으로 관리하였습니다.
  - 성공 및 에러 코드와 메시지를 정의하여, 클라이언트 측에서 에러 핸들링을 처리할 수 있도록 하였습니다.
  
   <br>
   
  ```json
  {
      "success": "true",
      "message": "회원가입 성공",
      "data": {
           "accessToken":
           "refreshToken":
       }
  }
  ```

  ```json
  {
    "success": false,
    "message": "해당하는 사용자를 찾을 수 없습니다.",
    "status": "NOT_FOUND",
  }
  ```
 
- 구현 과정
  - 엔티티 설계
  - DTO 생성
  - service / repository 생성
  - controller 생성

<br>
<br>

## 📌 주요 기능

**데이터 파이프라인**

- **외부 API를 통한 데이터 수집**
    - `FeignClient`를 사용하여 외부 API를 호출하였습니다.
- **데이터 전처리**
    - `FAIL_ON_UNKNOWN_PROPERTIES` 비활성화 - JSON 데이터에 자바 객체와 매핑되지 않은 필드가 있을 경우, 오류를 발생시키지 않도록 해주었습니다.
    - `ACCEPT_EMPTY_STRING_AS_NULL_OBJECT` 활성화 - JSON 데이터 내 빈 문자열을 `null` 값으로 처리해주었습니다.
- **데이터 수집**
    - 중복 데이터를 처리하기 위해 유일 키(`사업장명 + 도로명주소`)를 설정하였습니다.

**GIS 데이터 처리**

- 거리 계산 및 필터링
    - 사용자의 위치에 따른 레스토랑 필터링

 ## 🖋 API 명세서
* `Postman`

<br>

## 📖 Commit Message Convention
- Feat : 기능 추가
- Add : 코드 추가 ( 어떠한 기능 내에 기능을 더 추가할 때 )
- Modify : 코드 수정 ( 버그 등 수정하는 모든 과정들 )
- Delete : 코드 삭제

<br>
<br>
