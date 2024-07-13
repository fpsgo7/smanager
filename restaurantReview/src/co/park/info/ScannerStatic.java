package co.park.info;

public class ScannerStatic {

	public static int mustInt(String string) {
		try {
			return Integer.parseInt(string);
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
}
