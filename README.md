# pay-token-api

pay token api

* * *

##### Table

* Card Table
    ```
    CREATE TABLE CARD (
        CARD_REF_ID BIGINT NOT NULL AUTO_INCREMENT COMMENT '카드 ID',
        PAYER_CI VARCHAR(128) NOT NULL COMMENT '결제자 CI',
        PAYER_NAME VARCHAR(512) NOT NULL COMMENT '결제자 이름',
        CARD_CODE VARCHAR(512) NOT NULL COMMENT '카드 코드',
        CARD_NUMBER VARCHAR(512) NOT NULL COMMENT '카드 번호',
        CARD_VALID_YEAR VARCHAR(512) NOT NULL COMMENT '카드 유효기간 년도',
        CARD_VALID_MONTH VARCHAR(512) NOT NULL COMMENT '카드 유효기간 월',
        CARD_CVS VARCHAR(512) NOT NULL COMMENT '카드 CVS',
        CREATED_TIME TIMESTAMP NOT NULL COMMENT '등록일자',
        PRIMARY KEY (CARD_REF_ID)
    );
    ```

* Token Table
    ```
    CREATE TABLE TOKEN (
        TOKEN_ID BIGINT NOT NULL AUTO_INCREMENT COMMENT '토큰 ID',
        CARD_REF_ID BIGINT NOT NULL COMMENT '카드 ID',
        TOKEN_TEXT VARCHAR(512) NOT NULL COMMENT '토큰 문자열',
        TOKEN_EXPIRE TIMESTAMP NOT NULL COMMENT '토큰 만료시간',
        TOKEN_STATUS_CODE VARCHAR(16) NOT NULL COMMENT '토큰 상태 코드',
        CREATED_TIME TIMESTAMP NOT NULL COMMENT '등록일자',
        PRIMARY KEY (TOKEN_ID)
    );
    ```

* Token status history Table
    ```
    CREATE TABLE TOKEN_STATUS (
        TOKEN_STATUS_ID BIGINT NOT NULL AUTO_INCREMENT COMMENT '토큰 상태 ID',
        TOKEN_ID BIGINT NOT NULL COMMENT '토큰 ID',
        TOKEN_STATUS_CODE VARCHAR(16) NOT NULL COMMENT '토큰 상태 코드',
        CREATED_TIME TIMESTAMP NOT NULL COMMENT '등록일자',
        PRIMARY KEY (TOKEN_STATUS_ID),
        CONSTRAINT IDX_TOKEN_STATUS_TOKEN_ID FOREIGN KEY(TOKEN_ID) REFERENCES TOKEN(TOKEN_ID)
    );
    ```

* * *

#### API

* Register payment card information
    * Path
        * [post] /card
    * Params
        ```
        {
          "payerCi": "CI numbers",
          "payerName": "card payer name",
          "code": "card company code",
          "number": "card numbers",
          "validYear": "card expire year",
          "validMonth": "card expire month",
          "cvs": "card cvs"
        }
        ```
    * Response
        ```
        {
          "status": 200,
          "message": "success",
          "data": {
            "cardRefId": 1
          }
        }
        ```

* Issue payment token
    * Path
        * [post] /token
    * Params
      ```
      {
        "cardRefId": 1
      }
      ```
    * Response
        ```
        {
          "status": 200,
          "message": "success",
          "data": {
            "token": "token text"
          }
        }
        ```

* Verify payment token
    * Path
        * [post] /token/valid
    * Params
      ```
      {
        "token": "token text"
      }
      ```
    * Response
        ```
        {
          "status": 200,
          "message": "success",
        }
        ```


