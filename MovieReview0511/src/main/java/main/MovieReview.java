package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import MovieDao.MovieDao;
import MovieDao.ReviewDao;
import MovieDao.UserDao;
import vo.MovieVO;
import vo.ReviewVO;
import vo.UserVO;
import vo.selectAllVO;
import vo.selectReviewVO;

public class MovieReview {

	static int select; // ���õǴ� ���� ���� ������ �ʿ� ���� ���⼭ ������ �� �ֽ��ϴ�.
	Scanner scanner = new Scanner(System.in);
	String loginInfo;
	
	//�� VO List
	List<MovieVO>        movieList        = new ArrayList<>();
	List<ReviewVO>       reviewList       = new ArrayList<>();
	List<selectAllVO>    selectAllList    = new ArrayList<>();
	List<selectReviewVO> selectReviewList = new ArrayList<>();
	List<UserVO>         userList         = new ArrayList<>();

	
	private int initDisplay() {
		System.out.println("---------------[Welcome MoviewReview SITE]----------------");
		System.out.println("1. �α���");
		System.out.println("2. ȸ������");
		
		select = scanner.nextInt();

		return select;
	}//initDisplay() end

	
	public MovieReview() {
		initDisplay();
		do {
			if (select == 1) {
				login();
			} else if (select == 2) {
				signup();
			}
		} while (select != 3);
		System.out.println("[ERROR]�߸��� �Է��Դϴ�");
		System.out.println();
		new MovieReview();
	}//MovieReview() end

	
	//TODO : �����Է� �̹� ��ϵ� ���̵�� �α����ϰų� ���ư��� �����ϱ� ����ϸ� ó������ ���ư���(������)
	private void signup() { //
		
		List<UserVO> list = UserDao.getInstance().userList();
		List<String> memberList = new ArrayList<String>();
		
		for (UserVO vo : list) {
			memberList.add(vo.getId());
		}
		
		scanner.nextLine();
		
		System.out.println("------------------------[ȸ������]------------------------");
		System.out.println("ID:");
		String id = scanner.nextLine();

		if (memberList.contains(id)) {
			System.out.println("[ERROR]�̹� ��ϵ� ���̵��Դϴ�. 1.�α����ϱ�  2.���ư���");
			select = scanner.nextInt();
			if (select == 1) {
				login();
			} else if (select == 2) {
				new MovieReview();
			}
		}//if end

		System.out.println("Password:");
		String pwd = scanner.nextLine();
		
		System.out.printf("ID      : %s\n", id);
		System.out.printf("Password: %s\n", pwd);
		System.out.println();
		System.out.println("[�Է��Ͻ� ������ �����Ͻðڽ��ϱ�? 1.����  2.���]");
		select = scanner.nextInt();

		if (select == 1) {
			UserVO vo = new UserVO(id, pwd);
			UserDao.getInstance().insertSignUp(vo);
			System.out.println("[ȸ�������� �Ϸ�Ǿ����ϴ�!]");
			loginInfo = id;
			mainpage();
		} else if (select == 2) {
			new MovieReview();
		}
	}//signup() end

	
	public void login() {
		/*
		 * TODO: ������ �α����ϱ� �����Է� ���̵��Է��ϸ� 1. �ε����� �ش��ϴ� ����������� 2. ���̵𰡾����� ���̵� Ʋ�Ƚ��ϴ� ����Է�
		 * 1. �ش��ϴ� ����� ������ ������������ �̵��ϱ� 2. ����� Ʋ���� ����� Ʋ�Ƚ��ϴ�
		 */
		scanner.nextLine();
		
		System.out.println("ID:");
		String id = scanner.nextLine();
		UserVO vo = new UserVO(id);

		List<UserVO> voList = UserDao.getInstance().checkUserInfo(vo); // �����������ϱ�
		try {
			if (voList.get(0).getId().equals(id)) {
				System.out.println("Password:");
				String pwd = scanner.nextLine();
				if (voList.get(0).getPwd().equals(pwd)) {
					this.loginInfo = voList.get(0).getId();
					mainpage();
				}else {
					System.out.println("[ERROR]��ġ�ϴ� ������ �����ϴ�.");
					System.out.println("[�����Ͻðڽ��ϱ�?  1.YES  2.NO]");
					select = scanner.nextInt();
					
					if(select==1) {
						login();
					}else {
						System.out.println("[�̿����ּż� �����մϴ�!]");
					}
					
				}
			}
		} catch (Exception e) {
			System.out.println("[ERROR]��ġ�ϴ� ���̵� �����ϴ� Ȩ�������� ���ư��ϴ�.");
			new MovieReview();
		}
	}//login() end

	
	
