use market_db;

# Full Table Scan
select mem_id, mem_name, addr from member;
select mem_id, mem_name, addr from member
    where member.mem_name = '에이핑크';

create index idx_member_mem_number on member(mem_number);
analyze table member;
select mem_name, mem_number from member where mem_number >= 7;

show index from member;
