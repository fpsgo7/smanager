package co.park.control;

import java.util.List;
import java.util.Scanner;

import co.park.dao.ReviewDAO;
import co.park.info.LengthLimitStatic;
import co.park.info.MemberStatic;
import co.park.info.PrintErrStatic;
import co.park.info.ScannerStatic;
import co.park.vo.RestaurantVO;
import co.park.vo.ReviewVO;

public class ReviewControl {
	private Scanner scan = new Scanner(System.in);
	private ReviewDAO dao = new ReviewDAO();

	public void reviewMenu(RestaurantVO vo) {
		while(true) {
			reviewMenuPrint(vo.getName());
			int choice = ScannerStatic.mustNaturalNum(scan.nextLine()); 
			switch (choice) {
			case 1:  
				addReview(vo.getId(), vo.getName());
				break;
			case 2:
				reviewList(vo.getId(), vo.getName());
				break;
			case 3:
				deleteReview(vo,MemberStatic.getId());
				break;
			case 4:
				updateReview(vo,MemberStatic.getId());
				break;
			case 5:
				System.out.println("이전메뉴로 돌아갑니다.");
				return;
			default:
				System.out.println("잘못된 입력입니다 다시입력해 주십시요.");
			}
		}
	}
	
	public void reviewMenuByManager(RestaurantVO vo) {
		while(true) {
			reviewByManagerMenuPrint(vo.getName());
			int choice = ScannerStatic.mustNaturalNum(scan.nextLine()); 
			switch (choice) {
			case 1:  
				reviewList(vo.getId(), vo.getName());
				break;
			case 2:
				deleteReview(vo);
				break;
			case 3:
				System.out.println("이전메뉴로 돌아갑니다.");
				return;
			default:
				System.out.println("잘못된 입력입니다 다시입력해 주십시요.");
			}
		}
	}

	private void reviewList(int id, String name) {
		List<ReviewVO> list = null; 
		try {
			System.out.println("===================================================================");
			System.out.println(name +" 에 대한 리뷰들 입니다.");	
			list = dao.getReviewsByRestaurant(id);
			for (ReviewVO vo : list) {
				System.out.println("-------------------------------------------------------------------");
				System.out.printf("%s 님의 %s 에 대한 리뷰\n"
						+ "%s \n"
						+ "포인드 %d, 복잡도 : %s, 날짜 %s \n", 
						vo.getMemberId(), vo.getMenu(), 
						vo.getReviewContent(), vo.getPoint(), 
						vo.getPlaceFull(), vo.getLastestDate());
			}
			System.out.println("-------------------------------------------------------------------");
			System.out.print("엔터를 치면 리뷰 선택목록으로 이동됩니다.");
			scan.nextLine();
		}catch (Exception e) {
			PrintErrStatic.serverErrorPrint(e);
			System.out.println("서버 오류로 이전화면으로 돌아가겠습니다.");
		}
		
	}