	private void mainpage() {
		
		System.out.println();
		System.out.println("-----------------------[MAIN PAGE]-----------------------");
		System.out.println();
		System.out.printf("�ȳ��ϼ��� [%s]��!\n", loginInfo);
		System.out.println();
		System.out.println("1. ��ȭ����");
		System.out.println("2. �� ���亸��");
		select = scanner.nextInt();

		if (select == 1) {
			selectMovies();
		} else if (select == 2) {
			showMyReviews();
		} else {
			System.out.println("[ERROR]�߸��� �Է��Դϴ� Ȩ�������� ���ư��ϴ�.");
			mainpage();
		}
	}

	
	private void selectMovies() { // TODO: ��ȭ����(�����)
		
		System.out.println("---------------------[Select Movies]--------------------");
		movieList = MovieDao.getInstance().select_movie_list();
		
		System.out.println("[��ȣ  ��ȭ����]");
		
		for(MovieVO vo : movieList) {
			System.out.printf("[%d - %s]\n", vo.getMovieIdx(), vo.getMovieTitle());
		}
		System.out.println(); //�� ����
		
		System.out.println("1. �����ۼ�");
		System.out.println("2. ���亸��");
		System.out.println("3. ���ư���");
		
		select = scanner.nextInt();
		
		if(select == 1) {
			insertReview();
		} else if (select == 2) {
			selectReview();
		} else if (select == 3) {
			System.out.println("Ȩ�������� ���ư��ðڽ��ϱ�? [1.YES  2.NO]");
			select = scanner.nextInt();
			
			if(select==1) {
				mainpage();
			}else if(select==2) {
				System.out.println("[�̿����ּż� �����մϴ�!]");
			}
		}
	}//selectMovies() end

	
	private void selectReview() { // TODO: ��ü����(�����)
		
		selectReviewList = ReviewDao.getInstance().select_Review_List();
		
		System.out.println("-----------------------[Reviews]------------------------");
		System.out.println("[�����ȣ    ��ȭ��ȣ    ��ȭ����    �г���    �ۼ�����    �ۼ�����]");
		System.out.println();
		
		for(selectReviewVO vo : selectReviewList) {
			System.out.printf("[%d - %d - %s - %s - %s - %s]\n", vo.getReviewIdx(), vo.getMoviewIdx(), vo.getMovieTitle(), vo.getUserId(),
														         vo.getReviewText(), vo.getReviewDate());
		}
		
		System.out.println("-------------------------------------------------------");
		System.out.println("1. �����ۼ�");
		System.out.println("2. ���ư���");
		
		select = scanner.nextInt();
		
		if(select == 1) {
			insertReview();
		} else if (select == 2) {
			System.out.println("Ȩ�������� ���ư��ðڽ��ϱ�? [1.YES  2.NO]");
			select = scanner.nextInt();
			
			if(select==1) {
				mainpage();
			}else if(select==2) {
				System.out.println("[�̿����ּż� �����մϴ�!]");
			}
		}
	}//selectReview() end
	
	
	
