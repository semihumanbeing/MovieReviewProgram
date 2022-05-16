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

	static int select; //선택되는 값은 따로 선언할 필요 없이 여기서 설정할 수 있습니다.
	Scanner scanner = new Scanner(System.in);
	
	//각 VO List
	List<MovieVO>     movieList     = new ArrayList<>();
	List<ReviewVO>    reviewList    = new ArrayList<>();
	List<selectAllVO> selectAllList = new ArrayList<>();
	List<UserVO>      userList      = new ArrayList<>();
	
	
	private int initDisplay() {
		System.out.println("----------[영화리뷰 사이트에 오신것을 환영합니다]----------");
		System.out.println("1. 로그인");
		System.out.println("2. 가입");
		System.out.println("3. 종료");
		
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
		
		System.out.println("이용해주셔서 감사합니다.");
	}
	
	private void signup() { // TODO: 만들기
		System.out.println("SIGNUP");
		mainpage();
	}

	private void login() { // TODO: 만들기
		System.out.println("LOGIN");
		mainpage();
	}

	private void mainpage() {
		System.out.println("메인페이지입니다");
		System.out.println("1. 영화선택");
		System.out.println("2. 작성한 리뷰 보기");
		
		select = scanner.nextInt();

		if (select == 1) {
			selectMovies();
		} else if (select == 2) {
			showMyReviews();
		} else {
			System.out.println("잘못된 입력입니다.");
			mainpage();
		}

	}

	private void selectMovies() { // TODO: 영화선택(김다정)
		
		System.out.println("----------[Select Movies]----------");
		
		movieList = MovieDao.getInstance().select_movie_list();
		
		for(MovieVO vo : movieList) {
			System.out.printf("[%d - %s]\n", vo.getMovieIdx(), vo.getMovieTitle());
		}
		System.out.println(); //줄 띄우기
		
		System.out.println("1. 리뷰작성");
		System.out.println("2. 리뷰보기");
		System.out.println("3. 돌아가기");
		
		select = scanner.nextInt();
		
		if(select == 1) {
			insertReview();
		} else if (select == 2) {
			selectReview();
		} else if (select == 3) {
			return;
		}
	}

	
	
	private void selectReview() { // TODO: 전체리뷰 선택(김다정)
		
		reviewList = MovieDao.getInstance().select_Review_All();
		
		for(ReviewVO vo : reviewList) {
			System.out.printf("[%d - %d - %s - %s - %s]\n", vo.getReviewIdx(), vo.getMovieIdx(),
															vo.getId(), vo.getReviewDate(), vo.getReviewText());
		}
		System.out.println();
	}

	
	private void insertReview() { // TODO: 리뷰작성(김다정)
		
		System.out.println("[영화번호, 아이디, 작성일자(YYYY-MM-DD), 리뷰] 입력하세요>>");
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
		
		System.out.printf("[%s]님 리뷰등록이 완료되었습니다.\n", id);
		System.out.println("----------[Reviews]----------");
		selectReview();
	}
	
	
	private void showMyReviews() { // TODO: 내가 쓴 리뷰 선택하기입니다. 만들기
		System.out.println("----------[Show MyReviews]----------");
		System.out.println("1. 수정");
		System.out.println("2. 삭제");
		System.out.println("3. 돌아가기");
		
		select = scanner.nextInt();
		
		if(select == 1) {
			updateMyReview();
		} else if (select == 2) {
			deleteMyReview();
		} else if (select == 3) {
			return;
		}
		
	}

	
	
	private void deleteMyReview() { // TODO: 만들기
		System.out.println("삭제할 리뷰의 번호를 선택하세요.");
		mainpage();
	}
	
	private void updateMyReview() { // TODO: 만들기
		System.out.println("수정할 리뷰의 번호를 선택하세요.");
		
	}
	
	public static void main(String[] args) {
		try {
			new MovieReview();
		} catch (Exception e) {
			System.out.println("잘못된 입력입니다.");
			new MovieReview();
		}
	}
}