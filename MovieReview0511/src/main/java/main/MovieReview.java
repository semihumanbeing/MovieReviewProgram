package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import MovieDao.UserDao;
import vo.UserVO;

public class MovieReview {

	static int select; // 선택되는 값은 따로 선언할 필요 없이 여기서 설정할 수 있습니다.
	Scanner scanner = new Scanner(System.in);
	String loginInfo;

	private int initDisplay() {
		System.out.println("영화리뷰 사이트에 오신것을 환영합니다");
		System.out.println("1. 로그인하기");
		System.out.println("2. 가입하기");
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
		System.out.println("잘못된 입력입니다");
		new MovieReview();

	}

	private void signup() { //
		/*
		 * TODO: 류다희 정보입력 이미 등록된 아이디면 로그인하거나 돌아가기 가입하기 취소하면 처음으로 돌아가기
		 */
		List<UserVO> list = UserDao.getInstance().userList();
		List<String> memberList = new ArrayList<String>();
		for (UserVO vo : list) {
			memberList.add(vo.getId());
		}
		scanner.nextLine();
		System.out.println("가입 페이지입니다");
		System.out.println("아이디를 입력하세요");
		String id = scanner.nextLine();

		if (memberList.contains(id)) {
			System.out.println("이미 등록된 아이디입니다.");
			System.out.println("1. 로그인하기");
			System.out.println("2. 돌아가기");
			select = scanner.nextInt();
			if (select == 1) {
				login();
			} else if (select == 2) {
				new MovieReview();
			}
		}

		System.out.println("비밀번호를 입력하세요");
		String pwd = scanner.nextLine();

		System.out.printf("아이디: %s\n비밀번호:%s\n입력하신 정보로 가입하시겠습니까?\n", id, pwd);
		System.out.println("1. 가입하기");
		System.out.println("2. 취소");
		select = scanner.nextInt();

		if (select == 1) {
			UserVO vo = new UserVO(id, pwd);
			UserDao.getInstance().insertSignUp(vo);
			System.out.println("가입완료");
			loginInfo = id;
			mainpage();
		} else if (select == 2) {
			new MovieReview();

		}
		
	}

	public void login() {
		/*
		 * TODO: 류다희 로그인하기 정보입력 아이디입력하면 1. 인덱스에 해당하는 비번가져오기 2. 아이디가없으면 아이디가 틀렸습니다 비번입력
		 * 1. 해당하는 비번이 맞으면 메인페이지로 이동하기 2. 비번이 틀리면 비번이 틀렸습니다
		 */
		scanner.nextLine();

		System.out.println("아이디를 입력하세요");
		String id = scanner.nextLine();
		UserVO vo = new UserVO(id);

		List<UserVO> voList = UserDao.getInstance().checkUserInfo(vo); // 유저정보구하기
		try {
			if (voList.get(0).getId().equals(id)) {
				System.out.println("비밀번호를 입력하세요");
				String pwd = scanner.nextLine();
				if (voList.get(0).getPwd().equals(pwd)) {
					System.out.println("로그인 성공");
					this.loginInfo = voList.get(0).getId();
					mainpage();
				} else {
					System.out.println("일치하는 계정이 없습니다.");
					login();
				}
			}
		} catch (Exception e) {
			System.out.println("일치하는 아이디가 없습니다. 홈페이지로 돌아갑니다...");
			new MovieReview();
		}

	}

	private void mainpage() {

		System.out.printf("%s님 안녕하세요\n", loginInfo);
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
		if (select == 1) {
			insertReview();
		} else if (select == 2) {
			selectReview();
		} else if (select == 3) {
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

		if (select == 1) {
			updateMyReview();
		} else if (select == 2) {
			deleteMyReview();
		} else if (select == 3) {
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
