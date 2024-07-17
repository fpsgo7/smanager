package co.yedam.mapper;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DataSource {
	// SqlSessionFactory 생성
	public static SqlSessionFactory getInstance() {
		// 해당 xml 파일을 읽어서 작업해준다.
		// 경로는 src를 기준으로 해준다.
		String resource = "mybatis-config.xml";
		InputStream inputStream = null;
		
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SqlSessionFactory sqlSessionFactory 
			= new SqlSessionFactoryBuilder().build(inputStream);
		return sqlSessionFactory;
	}
	
}
