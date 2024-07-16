package co.park.control;

import java.util.Scanner;

import co.park.info.ScannerStatic;

public class ManagerControl {
	private Scanner scan = new Scanner(System.in);
	RestaurantControl restaurantControl = new RestaurantControl();
	MemberControl memberControl = new MemberControl();
	
	public void managerMenu() {
		
		while(true) {
			managerMenuPrint();
			System.out.print("입력 > ");
			int choice = ScannerStatic.mustNaturalNum(scan.nextLine()); 
			switch (choice) {
			case 1:  
				restaurantControl.restaurantMenu();
				break;
			case 2:
				managerMemberMenu();
				break;
			case 3:
				System.out.println("이전메뉴로 돌아갑니다.");
				return;
			default:
				System.out.println("잘못된 입력입니다 다시입력해 주십시요.");
			}
		}
	}
	private void managerMenuPrint() {
		System.out.println("===============================================");
		System.out.println("안녕하십니까 관리자님 무었을 하시겠습니까?");
		System.out.println("-----------------------------------------------");
		System.out.println("1. 식당 메뉴");
		System.out.println("2. 회원 메뉴");
		System.out.println("3. 이전화면으로");
	}
	
	private void managerMemberMenu() {
		
		while(true) {
			managerMemberMenuPrint();
			System.out.print("입력 > ");
			int choice = ScannerStatic.mustNaturalNum(scan.nextLine()); 
			switch (choice) {
			case 1:  
				memberControl.getMemberList();
				break;
			case 2:
				memberControl.searchMember();
				break;
			case 3:
				System.out.println("이전메뉴로 돌아갑니다.");
				return;
			default:
				System.out.println("잘못된 입력입니다 다시입력해 주십시요.");
			}
		}
	}
	private void managerMemberMenuPrint() {
		System.out.println("===============================================");
		System.out.println("회원에 대한 어떤작업을 하시겠습니까?");
		System.out.println("-----------------------------------------------");
		System.out.println("1. 회원 리스트 보기");
		System.out.println("2. 회원 검색 후 삭제");
		System.out.println("3. 이전화면으로");
	}
}
