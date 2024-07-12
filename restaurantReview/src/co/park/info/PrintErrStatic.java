package co.park.info;

public class PrintErrStatic {
	/**
	 * 서비스때는 로그가 안뜨게한다.
	 * @param e
	 */
	public static void errPrint(Exception e) {
		e.printStackTrace();
	}
}
