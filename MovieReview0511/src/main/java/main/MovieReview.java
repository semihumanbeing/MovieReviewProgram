package main;

import java.util.Scanner;

public class MovieReview {

	static int select; // 선택되는 값은 따로 선언할 필요 없이 여기서 설정할 수 있습니다.
	Scanner scanner = new Scanner(System.in);

	private int initDisplay() {
		System.out.println("영화리뷰 사이트에 오신것을 환영합니다");
		System.out.println("1. 로그인하기");
		System.out.println("2. 가입하기");
		System.out.println("3. 종료하기");
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
		System.out.println("안녕히가세요");
	}

	private void signup() { // TODO: 만들기
		System.out.println("signup");
		mainpage();
	}

	private void login() { // TODO: 만들기
		System.out.println("login");
		mainpage();
	}

	private void mainpage() {
		System.out.println("메인페이지입니다");
		System.out.println("1. 영화선택하기");
		System.out.println("2. 내가 쓴 글 보기");
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

	private void selectMovies() { // TODO: 만들기
		System.out.println("selectMovies");
		System.out.println("영화 리스트");
		System.out.println("1. 글쓰기");
		System.out.println("2. 리뷰 보기");
		System.out.println("3. 돌아가기");
		select = scanner.nextInt();
		if(select ==1) {
			insertReview();
		} else if (select ==2) {
			selectReview();
		} else if (select ==3) {
			return;
		}
	}

	private void selectReview() { // TODO: 전체리뷰 선택하기입니다. 만들기
		
		
	}

	private void insertReview() { // TODO: 만들기
		System.out.println("입력완료... 리뷰보기");
		selectReview();
		
	}
	
	private void showMyReviews() { // TODO: 내가 쓴 리뷰 선택하기입니다. 만들기
		System.out.println("showMyReviews");
		System.out.println("1. 수정하기");
		System.out.println("2. 삭제하기");
		System.out.println("3. 돌아가기");
		select = scanner.nextInt();
		
		if(select == 1) {
			updateMyReview();
		} else if (select ==2) {
			deleteMyReview();
		} else if (select ==3) {
			return;
		}
		
	}

	private void deleteMyReview() { // TODO: 만들기
		System.out.println("삭제할 리뷰 번호 선택");
		mainpage();
	}

	private void updateMyReview() { // TODO: 만들기
		System.out.println("수정할 리뷰 번호 선택");
		
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
