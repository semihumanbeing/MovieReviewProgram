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

public class MovieReview {

	static int select; // 선택되는 값은 따로 선언할 필요 없이 여기서 설정할 수 있습니다.
	Scanner scanner = new Scanner(System.in);
	String loginInfo;
	
	//각 VO List
	List<MovieVO>     movieList     = new ArrayList<>();
	List<ReviewVO>    reviewList    = new ArrayList<>();
	List<selectAllVO> selectAllList = new ArrayList<>();
	List<UserVO>      userList      = new ArrayList<>();

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
					
					System.out.println("1. 돌아가기");
					select = scanner.nextInt();
					
					if(select == 1) {
						login();
					}
					
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
			mainpage();
		}
	}//selectMovies() end

	
	private void selectReview() { // TODO: 전체리뷰선택(김다정)
		
		reviewList = ReviewDao.getInstance().select_Review_All();
		
		for(ReviewVO vo : reviewList) {
			System.out.printf("[%d - %d - %s - %s - %s]\n", vo.getReviewIdx(), vo.getMovieIdx(),
															vo.getId(), vo.getReviewDate(), vo.getReviewText());
		}

		System.out.println("1. 리뷰작성");
		System.out.println("2. 돌아가기");
		
		select = scanner.nextInt();
		
		if(select == 1) {
			insertReview();
		} else if (select == 2) {
			mainpage();
		}
		
	}//selectReview() end
	
	
	
	private void insertReview() { // TODO: 리뷰작성(김다정) -> 영화제목까지 보이게 하기 movieTitle
		
		System.out.println("영화번호 선택>>");
		int movieIdx = scanner.nextInt();
		
		String id = loginInfo;
		
		System.out.println("[리뷰] 입력하세요>>");
		String reviewText = scanner.next();
		
		ReviewVO vo  = new ReviewVO();

		vo.setMovieIdx(movieIdx);
		vo.setId(id);
		vo.setReviewText(reviewText);
		
		int res = ReviewDao.getInstance().insert_review(vo);
		
		System.out.printf("[%s]님 리뷰등록이 완료되었습니다.\n", id);
		System.out.println("----------[Reviews]----------");
		selectReview();
		mainpage();
	}//insertReivew() end

	
	
	private void showMyReviews() { // TODO: 내 리뷰확인하기 넣기(최규범)
		
		
		//규범님 확인
		reviewList = ReviewDao.getInstance().select_Review_All();
		
		for(ReviewVO vo : reviewList) {
			System.out.printf("[%d - %d - %s - %s - %s]\n", vo.getReviewIdx(), vo.getMovieIdx(),
															vo.getId(), vo.getReviewDate(), vo.getReviewText());
		}
		System.out.println();
		
		
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
			mainpage();
		}

	}

	private void deleteMyReview() { // TODO: 최규범 삭제 전, 해당 id의 리뷰목록 조회
		System.out.println("삭제할 리뷰 번호 선택");
		int deleteReview = scanner.nextInt();
		
		System.out.println("정말 삭제하시겠습니까?\n1.리뷰삭제\n2.돌아가기\n");
		
		select = scanner.nextInt();
		
		if (select == 1) {
			ReviewVO vo = new ReviewVO(deleteReview);
			ReviewDao.getInstance().DeleteReview(vo);
			System.out.println("------------------------------------");
			System.out.print("삭제가 완료되었습니다. 감사합니다.");
			
			//TODO : 삭제 후 전체리뷰조회
			
			mainpage();
			
		} else if (select == 2) {
			mainpage();
		}
	}//deleteMyReview() end

	private void updateMyReview() { // TODO: 최규범 수정 전, 해당 id의 리뷰목록 조회
		System.out.println("수정할 리뷰 번호 선택");
		select = scanner.nextInt();
		
		System.out.println("수정할 내용을 입력해주세요.");
		String text = scanner.next();
		
		System.out.println(text);
		System.out.println("수정 하시겠습니까?[1.완료 2.취소]");
		
		int suIdx = scanner.nextInt();
		
		if(suIdx == 1) {
			ReviewVO vo = new ReviewVO();
			ReviewDao.getInstance().updateReview(vo);
			System.out.println("수정이 완료되었습니다. 감사합니다.");
			//TODO : 수정완료 후 전체리뷰조회
			mainpage();
		}else if (suIdx == 2) {
			mainpage();
		}
		
	}//updateMyReview() end

	public static void main(String[] args) {
		try {
			new MovieReview();
		} catch (Exception e) {
			System.out.println("잘못된 입력입니다.");
			new MovieReview();
		}
	}//main end

}
