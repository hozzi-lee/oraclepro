/* system
-- 계정 생성( ID: phonedb, PW: phonedb )
CREATE USER phonedb IDENTIFIED BY phonedb;

-- 접속 권한
GRANT resource, connect TO phonedb;
*/

-- 초기화: 삭제 코드
DROP SEQUENCE seq_person_id;
DROP TABLE person;


-- 테이블 생성
CREATE TABLE person (
    person_id  NUMBER(5),
    name       VARCHAR2(30) NOT NULL,
    hp         VARCHAR2(20),
    company    VARCHAR2(20),
    PRIMARY KEY ( person_id )
);

-- PK 시퀀스 생성
CREATE SEQUENCE seq_person_id;

-- 생성된 테이블 확인
SELECT
    person_id,
    name,
    hp,
    company
FROM
    person;

-- 데이터 추가
INSERT INTO
        person
VALUES (
    seq_person_id.NEXTVAL,
    '이효리',
    '010-1111-1111',
    '02-1111-1111'
);

INSERT INTO
        person
VALUES (
    seq_person_id.NEXTVAL,
    '정우성',
    '010-2222-2222',
    '02-2222-2222'
);

INSERT INTO
        person
VALUES (
    seq_person_id.NEXTVAL,
    '유재석',
    '010-3333-3333',
    '02-3333-3333'
);

INSERT INTO
        person
VALUES (
    seq_person_id.NEXTVAL,
    '이정재',
    '010-4444-4444',
    '02-4444-4444'
);

INSERT INTO
        person
VALUES (
    seq_person_id.NEXTVAL,
    '서장훈',
    '010-5555-5555',
    '02-5555-5555'
);

-- 커밋
COMMIT;

-- 데이터 수정
UPDATE
    person
SET
    hp = '010-9999-9999',
    company = '02-9999-9999'
WHERE
    name = '이정재';

-- 데이터 삭제
DELETE FROM
        person
WHERE
    person_id = 5;

-- 테이블 데이터 확인
SELECT
    person_id,
    name,
    hp,
    company
FROM
    person;

-- 커밋
COMMIT;