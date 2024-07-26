package com.yedam.service;

import java.util.List;

import com.yedam.vo.ReplyVO;

public interface ReplyService {
	List<ReplyVO> replyList(int boardNo);
	boolean addRply(ReplyVO rvo);
	boolean removeReply(int replyNo); // 댓글 삭제
}
