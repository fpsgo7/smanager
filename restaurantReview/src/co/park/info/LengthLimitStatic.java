package co.park.info;

public class LengthLimitStatic {
	
	// int 형 길이 체크로 특정 자리수를 넘으면 -1 반환
	public static int lengthCheck(int value, int length) {
		if(value >= Math.pow(10, length)) {// length 가 5 이면 100000 가될것이다.
			return -1;
		}
		return value;
	}
	// String 형 길이 체크
	public static String lengthCheck(String value, int length) {
		if(value.length() > length) {
			return null;
		}
		return value;
	}
}
