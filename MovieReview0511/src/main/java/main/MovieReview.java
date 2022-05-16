package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import MovieDao.MovieDao;
import vo.MovieVO;
import vo.ReviewVO;
import vo.UserVO;
import vo.selectAllVO;

public class MovieReview {

	static int select; //���õǴ� ���� ���� ������ �ʿ� ���� ���⼭ ������ �� �ֽ��ϴ�.
	Scanner scanner = new Scanner(System.in);
	
	//�� VO List
	List<MovieVO>     movieList     = new ArrayList<>();
	List<ReviewVO>    reviewList    = new ArrayList<>();
	List<selectAllVO> selectAllList = new ArrayList<>();
	List<UserVO>      userList      = new ArrayList<>();
	
	
	private int initDisplay() {
		System.out.println("----------[��ȭ���� ����Ʈ�� ���Ű��� ȯ���մϴ�]----------");
		System.out.println("1. �α���");
		System.out.println("2. ����");
		System.out.println("3. ����");
		
		select = scanner.nextInt();

		return select;
	}

	
	
	public MovieReview() {
		
		initDisplay();
		
		do {
			if (select == 1) {
				login();
			} else if (select == 2) {
				signup();
			}
		} while (select != 3);
		
		System.out.println("�̿����ּż� �����մϴ�.");
	}
	
	private void signup() { // TODO: �����
		System.out.println("SIGNUP");
		mainpage();
	}

	private void login() { // TODO: �����
		System.out.println("LOGIN");
		mainpage();
	}

	private void mainpage() {
		System.out.println("�����������Դϴ�");
		System.out.println("1. ��ȭ����");
		System.out.println("2. �ۼ��� ���� ����");
		
		select = scanner.nextInt();

		if (select == 1) {
			selectMovies();
		} else if (select == 2) {
			showMyReviews();
		} else {
			System.out.println("�߸��� �Է��Դϴ�.");
			mainpage();
		}

	}

	private void selectMovies() { // TODO: ��ȭ����(�����)
		
		System.out.println("----------[Select Movies]----------");
		
		movieList = MovieDao.getInstance().select_movie_list();
		
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
			return;
		}
	}

	
	
	private void selectReview() { // TODO: ��ü���� ����(�����)
		
		reviewList = MovieDao.getInstance().select_Review_All();
		
		for(ReviewVO vo : reviewList) {
			System.out.printf("[%d - %d - %s - %s - %s]\n", vo.getReviewIdx(), vo.getMovieIdx(),
															vo.getId(), vo.getReviewDate(), vo.getReviewText());
		}
		System.out.println();
	}

	
	private void insertReview() { // TODO: �����ۼ�(�����)
		
		System.out.println("[��ȭ��ȣ, ���̵�, �ۼ�����(YYYY-MM-DD), ����] �Է��ϼ���>>");
		int    movieIdx   = scanner.nextInt();
		String id         = scanner.next();
		String reviewDate = scanner.next();
		String reviewText = scanner.next();
		
		ReviewVO vo  = new ReviewVO();
		
		vo.setMovieIdx(movieIdx);
		vo.setId(id);
		vo.setReviewDate(reviewDate);
		vo.setReviewText(reviewText);
		
		int res = MovieDao.getInstance().insert_review(vo);
		
		System.out.printf("[%s]�� �������� �Ϸ�Ǿ����ϴ�.\n", id);
		System.out.println("----------[Reviews]----------");
		selectReview();
	}
	
	
	private void showMyReviews() { // TODO: ���� �� ���� �����ϱ��Դϴ�. �����
		System.out.println("----------[Show MyReviews]----------");
		System.out.println("1. ����");
		System.out.println("2. ����");
		System.out.println("3. ���ư���");
		
		select = scanner.nextInt();
		
		if(select == 1) {
			updateMyReview();
		} else if (select == 2) {
			deleteMyReview();
		} else if (select == 3) {
			return;
		}
		
	}

	
	
	private void deleteMyReview() { // TODO: �����
		System.out.println("������ ������ ��ȣ�� �����ϼ���.");
		mainpage();
	}
	
	private void updateMyReview() { // TODO: �����
		System.out.println("������ ������ ��ȣ�� �����ϼ���.");
		
	}
	
	public static void main(String[] args) {
		try {
			new MovieReview();
		} catch (Exception e) {
			System.out.println("�߸��� �Է��Դϴ�.");
			new MovieReview();
		}
	}
}