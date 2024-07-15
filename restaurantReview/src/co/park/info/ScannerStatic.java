package co.park.info;

public class ScannerStatic {

	public static int mustNaturalNum(String string) {
		try {
			int num = Integer.parseInt(string);
			if(num > 0) {
				return num;
			}else {
				return -1;
			}
		}catch(Exception e){
			return -1;
		}
	}
	
	public static int mustNaturalNumOr0(String string) {
		try {
			int num = Integer.parseInt(string);
			if(num >= 0) {
				return num;
			}else {
				return -1;
			}
		}catch(Exception e){
			return -1;
		}
	}
	
	public static int mustNaturalNumOrBlink(String string) {
		if(string.length() < 1) {
			return 0;
		}
		try {
			int num = Integer.parseInt(string);
			if(num > 0) {
				return num;
			}else {
				return -1;
			}
		}catch(Exception e){
			return -1;
		}
	}
	// 올바른 값인지 확인한다.
	// 허가된 특수 기호 : ! @ # $ % ^ & + = / _
	public static String rightString(String string) {
		
		char[] charArray = string.toCharArray();
		// 사용하면 안되는 대상이 있는지 확인한다.
		for (char c : charArray) {
			if(c == ' ' || c == '"' || c == '\'' || c == ':' || c == ';' || 
					c == '{' || c == '[' || c == '}' || c == ']' || c == '\\' || 
					c == '|' || c == ',' || c == '.' || c == '<' || c == '>' ||
					c == '?' || c == '*' || c == '(' || c == ')' ||
					c == '-' ) {
				return null;
			}
		}
		
		return string;
	}
	
	// 올바른 값인지 확인하는데서 빈 문자열도 허용한다.
	public static String rightStringWithBlink(String string) {
		
		char[] charArray = string.toCharArray();
		// 사용하면 안되는 대상이 있는지 확인한다.
		for (char c : charArray) {
			if(c == '"' || c == '\'' || c == ':' || c == ';' || 
					c == '{' || c == '[' || c == '}' || c == ']' || c == '\\' || 
					c == '|' || c == ',' || c == '.' || c == '<' || c == '>' ||
					c == '?' || c == '*' || c == '(' || c == ')' ||
					c == '-' ) {
				return null;
			}
		}
		
		return string;
	}
}
