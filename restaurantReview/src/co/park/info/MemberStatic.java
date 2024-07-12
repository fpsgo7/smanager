package co.park.info;

import co.park.vo.MemberVO;

public class MemberStatic {
	private static String id = null;
	private static String password = null;
	private static int grade = -1;
	
	public static void login(MemberVO vo) {
		MemberStatic.id = vo.getId();
		MemberStatic.password = vo.getPassword();
		MemberStatic.grade = vo.getGrade();
	}
	public static String getId() {
		return id;
	}
	public static String getPassword() {
		return password;
	}
	public static int getGrade() {
		return grade;
	}
}