	private void deleteReview(RestaurantVO vo, String memberId) {
		List<ReviewVO> list = null; 
		try {
			System.out.println("===================================================================");
			System.out.println(vo.getName() +" 에 대한 "+ memberId + "님의 리뷰들 입니다.");	
			list = dao.getReviewsByRestaurant(vo.getId(),memberId);
			for (ReviewVO review : list) {
				System.out.println("-------------------------------------------------------------------");
				System.out.printf("%d. %s 님의 %s 에 대한 리뷰\n"
						+ "%s \n"
						+ "포인드 %d, 복잡도 : %s, 날짜 %s \n", 
						review.getId(),
						review.getMemberId(), review.getMenu(), 
						review.getReviewContent(), review.getPoint(), 
						review.getPlaceFull(), review.getLastestDate());
			}
			System.out.println("-------------------------------------------------------------------");
			System.out.println(" 0번을 눌러 삭제를 취소하거나 \n"
					+ "식당정보중 삭제하고자 하는 정보의 번호를 입력해주세요");
			int choose;
			while(true) {
					System.out.print("입력> ");
					choose = ScannerStatic.mustNaturalNum(scan.nextLine());
					if(choose == 0) {
						System.out.println("리뷰 삭제를 나갑니다. ");
						return;
					}
					for (ReviewVO revew : list) {
						if(revew.getId() == choose) {
							dao.deleteReview(choose);
							System.out.println("리뷰 삭제가 완료 되었습니다.");
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

	private void deleteReview(RestaurantVO vo) {
		List<ReviewVO> list = null; 
		try {
			System.out.println("===================================================================");
			System.out.println(vo.getName() +" 에 대한 리뷰들 입니다.");	
			list = dao.getReviewsByRestaurant(vo.getId());
			for (ReviewVO review : list) {
				System.out.println("-------------------------------------------------------------------");
				System.out.printf("%d. %s 님의 %s 에 대한 리뷰\n"
						+ "%s \n"
						+ "포인드 %d, 복잡도 : %s, 날짜 %s \n", 
						review.getId(),
						review.getMemberId(), review.getMenu(), 
						review.getReviewContent(), review.getPoint(), 
						review.getPlaceFull(), review.getLastestDate());
			}
			System.out.println("-------------------------------------------------------------------");
			System.out.println(" 0번을 눌러 삭제를 취소하거나 \n"
					+ "식당정보중 삭제하고자 하는 정보의 번호를 입력해주세요");
			int choose;
			while(true) {
					System.out.print("입력> ");
					choose = ScannerStatic.mustNaturalNum(scan.nextLine());
					if(choose == 0) {
						System.out.println("리뷰 삭제를 나갑니다. ");
						return;
					}
					for (ReviewVO revew : list) {
						if(revew.getId() == choose) {
							dao.deleteReview(choose);
							System.out.println("리뷰 삭제가 완료 되었습니다.");
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
	
	private void updateReview(RestaurantVO vo, String memberId) {
		List<ReviewVO> list = null; 
		try {
			System.out.println("===================================================================");
			System.out.println(vo.getName() +" 에 대한 "+ memberId + "님의 리뷰들 입니다.");	
			list = dao.getReviewsByRestaurant(vo.getId(),memberId);
			for (ReviewVO review : list) {
				System.out.println("-------------------------------------------------------------------");
				System.out.printf("%d. %s 님의 %s 에 대한 리뷰\n"
						+ "%s \n"
						+ "포인드 %d, 복잡도 : %s, 날짜 %s \n", 
						review.getId(),
						review.getMemberId(), review.getMenu(), 
						review.getReviewContent(), review.getPoint(), 
						review.getPlaceFull(), review.getLastestDate());
			}
			System.out.println("-------------------------------------------------------------------");
			System.out.println(" 0번을 눌러 수정을 취소하거나 \n"
					+ "식당정보중 수정하고자 하는 정보의 번호를 입력해주세요");
			int choose;
			while(true) {
				System.out.print("입력> ");
				choose = ScannerStatic.mustNaturalNum(scan.nextLine());
				if(choose == 0) {
					System.out.println("리뷰 수정을 나갑니다. ");
					return;
				}
				for (ReviewVO review : list) {
					if(review.getId() == choose) {
						while(true) {
							System.out.print("메뉴이름 무었이에요(20자 제한) > ");
							String name = ScannerStatic.rightStringWithBlink(scan.nextLine());
							name = LengthLimitStatic.lengthCheck(name,20);
							
							System.out.print("자신이 주고자하는 점수는 몇점인가요? (1~5) > ");
							int point = ScannerStatic.mustNaturalNum(scan.nextLine());
							point = LengthLimitStatic.lengthCheck(point, 1);

							System.out.print("리뷰 내용 을 작성해 주십시요 (100글자 제한됩니다 ) > ");
							String reviewContent  = ScannerStatic.rightStringWithBlink(scan.nextLine());
							reviewContent = LengthLimitStatic.lengthCheck(reviewContent,200);
							
							System.out.print("복잡도 (상,중,하 중하나를 입력해 수십시요) > ");
							String placeFull  = ScannerStatic.rightStringWithBlink(scan.nextLine());
							placeFull = LengthLimitStatic.lengthCheck(placeFull,1);
							
							if(name == null || point == -1 || reviewContent == null || placeFull == null) {
								System.out.println("잘못입력한 값이 있습니다. "
										+ "수정작업이 실패하였습니다.\n");
								return;
							}
							review.setMenu(name);
							review.setPoint(point);
							review.setReviewContent(reviewContent);
							review.setPlaceFull(placeFull);
							dao.updateReview(review);
							System.out.println("리뷰 수정이 완료 되었습니다.");
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

	private void addReview(int id, String restaurantName) {
		
		System.out.println("===============================================");
		System.out.println(restaurantName +"(식당 이름) 에 대한 리뷰를 추가합니다.");
		System.out.println("-----------------------------------------------");
		
		ReviewVO vo = new ReviewVO();
		System.out.print("메뉴이름 무었이에요(20자 제한) > ");
		String name = ScannerStatic.rightStringWithBlink(scan.nextLine());
		name = LengthLimitStatic.lengthCheck(name,20);
		
		System.out.print("자신이 주고자하는 점수는 몇점인가요? (1~5) > ");
		int point = ScannerStatic.mustNaturalNum(scan.nextLine());
		point = LengthLimitStatic.lengthCheck(point, 1);

		System.out.print("리뷰 내용 을 작성해 주십시요 (100글자 제한됩니다 ) > ");
		String reviewContent  = ScannerStatic.rightStringWithBlink(scan.nextLine());
		reviewContent = LengthLimitStatic.lengthCheck(reviewContent,200);
		
		System.out.print("복잡도 (상,중,하 중하나를 입력해 수십시요) > ");
		String placeFull  = ScannerStatic.rightStringWithBlink(scan.nextLine());
		placeFull = LengthLimitStatic.lengthCheck(placeFull,1);
		
		if(name == null || point == -1 || reviewContent == null || placeFull == null) {
			System.out.println("잘못입력한 값이 있습니다. +"
					+ "추가작업이 실패하였습니다.");
			return;
		}
		vo.setMemberId(MemberStatic.getId());
		vo.setRestaurantId(id);
		vo.setMenu(name);
		vo.setPoint(point);
		vo.setReviewContent(reviewContent);
		vo.setPlaceFull(placeFull);
		try {
			if(dao.insertReview(vo)) {
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

	private void reviewMenuPrint(String name) {
		System.out.println("==================================================================");
		System.out.println(name+" 식당의 리뷰 공간입니다 무엇을 하시겠습니까?");
		System.out.println("1. 리뷰를 추가합니다");
		System.out.println("2. 리뷰목록을 봅니다.");
		System.out.println("3. 내 리뷰를 삭제합니다.");
		System.out.println("4. 내 리뷰를 수정합니다.");
		System.out.println("5. 식당목록으로 돌아갑니다.");
		System.out.print("번호입력 > ");
		
	}

	private void reviewByManagerMenuPrint(String name) {
		System.out.println("==================================================================");
		System.out.println(name+" 식당의 리뷰 공간입니다 무엇을 하시겠습니까?");
		System.out.println("1. 리뷰목록을 봅니다.");
		System.out.println("2. 리뷰를 삭제합니다.");
		System.out.println("3. 식당목록으로 돌아갑니다.");
		System.out.print("번호입력 > ");
	}
}
