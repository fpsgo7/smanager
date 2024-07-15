package co.park.info;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MemberFileStatic {
	public static final String DIRECTORY = "D:/restaurant/";
	public static final String FILE_NAME = "member.txt";
	
	public static boolean addMemberFile() {
		try {
			String path = DIRECTORY;
			
			// 경로 생성
			File directory = new File(path);
			if(!directory.exists()) {
				directory.mkdir();
			}
			
			// 파일생성
			File file = new File(directory,FILE_NAME);
			file.delete();
			file.createNewFile();
			
			char[] idArray = MemberStatic.getId().toCharArray();
			char[] passwordArray = MemberStatic.getPassword().toCharArray();
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write(idArray);
			fileWriter.write(',');
			fileWriter.write(passwordArray);
			fileWriter.flush();
			fileWriter.close();
			
			return true;
		} catch (IOException e) {
			PrintErrStatic.serverErrorPrint(e);
			return false;
		}
	}

	public static boolean removeMemberFile() {
		try {
			String path = DIRECTORY;
			
			// 경로 생성
			File directory = new File(path);
			if(!directory.exists()) {
				directory.mkdir();
			}
			
			// 파일생성
			File file = new File(directory,FILE_NAME);
			file.delete();
			file.createNewFile();
			
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write(' ');
			fileWriter.write(',');
			fileWriter.write(' ');
			fileWriter.flush();
			fileWriter.close();
			
			return true;
		} catch (IOException e) {
			PrintErrStatic.serverErrorPrint(e);
			return false;
		}
	}

	public static String getString() {
		String string = null;
		try {
			// 파일 찾기
			File file = new File(DIRECTORY,FILE_NAME);
			try (FileReader fileReader = new  FileReader(file)) {
				int ch;
				while((ch = fileReader.read())!= -1) {
					if(string == null) {
						string = String.valueOf((char)ch);
					}else {
						string += (char)ch;
					}					
				}
			}
			return string;
		} catch (Exception e) {
			PrintErrStatic.serverErrorPrint(e);
			return string;
		}
		
	}
}
