package com.yedam.service;

import com.yedam.vo.MemberVO;

public interface MemberService {
	MemberVO loginCheck(String id, String pw);
}
