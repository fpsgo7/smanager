package co.park.control;

import java.util.List;
import java.util.Scanner;

import co.park.dao.RestaurantDAO;
import co.park.dao.ReviewDAO;
import co.park.info.LengthLimitStatic;
import co.park.info.MemberStatic;
import co.park.info.PrintErrStatic;
import co.park.info.ScannerStatic;
import co.park.vo.RestaurantVO;

public class RestaurantControl {
	private ReviewControl reviewControl = new ReviewControl();
	private RestaurantDAO restaurantDao = new RestaurantDAO();
	private ReviewDAO reviewDAO = new ReviewDAO();
	private Scanner scan = new Scanner(System.in);
	/**
	 * 식당 기능 메슈 (관리자 전용)
	 */
	public void restaurantMenu() {
		while(true) {
			restaurantMenuPrint();
			System.out.print("입력 > ");
			int choice = ScannerStatic.mustNaturalNum(scan.nextLine()); 
			switch (choice) {
			case 1:  
				addRestaurant();
				break;
			case 2:
				updateRestaurant();
				break;
			case 3:
				deleteRestaurant();
				break;
			case 4:
				restaurantList();
				break;
			case 5:
				System.out.println("이전메뉴로 돌아갑니다.");
				return;
			default:
				System.out.println("잘못된 입력입니다 다시입력해 주십시요.");
			}
		}
	}
	public void restaurantList() {
		List<RestaurantVO> list = null;
		int choose; 
		while(true) {
			try {
				System.out.println("===================================================================");
				System.out.println("번호 | 식당이름 | 가격대 최소 | 가격대 최대 | 이동 시간 | 최신날짜");
				System.out.println("-------------------------------------------------------------------");
				list = restaurantDao.getRestaurants();
				for (RestaurantVO vo : list) {
					System.out.printf("%d. %s %d원 %d원 %d분 %s\n", 
							vo.getId(), vo.getName(), 
							vo.getLowestPrice(), vo.getHighestPrice(), 
							vo.getDurationOfTime(), vo.getLastestDate());
				}
				System.out.println("0 번을 눌러 되돌아가거나 식당의 번호를 입력하여 리뷰를 볼수있습니다.");
				while(true) {
					System.out.print("번호 입력 > ");
					choose = ScannerStatic.mustNaturalNumOr0(scan.nextLine()); 
					if(choose == 0) {
						System.out.println("처음화면으로 돌아갑니다.");
						return;
					}
					RestaurantVO vo = findRestaurantId(choose,list);
					if(vo == null){
						System.out.println("잘못된 입력입니다 다시 입력해주시길 바랍니다.");
					}else {
						// 해당 식당의 리뷰로 이동
						if(MemberStatic.getGrade() == 0 ) {
							reviewControl.reviewMenuByManager(vo);
						}else {
							reviewControl.reviewMenu(vo);
						}
						break;
					}
				}
			}catch (Exception e) {
				PrintErrStatic.serverErrorPrint(e);
				System.out.println("서버 오류로 이전화면으로 돌아가겠습니다.");
			}
		}
	}
	private RestaurantVO findRestaurantId(int choose, List<RestaurantVO> list) {
		for (RestaurantVO vo : list) {
			if(vo.getId() == choose) {
				return vo;
			}
		}
		return null;
	}
	private void deleteRestaurant() {
		List<RestaurantVO> list = null;
		int choose;
		try {
			System.out.println("===================================================================");
			System.out.println("번호 | 식당이름 | 가격대 최소 | 가격대 최대 | 이동 시간 | 최신날짜");
			System.out.println("-------------------------------------------------------------------");
			list = restaurantDao.getRestaurants();
			for (RestaurantVO vo : list) {
				System.out.printf("%d. %s %d원 %d원 %d분 %s\n", 
						vo.getId(), vo.getName(), 
						vo.getLowestPrice(), vo.getHighestPrice(), 
						vo.getDurationOfTime(), vo.getLastestDate());
			}
			System.out.println("-------------------------------------------------------------------");
			System.out.println(" 0번을 눌러 삭제를 취소하거나 \n"
					+ "식당정보중 삭제하고자 하는 정보의 번호를 입력해주세요");
			
			while(true) {
				System.out.print("입력> ");
				choose = ScannerStatic.mustNaturalNumOr0(scan.nextLine());
				if(choose == 0) {
					System.out.println("식당 삭제을 나갑니다. ");
					return;
				}
				for (RestaurantVO vo : list) {
					if(vo.getId() == choose) {
						reviewDAO.deleteReviewByRestaurant(choose);
						restaurantDao.deleteRestaurant(choose);
						System.out.println("식당 삭제가 완료 되었습니다.");
						return;
					}
				}
				System.out.println("해당하는 번호가 없습니다 다시입력해주세요");
			}
		}catch (Exception e) {
			PrintErrStatic.serverErrorPrint(e);
			System.out.println("서버 오류로 이전화면으로 돌아가겠습니다.");
		}
		
	}
	private void updateRestaurant() {
		List<RestaurantVO> list = null;
		int choose;
		try {
			System.out.println("===================================================================");
			System.out.println("번호 | 식당이름 | 가격대 최소 | 가격대 최대 | 이동 시간 | 최신날짜");
			System.out.println("-------------------------------------------------------------------");
			list = restaurantDao.getRestaurants();
			for (RestaurantVO vo : list) {
				System.out.printf("%d. %s %d원 %d원 %d분 %s\n", 
						vo.getId(), vo.getName(), 
						vo.getLowestPrice(), vo.getHighestPrice(), 
						vo.getDurationOfTime(), vo.getLastestDate());
			}
			System.out.println("-------------------------------------------------------------------");
			System.out.println(" 0번을 눌러 수정을 취소하거나 \n"
					+ "식당정보중 수정하고자 하는 정보의 번호를 입력해주세요");
			
			while(true) {
				System.out.print("입력> ");
				choose = ScannerStatic.mustNaturalNumOr0(scan.nextLine());
				if(choose == 0) {
					System.out.println("식당 수정을 나갑니다. ");
					return;
				}
				for (RestaurantVO vo : list) {
					if(vo.getId() == choose) {
						while(true) {
							System.out.print("식당이름은 무었이에요(20자 제한) > ");
							String name = ScannerStatic.rightStringWithBlink(scan.nextLine());
							name = LengthLimitStatic.lengthCheck(name,20);
							
							System.out.print("메뉴들중 낮은 가격은(최대 6자리) > ");
							int lowPrice = ScannerStatic.mustNaturalNumOrBlink(scan.nextLine());
							lowPrice = LengthLimitStatic.lengthCheck(lowPrice,6);
							
							System.out.print("메뉴들중 높은 가격은(최대 6자리) > ");
							int highPrice = ScannerStatic.mustNaturalNumOrBlink(scan.nextLine());
							highPrice = LengthLimitStatic.lengthCheck(highPrice,6);
							
							System.out.print("학원 기준으로 이동시간은 어느정도 인가요(최재 2자리) > ");
							int durationTime = ScannerStatic.mustNaturalNumOrBlink(scan.nextLine());
							durationTime = LengthLimitStatic.lengthCheck(durationTime,2);
							
							if(name == null || lowPrice == -1 || highPrice == -1 || durationTime == -1) {
								System.out.println("잘못입력한 값이 있습니다. "
										+ "수정작업이 실패하였습니다.\n");
								return;
							}
							vo.setName(name);
							vo.setLowestPrice(lowPrice);
							vo.setHighestPrice(highPrice);
							vo.setDurationOfTime(durationTime);
							restaurantDao.updateRestaurant(vo);
							System.out.println("식당 수정이 완료 되었습니다.");
							return;
						}
					}
				}
				System.out.println("해당하는 번호가 없습니다 다시입력해주세요");
			}
		}catch (Exception e) {
			PrintErrStatic.serverErrorPrint(e);
			System.out.println("서버 오류로 이전화면으로 돌아가겠습니다.");
		}
		
	}
	private void addRestaurant() {
		System.out.println("===============================================");
		System.out.println("새로운 식당을 발견하셧군요 식당을 을 추가합니다");
		System.out.println("모든 입력내용에대하여 입력해주셔야 합니다.");
		System.out.println("-----------------------------------------------");
		
		RestaurantVO vo = new RestaurantVO();
		
		System.out.print("식당이름은 무었이에요(20자 제한) > ");
		String name = ScannerStatic.rightStringWithBlink(scan.nextLine());
		name = LengthLimitStatic.lengthCheck(name,20);
		
		System.out.print("메뉴들중 낮은 가격은(최대 6자리) > ");
		int lowPrice = ScannerStatic.mustNaturalNum(scan.nextLine());
		lowPrice = LengthLimitStatic.lengthCheck(lowPrice,6);
		
		System.out.print("메뉴들중 높은 가격은(최대 6자리) > ");
		int highPrice = ScannerStatic.mustNaturalNum(scan.nextLine());
		highPrice = LengthLimitStatic.lengthCheck(highPrice,6);
		
		System.out.print("학원 기준으로 이동시간은 어느정도 인가요(최재 2자리) > ");
		int durationTime = ScannerStatic.mustNaturalNum(scan.nextLine());
		durationTime = LengthLimitStatic.lengthCheck(durationTime,2);
		
		if(name == null || lowPrice == -1 || highPrice == -1 || durationTime == -1) {
			System.out.println("잘못입력한 값이 있습니다. "
					+ "추가작업이 실패하였습니다.\n");
			return;
		}
		vo.setName(name);
		vo.setLowestPrice(lowPrice);
		vo.setHighestPrice(highPrice);
		vo.setDurationOfTime(durationTime);
		try {
			if(restaurantDao.insertRestaurant(vo)) {
				System.out.println("추가 작업이 성공하였습니다.");
			}else {
				System.out.println("추가 작업이 실패하였습니다.");
			}
		} catch (Exception e) {
			PrintErrStatic.serverErrorPrint(e);
			System.out.print("서버 오류가 발생하였습니다"
					+ " 추가작업이 실패하였습니다. >");
		}
		
	}
	private void restaurantMenuPrint() {
		System.out.println("===============================================");
		System.out.println("식당메뉴입니다 무었을 하시겠습니까?");
		System.out.println("-----------------------------------------------");
		System.out.println("1. 식당 추가");
		System.out.println("2. 식당 수정");
		System.out.println("3. 식당 삭제");
		System.out.println("4. 식당 목록");
		System.out.println("5. 이전화면으로");
	}
}
