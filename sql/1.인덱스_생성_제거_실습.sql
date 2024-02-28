use market_db;

# member에 어떤 인덱스 생성되있는지 확인
show index from member;

# 테이블에 생성된 인덱스 크기를 확인할 수 있다
# 아래 명령어를 통해서 조회되는 index length는 보조 인덱스 크기인데
# member는 보조 인덱스가 없기 때문에 0으로 표기된다.
show table status like 'member';

# member의 addr 컬럼에 인덱스 생성
create index idx_member_addr on member (addr);
show index from member;
# 생성된 인덱스 조회

# 1에서 index_lenght가 0으로 나오는 이유는 생성된 인덱스를 통해서
# 테이블을 분석/조회 해주지 않았기 때문이다.
show table status like 'member';
# 1
# 위에서 생성된 인덱스를 통해서 테이블을 분석/조회 해주면
analyze table member;
# 2
# index_length가 생성된 인덱스 크기로 조회된다.
show table status like 'member';
# 3

# 블랙핑크, 마마무, 레드벨벳의 인원수가 4이기 때문에 중복이 있어서
# 아래 index 생성 쿼리가 동작하지 않음
create unique index idx_member_mem_number
    on member (mem_number);
# 멤버 이름은 모두 고유하기 때문에 고유 보조 인덱스를 생성할 수 있음
create unique index idx_member_mem_name
    on member (mem_name);
# 우연히 마마무와 이름이 같은 태국의 가수 그룹이 회원가입을 하려고 할 때
# 위에서 생성한 인덱스로 인해서 아래 쿼리는 동작하지 않는다.
# [23000][1062] Duplicate entry '마마무' for key 'member.idx_member_mem_name'
insert into member
values (
        'MOO',
        '마마무',
        2,
        '태국',
        001,
        12341234,
        155, '2020.10.10'
       );