	private void insertReview() { // TODO: �����ۼ�(�����) -> ��ȭ������� ���̰� �ϱ� movieTitle
		
		System.out.println();
		System.out.println("��ȭ��ȣ����:");
		int movieIdx = scanner.nextInt();
		
		String id = loginInfo;
		
		System.out.println();
		System.out.println("�����Է�:");
		String reviewText = scanner.next();
		
		ReviewVO vo  = new ReviewVO();

		vo.setMovieIdx(movieIdx);
		vo.setId(id);
		vo.setReviewText(reviewText);
		
		int res = ReviewDao.getInstance().insert_review(vo);
		
		System.out.println();
		System.out.printf("[%s]�� �������� �Ϸ�Ǿ����ϴ�.\n", id);
		
		System.out.println();
		//selectReview();
		
		selectReviewList = ReviewDao.getInstance().select_Review_List();
		
		System.out.println("----------------------[Review List]----------------------");
		System.out.println("[�����ȣ  ��ȭ��ȣ  ��ȭ����  �г���  �ۼ�����  �ۼ�����]");
		System.out.println();
		
		for(selectReviewVO vo1 : selectReviewList) {
			System.out.printf("[%d - %d - %s - %s - %s - %s]\n", vo1.getReviewIdx(), vo1.getMoviewIdx(), vo1.getMovieTitle(), vo1.getUserId(),
														         vo1.getReviewText(), vo1.getReviewDate());
		}//for end
		
		System.out.println();
		System.out.println("Ȩ�������� ���ư��ðڽ��ϱ�? [1.YES  2.NO]");
		select = scanner.nextInt();
		
		if(select==1) {
			mainpage();
		}else {
			System.out.println("[�̿����ּż� �����մϴ�!]");
		}
	}//insertReivew() end

	
	
	private void showMyReviews() { // TODO: �� ����Ȯ���ϱ� �ֱ�(�ֱԹ�)
		
		//selectReviewVO srv = new selectReviewVO(loginInfo);
		selectReviewList   = ReviewDao.getInstance().showMyReview(loginInfo);
		
		System.out.println("--------------------[My Review List]-------------------");
		System.out.println("[�����ȣ  ��ȭ��ȣ  ��ȭ����  �г���  �ۼ�����  �ۼ�����]");
		for(selectReviewVO vo : selectReviewList) {
			System.out.printf("[%d - %d - %s - %s - %s]\n", vo.getReviewIdx(), vo.getMoviewIdx(), vo.getMovieTitle(),
														    vo.getReviewText(), vo.getReviewDate());
		}
		
		System.out.println("-------------------------------------------------------");
		
		System.out.println("1. ������");
		System.out.println("2. �������");
		System.out.println("3. �������");
		System.out.println("4. ���ư���");
		
		select = scanner.nextInt();
		
		if(select==1) {
			insertReview();
		}else if(select==2) {
			updateMyReview();
		} else if (select==3) {
			deleteMyReview();
		} else if (select==4) {
			System.out.println("Ȩ�������� ���ư��ðڽ��ϱ�? [1.YES  2.NO]");
			select = scanner.nextInt();
			
			if(select==1) {
				mainpage();
			}else if(select==2) {
				System.out.println("[�̿����ּż� �����մϴ�!]");
			}
		}
	}//showMyReview() end

	
	private void deleteMyReview() { // TODO: �ֱԹ� ���� ��, �ش� id�� ������ ��ȸ
		
		ReviewVO vo = new ReviewVO(loginInfo);

		reviewList = ReviewDao.getInstance().select_Review_My(vo);

		for (ReviewVO vo2 : reviewList) {
			System.out.printf("[%d - %d - %s - %s - %s]\n", vo2.getReviewIdx(), vo2.getMovieIdx(), vo2.getId(),
					vo2.getReviewDate(), vo2.getReviewText());
		}
		
		
		System.out.println("������ �����ȣ�� �Է��ϼ���.");
		int deleteReview = scanner.nextInt();
		
		System.out.println("���� �����Ͻðڽ��ϱ�?");
		System.out.println("1. �������");
		System.out.println("2. ���ư���");
		
		select = scanner.nextInt();
		
		if (select == 1) {
			ReviewVO vo3 = new ReviewVO(deleteReview);
			ReviewDao.getInstance().DeleteReview(vo3);
			selectReviewList = ReviewDao.getInstance().select_Review_List();
			
			System.out.println("----------------------[Review List]----------------------");
			System.out.println("[�����ȣ  ��ȭ��ȣ  ��ȭ����  �г���  �ۼ�����  �ۼ�����]");
			System.out.println();
			
			for(selectReviewVO vo1 : selectReviewList) {
				System.out.printf("[%d - %d - %s - %s - %s - %s]\n", vo1.getReviewIdx(), vo1.getMoviewIdx(), vo1.getMovieTitle(), vo1.getUserId(),
															         vo1.getReviewText(), vo1.getReviewDate());
			}
			System.out.println();
			System.out.print("[����]�� �Ϸ�Ǿ����ϴ� Ȩ�������� ���ư��ðڽ��ϱ�?  [1.YES  2.NO]");
			select = scanner.nextInt();
			
			if(select==1) {
				mainpage();
			}else if(select==2) {
				System.out.println("[�̿����ּż� �����մϴ�!]");
			}
			//TODO : ���� �� ��ü������ȸ
			
		} else if (select == 2) {
			
			System.out.println("Ȩ�������� ���ư��ðڽ��ϱ�? [1.YES  2.NO]");
			select = scanner.nextInt();
			
			if(select==1) {
				mainpage();
			}else if(select==2) {
				System.out.println("[�̿����ּż� �����մϴ�!]");
			}
		}
	}//deleteMyReview() end

	
	
