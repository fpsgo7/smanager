package co.park.control;

import java.util.List;
import java.util.Scanner;

import co.park.dao.MemberDAO;
import co.park.dao.ReviewDAO;
import co.park.info.MemberFileStatic;
import co.park.info.MemberStatic;
import co.park.info.PrintErrStatic;
import co.park.info.ScannerStatic;
import co.park.vo.MemberVO;

public class MemberControl {
	private MemberDAO memberDAO = new MemberDAO();
	private ReviewDAO reviewDAO = new ReviewDAO();
	private Scanner scan = new Scanner(System.in);
	
	public void join() {
		String id;
		String password;
		joinPrint();
		while(true) {
			id = ScannerStatic.rightString(scan.nextLine());
			if(id == null) {
				System.out.print("잘못된 입력 입니다 다시 입력해주십시요. >");
				continue;
			}
			if(id.equals("0")) {
				System.out.println("회원 가입을 취소합니다.");
				return;
			}
			try {
				if(memberDAO.idCheck(id)) {
					System.out.print("중복된 아이디 입니다 다시입력해주십시요. > ");
					continue;
				}
				System.out.println("사용가능한 아이디 입니다.");
				break;
			} catch (Exception e) {
				PrintErrStatic.serverErrorPrint(e);
				System.out.print("서버에 문제가 발생하였습니다 다시입력해주십시요. >");
			}
		}
		System.out.print("비밀번호를 입력해주세요> ");
		while(true) {
			password = ScannerStatic.rightString(scan.nextLine());
			if(password == null) {
				System.out.print("잘못된 입력 입니다 다시 입력해주십시요. >");
				continue;
			}
			if(password.equals("0")) {
				System.out.println("회원 가입을 취소합니다.");
				return;
			}
			try {
				if(memberDAO.join(id, password)) {
					System.out.println("회원 가입이 완료되었습니다.");
					return;
				}
				System.out.print("회원 가입이 실패하였습니다\n"
						+ " 다시 입력해 주십시요 > ");
			}catch (Exception e) {
				PrintErrStatic.serverErrorPrint(e);
				System.out.print("서버 오류가 발생하였습니다\n"
						+ " 다시 입력해 주십시요 >");
			}
		}
	}
	private void joinPrint() {
		System.out.println("================================================");
		System.out.println("회원 가입을 시작합니다");
		System.out.println("해당 프로그램은 비밀번호를 암호화하지 않으며");
		System.out.println("보안이 취약하기에 ");
		System.out.println("실제 사용하는 비밀번호는 입력하지 마시길 바랍니다.");
		System.out.println("허가되는 특수기호는 다음과 같습니다");
		System.out.println("! @ # $ % ^ & + = / _");
		System.out.println("회원가입을 취소하고 싶으시다면 0만 입력해주세요");
		System.out.println("------------------------------------------------");
		System.out.print("아이디를 입력해주십시요 > ");
	}
	
	public boolean loginActive() {
		String id;
		String password;
		System.out.println("=================================");
		System.out.print("아이디를 입력해주세요 > ");
		id = ScannerStatic.rightString(scan.nextLine());
		System.out.print("비밀번호를 입력해주세요> ");
		password = ScannerStatic.rightString(scan.nextLine());
		if(id == null || password == null) {
			System.out.println("잘못된 값을 입력하셧습니다.");
			return false;
		}
		return login(id,password);
	}
	
	public boolean autoLogin() {
		String str = MemberFileStatic.getString();
		if(str == null) {
			System.out.println("저장된 정보가 없습니다.");
			return false;
		}
		String[] strArray = str.split(",");
		String id = strArray[0];
		String password = strArray[1];
		
		return login(id, password);
		
	}

	private boolean login(String id, String password) {
		try {
			MemberVO vo = memberDAO.login(id, password);
			// 로그인 실패시
			if(vo == null) {
				System.out.println("아이디 또는 비밀번호가 틀렸습니다.");
				return false;
			}
			MemberStatic.login(vo);
			System.out.println("로그인이 성공하였습니다.");
			System.out.println(MemberStatic.getId()+"님 환영합니다.");
			return true;
		} catch (Exception e) {
			PrintErrStatic.serverErrorPrint(e);
			System.out.println("서버 오류가 발생하였습니다.");
			return false;
		}
	}

	public void getMemberList() {
		List<MemberVO> list = null; 
		try {
			System.out.println("===================================================================");
			System.out.println("회원 리스트 입니다.");	
			list = memberDAO.getMemberList();
			for (MemberVO vo : list) {
				System.out.println("-------------------------------------------------------------------");
				System.out.printf("%s 님의 등급 %d \n", 
						vo.getId(), vo.getGrade());
			}
			System.out.println("-------------------------------------------------------------------");
			System.out.print("엔터를 치면 이전화면으로 이동됩니다.");
			scan.nextLine();
		}catch (Exception e) {
			PrintErrStatic.serverErrorPrint(e);
			System.out.println("서버 오류로 이전화면으로 돌아가겠습니다.");
		}
		
	}
	public void searchMember() {
		List<MemberVO> list = null; 
		System.out.println("아이디 검색 > ");
		String searchId = ScannerStatic.rightString(scan.nextLine());
		try {
			System.out.println("===================================================================");
			System.out.println("검색된 회원 리스트 입니다.");	
			list = memberDAO.getMemberList(searchId);
			for (MemberVO vo : list) {
				System.out.println("-------------------------------------------------------------------");
				System.out.printf("%s 님의 등급 %d \n", 
						vo.getId(), vo.getGrade());
			}
			System.out.println("-------------------------------------------------------------------");
			System.out.print("삭제할 대상의 아이디를 정확히 입력해주세요 > ");
			String id = ScannerStatic.rightString(scan.nextLine());

			if(reviewDAO.deleteReviewByMember(id) && memberDAO.deleteMemberById(id)) {
				System.out.println("회원이 성공적으로 삭제되었습니다.");
			}else {
				System.out.println("회원 삭제가 실패하였습니다.");
			}
		}catch (Exception e) {
			PrintErrStatic.serverErrorPrint(e);
			System.out.println("서버 오류로 이전화면으로 돌아가겠습니다.");
		}
		
	}
	
}
