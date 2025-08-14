# ERD
![schedule-user ERD](https://github.com/user-attachments/assets/yd8PShZXnS8q1b1XNwNEE.jpeg)

#

# API 명세서

## Schedule API

| 기능         | 메서드  | URL                        | 기능 설명                             | 요청 예시                                                                 | 응답 예시                                                                 |
|--------------|---------|----------------------------|----------------------------------------|---------------------------------------------------------------------------|---------------------------------------------------------------------------|
| 일정 생성    | POST    | /schedules                 | 새로운 일정을 작성합니다.             | {"userId": Long, "title": "string", "content": "string", "password": "string"} | {"id": Long, "title": "string", "content": "string", "userId": Long, "createdAt": DateTime, "modifiedAt": DateTime} |
| 일정 전체 조회 | GET     | /schedules                 | 등록된 모든 일정을 조회합니다.        | X                                                                         | [{"id": Long, "title": "string", "content": "string", "userId": Long, "createdAt": DateTime, "modifiedAt": DateTime}] |
| 일정 단건 조회 | GET     | /schedules/{scheduleId}    | 선택한 일정 정보를 조회합니다.        | X                                                                         | {"id": Long, "title": "string", "content": "string", "userId": Long, "createdAt": DateTime, "modifiedAt": DateTime} |
| 일정 수정    | PUT     | /schedules/{scheduleId}    | 일정 제목, 내용 등을 수정합니다.      | {"title": "string", "content": "string", "password": "string"}            | {"id": Long, "title": "string", "content": "string", "userId": Long, "createdAt": DateTime, "modifiedAt": DateTime} |
| 일정 삭제    | DELETE  | /schedules/{scheduleId}    | 선택한 일정을 삭제합니다.             | {"password": "string"}                                                    | X                                                                         |


## User API

| 기능       | 메서드  | URL              | 기능 설명                     | 요청 예시                                                                 | 응답 예시                                                                 |
|------------|---------|------------------|-------------------------------|---------------------------------------------------------------------------|---------------------------------------------------------------------------|
| 회원가입   | POST    | /users           | 새로운 유저를 등록합니다.     | {"username": "string", "email": "string", "password": "string"}           | {"id": Long, "username": "string", "email": "string", "createdAt": DateTime, "modifiedAt": DateTime} |
| 유저 조회  | GET     | /users/{userId}  | 특정 유저 정보를 조회합니다. | X                                                                         | {"id": Long, "username": "string", "email": "string", "createdAt": DateTime, "modifiedAt": DateTime} |
| 유저 수정  | PUT     | /users/{userId}  | 유저 정보를 수정합니다.       | {"username": "string", "email": "string", "password": "string"}           | {"id": Long, "username": "string", "email": "string", "createdAt": DateTime, "modifiedAt": DateTime} |
| 유저 삭제  | DELETE  | /users/{userId}  | 유저를 삭제합니다.            | X                                                                         | X                                                                         |


## Auth API

| 기능     | 메서드 | URL      | 기능 설명                           | 요청 예시                                                                 | 응답 예시                          |
|----------|--------|----------|--------------------------------------|---------------------------------------------------------------------------|------------------------------------|
| 회원가입 | POST   | /signup  | 새로운 유저를 등록합니다. (인증 제외) | {"username": "string", "email": "string", "password": "string"}           | "유저 회원가입에 성공했습니다."     |
| 로그인   | POST   | /login   | 유저 로그인 처리 (세션 저장)         | {"username": "string", "password": "string"}                              | "유저 로그인에 성공했습니다."       |
| 로그아웃 | POST   | /logout  | 유저 로그아웃 처리 (세션 삭제)       | X                                                                         | "유저 로그아웃에 성공했습니다."     |