	private void updateMyReview() { // TODO: �ֱԹ� ���� ��, �ش� id�� ������ ��ȸ
		System.out.println("[������ �����ȣ�� �Է��ϼ���]");
		int suIdx = scanner.nextInt();
		
		System.out.println("[������ ������ �Է��ϼ���]");
		String text = scanner.next();
		
		System.out.printf("[%s]�� �����Ͻðڽ��ϱ�?  [1.����   2.���]", text);
		select = scanner.nextInt();
		
		if(select == 1) {
			ReviewVO vo = new ReviewVO(suIdx);
			vo.setReviewText(text);			
			ReviewDao.getInstance().updateReview(vo);
			
			ReviewVO vo2 = new ReviewVO(suIdx,loginInfo);			
			reviewList = ReviewDao.getInstance().selectUpdatedReview(vo2); // ������ �� ��ȸ�ϴ� �޼ҵ�
			
			for(ReviewVO vo3 : reviewList) { // �������� ����ϴ� �޼ҵ�
				System.out.printf("[%d - %d - %s - %s - %s]\n", vo3.getReviewIdx(), vo3.getMovieIdx(),
																vo3.getId(), vo3.getReviewDate(), vo3.getReviewText());
			}
			System.out.println();
			System.out.println("[����]�� �Ϸ�Ǿ����ϴ� Ȩ�������� ���ư��ϴ�.");
			//TODO : �����Ϸ� �� ��ü������ȸ
			mainpage();
		}else if (select == 2) {
			
			System.out.println("Ȩ�������� ���ư��ðڽ��ϱ�? [1.YES  2.NO]");
			select = scanner.nextInt();
			
			if(select==1) {
				mainpage();
			}else if(select==2) {
				System.out.println("[�̿����ּż� �����մϴ�!]");
			}
			
		}
		
	}//updateMyReview() end

	public static void main(String[] args) {
		try {
			new MovieReview();
		} catch (Exception e) {
			System.out.println("[ERROR]�߸��� �Է��Դϴ� Ȩ�������� ���ư��ϴ�.");
			new MovieReview();
		}
	}//main end

}
