package co.yedam.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter 
@Setter
@ToString
@AllArgsConstructor// 모든 필드에 대한 
@NoArgsConstructor// 매개 변수 선언이 없는 생성자
//@Data// @Getter, @Setter @HashCode 등 모든 것을 대체해준다.
public class StudentVO {
	private String stdNo; // std_no
	private String stdName;
	private String stdPhone;
	private String address;
	private String birthDate; // 
	private Date creationDate;
	
	
	public String briefShow() {
		return stdNo + "  " + stdName + "  " + stdPhone ;
	}
	
	
}
