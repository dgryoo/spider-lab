# 우리집은 도서관 과제

## 프로젝트

***

### 사용 기술

- Java 17
- Spring boot 3.1.10
- Gradle Wrapper 8.6
- Mysql 8

## API 스펙

***

### 회원가입
- Request
    - Method : GET
    - URL : localhost:8080/api/members/sign-in
    - Body
        - name : String ( 유저 이름 )
        - email : String ( 유저 이메일 )
        - password : String ( 유저 패스워드 )
        - phoneNumber : String ( 유저 핸드폰번호 )
- Response
    - Body
        - id : String ( 유저 id )
        - name : String ( 유저 이름 )
        - email : String ( 유저 이메일 )
        - phoneNumber : String ( 유저 핸드폰번호 )

### 도서 위탁
- Request
    - Method : POST
    - URL : localhost:8080/api/book-rental/register
    - Header : X-User-Id ( 유저 id )
    - Body
        - isbn : String ( 책 isbn )
        - bookName : String ( 책 이름 )
        - price : Number ( 대여 가격 )
- Response
    - Body : -
  
### 도서 조회
- Request
    - Method : POST
    - URL : localhost:8080/api/book-rental
    - QueryString :
      - page
        - 1 (default)
      - filterBy
        - RENTAL_COUNT ( 대여 많은순) ( default )
        - PRICE ( 가격 오름차순 )
        - RECENT_REGISTERED ( 최근 등록일순 )
- Response
    - Body (Array)
      - id : number ( 대여정보 식별자)
      - bookName : String ( 책 이름 )
      - price : Number ( 대여 가격 )
      - consignorName : String ( 위탁자 이름 )

### 도서 대여
- Request
    - Method : POST
    - URL : localhost:8080/api/book-rental/rent
    - Header : X-User-Id ( 유저 id )
    - Body
        - ids : NumberArray ( 대여 정보 식별자 배열 )
- Response
    - Body : -
      
## 애플리케이션 실행 절차
***
### 실행 전
- Java
  - 17이상 버전의 JDK가 있어야합니다.
- Mysql
  - 8이상의 버전이 필요합니다.
  - spider라는 이름의 데이터 베이스가 생성되어 있어야 합니다.
  - table은 자동으로 생성되도록 했습니다.
  - Mysql의 유저에 맞게 application.yml의 username, password 값 변경이 필요합니다.
  
### 도서 대여 서비스
1. 회원가입
   - 원활한 진행을 위해 최소 2명의 유저 회원가입이 필요합니다.
   - API 스펙에 맞게 요청하여 회원가입을 진행합니다.
   - 회원 가입에서 받은 유저 id가 다른 API의 Header로 사용됩니다.
2. 도서 위탁
    - 페이징, 정렬 확인을 위해 21개 이상의 도서 등록이 필요합니다. 
    - 회원가입시 받은 유저 id로 API 스펙에 맞게 요청합니다.
3. 도서 조회
    - 페이징 및 필터를 적용 할 수 있습니다.
    - 유저 id는 필요하지 않습니다.
    - 조회된 도서정보의 id값이 도서 대여에 사용됩니다.
4. 도서 대여
    - 조회된 도서의 id들을 사용하여 API 스펙에 맞게 요청합니다.
5. 도서 반납
    - 도서반납은 매초 자동으로 반납되며 대여된지 10초이상 지난 대여에 대해 동작합니다. 
