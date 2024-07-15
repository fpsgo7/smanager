package co.park.control;

import java.util.Scanner;

import co.park.dao.MemberDAO;
import co.park.dao.RestaurantDAO;
import co.park.dao.ReviewDAO;
import co.park.info.MemberStatic;
import co.park.info.ScannerStatic;

public class RestaurnatReviewControl {
	Scanner scan = new Scanner(System.in);
	MemberControl memberControl = new MemberControl();
	RestaurantControl restaurantControl = new RestaurantControl();
	MemberDAO  memberDAO = new MemberDAO();
	RestaurantDAO restaurantDAO = new RestaurantDAO();
	ReviewDAO reviewDAO = new ReviewDAO();
	
	int choice = 0;
	
	public void main() {
		while(true) {
			mainPrint();
			int choice = ScannerStatic.mustNaturalNumOr0(scan.nextLine()); 
			if(MemberStatic.getId() == null) {
				switch (choice) {
				case 1:  
					if(memberControl.login()) {
						loginSuccess(MemberStatic.getGrade());
					}
					break;
				case 2:
					memberControl.join();// 회원 가입 작성완료
					break;
				case 3:
					InstructionForUse();// 작성 완료
					break;
				case 4:
					// 작업전 (기본적인거 완료후 추가작업때 하자)
					memberControl.autoLogin();
					break;
				case 5:
					System.out.println("종료합니다.");
					return;
				default:
					System.out.println("잘못된 입력입니다 다시입력해 주십시요.");
				}
			}
			else {
				switch (choice) {
				case 1:  
					MemberStatic.logout();
					System.out.println("로그아웃이 완료되었습니다.");
					break;
				case 2:
					InstructionForUse();// 회원 가입 작성완료
					break;
				case 3:
					if(MemberStatic.getGrade() == 0) {
						restaurantControl.restaurantMenu();
					}else {
						restaurantControl.restaurantList();
					}
					break;
				case 4:
					System.out.println("자동 로그인 등록");
					break;
				case 5:
					System.out.println("종료합니다.");
					return;
				default:
					System.out.println("잘못된 입력입니다 다시입력해 주십시요.");
				}
			}
		}
	}
	
	private void loginSuccess(int grade) {
		if(grade == 0) {
			// 관리자
			restaurantControl.restaurantMenu();
		}else {
			restaurantControl.restaurantList();
		}
		
	}

	/**
	 * 주의 사항을 알려주는 출력 메서드이다.
	 */
	private void InstructionForUse() {
		System.out.println("해당 프로그램은 학원 주변에 "
			//  프로그램 설명
			+ "식당들에대한 리뷰를 작성하기위한 프로그램입니다."
			// 금지사항
			+ "\n리뷰 작성시 욕설및 비방은 금지하고 있으며"
			+ "\n 사용하지 마시길 바랍니다."
			// 점수 기준
			+ "\n 식당 리뷰에서의 점수 기준 작성은"
			+ "\n 5점 꼭 가보길 추천하는 곳"
			+ "\n 4점 추천할만한 곳"
			+ "\n 3점 좋은 곳"
			+ "\n 2점과 1점 추천하지 않는 곳"
			+ "\n 이며 맛이 좋더라도 가게가 복잡하면"
			+ "\n 그이유에 대하여 감점하셔도됩니다."
			// 금지된 특수기호
			+ "! @ # $ % ^ & + = / _ 을 제외하고는 특수기호를 거부하고 있습니다."
			// 인사말
			+ "\n 이상 저의 프로그램에 대한"
			+ "\n 설명을 읽어주셔서 감사합니다.");
	}
	private void mainPrint() {
		if(MemberStatic.getId() == null) {
			System.out.println("=================================");
			System.out.println("  식당 리뷰 ver0.1에 오신것을 환영합니다. ");
			System.out.println("=================================");
			System.out.println("1. 로그인하기");
			System.out.println("2. 회원가입하기");
			System.out.println("3. 사용안네(처음 이용하시면 읽어주세요)");
			System.out.println("4. 자동 로그인");
			System.out.println("5. 종료");
			System.out.print("입력 > ");
		}
		else {
			System.out.println("=================================");
			System.out.println("  안녕하십니까 "+MemberStatic.getId()+ "님");
			System.out.println("=================================");
			System.out.println("1. 로그아웃");
			System.out.println("2. 사용안네");
			if(MemberStatic.getGrade() == 0) {
				System.out.println("3. 다시 식당메뉴로 ");
			}else {
				System.out.println("3. 다시 식당리스트로 ");
			}
			System.out.println("4. 자동 로그인 등록");
			System.out.println("5. 종료");
			System.out.print("입력 > ");
		}
	}
}
