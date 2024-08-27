/* 외래키 관계연결과, 확인용 일반 sql*/
-- 회원 테이블
-- unique 속성이 없으면 외래키의 참조 대상이 될 수 없다.
ALTER TABLE jsp.tbl_member ADD CONSTRAINT tbl_member_pk PRIMARY KEY (member_id);
-- 게시글 테이블
alter table tbl_board
add constraint fk_member_id foreign key(writer) references tbl_member(member_id);
-- 댓글 테이블
alter table tbl_reply
add constraint fk_board_no foreign key(board_no) references tbl_board(board_no);

-- 외래키 참조관계로 만약 board_no 에  없느 게시판 번호를 입력하면 
-- 오류가 발생한다.
insert into tbl_reply(reply_no, reply_content, replyer, reply_date,board_no)
values (reply_seq.nextval,
           'test',
           'chacha',
           sysdate,
           123);
-- chacha => tbl_reply < tbl_board < tbl_member
-- sign_out_proc(p_member_id, p_ret_code, p_ret_msg);
           
/*프로시져 sign_out_proc*/
CREATE OR REPLACE PROCEDURE SIGN_OUT_PROC 
(
  P_MEMBER_ID IN VARCHAR2 
, P_RET_CODE OUT VARCHAR2 
, P_RET_MSG OUT VARCHAR2 
) AS 
    v_count number := -1;
BEGIN
    -- 작성댓글 삭제,
    delete from tbl_reply
    where replyer = p_member_id;
    
    -- 작성글 삭제
    -- tbl_board 글 삭제, 댓글 확인 -> 댓글 먼저 삭제.
    for r_board in (select board_no
                         from tbl_board
                         where writer = p_member_id) loop
        -- 댓글 여부 확인.
        select count(1)
        into v_count
        from tbl_reply 
        where board_no = r_board.board_no;
        if v_count > 0 then
            delete  from tbl_reply 
            where board_no = r_board.board_no;
        end if;
        delete from tbl_board
        where board_no = r_board.board_no;
    end loop;
    
    -- 회원 삭제
    delete from tbl_member
    where member_id = p_member_id;
    
    p_ret_code := 'OK';
    p_ret_msg := '삭제완료!';
EXCEPTION
    when OTHERS then
    p_ret_code :='NG';
    p_ret_msg := sqlerrm;
END SIGN_OUT_PROC;
/* 프로시져 사용하는 pl/sql 문*/
declare
    v_member_id tbl_member.member_id%TYPE;
    
    v_cnt number(10);
    v_ret_code varchar(10);
    v_ret_msg varchar(1000);
begin
    v_cnt := -1;
    v_member_id := 'chacha';
    sign_out_proc(v_member_id, v_ret_code, v_ret_msg);
    select count(1)
    into v_cnt
    from tbl_member
    where member_id = v_member_id;
    
    dbms_output.put_line('카운트 ' || v_cnt);
    dbms_output.put_line('결과 코드 ' || v_ret_code);
    dbms_output.put_line('결과 메시지 ' || v_ret_msg);
end;
