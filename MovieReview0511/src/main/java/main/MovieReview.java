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

	static int select; // 선택되는 값은 따로 선언할 필요 없이 여기서 설정할 수 있습니다.
	Scanner scanner = new Scanner(System.in);
	String loginInfo;
	
	//각 VO List
	List<MovieVO>        movieList        = new ArrayList<>();
	List<ReviewVO>       reviewList       = new ArrayList<>();
	List<selectAllVO>    selectAllList    = new ArrayList<>();
	List<selectReviewVO> selectReviewList = new ArrayList<>();
	List<UserVO>         userList         = new ArrayList<>();

	
	private int initDisplay() {
		System.out.println("---------------[Welcome MoviewReview SITE]----------------");
		System.out.println("1. 로그인");
		System.out.println("2. 회원가입");
		
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
		System.out.println("[ERROR]잘못된 입력입니다");
		System.out.println();
		new MovieReview();
	}//MovieReview() end

	
	//TODO : 정보입력 이미 등록된 아이디면 로그인하거나 돌아가기 가입하기 취소하면 처음으로 돌아가기(류다희)
	private void signup() { //
		
		List<UserVO> list = UserDao.getInstance().userList();
		List<String> memberList = new ArrayList<String>();
		
		for (UserVO vo : list) {
			memberList.add(vo.getId());
		}
		
		scanner.nextLine();
		
		System.out.println("------------------------[회원가입]------------------------");
		System.out.println("ID:");
		String id = scanner.nextLine();

		if (memberList.contains(id)) {
			System.out.println("[ERROR]이미 등록된 아이디입니다. 1.로그인하기  2.돌아가기");
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
		System.out.println("[입력하신 정보로 가입하시겠습니까? 1.가입  2.취소]");
		select = scanner.nextInt();

		if (select == 1) {
			UserVO vo = new UserVO(id, pwd);
			UserDao.getInstance().insertSignUp(vo);
			System.out.println("[회원가입이 완료되었습니다!]");
			loginInfo = id;
			mainpage();
		} else if (select == 2) {
			new MovieReview();
		}
	}//signup() end

	
	public void login() {
		/*
		 * TODO: 류다희 로그인하기 정보입력 아이디입력하면 1. 인덱스에 해당하는 비번가져오기 2. 아이디가없으면 아이디가 틀렸습니다 비번입력
		 * 1. 해당하는 비번이 맞으면 메인페이지로 이동하기 2. 비번이 틀리면 비번이 틀렸습니다
		 */
		scanner.nextLine();
		
		System.out.println("ID:");
		String id = scanner.nextLine();
		UserVO vo = new UserVO(id);

		List<UserVO> voList = UserDao.getInstance().checkUserInfo(vo); // 유저정보구하기
		try {
			if (voList.get(0).getId().equals(id)) {
				System.out.println("Password:");
				String pwd = scanner.nextLine();
				if (voList.get(0).getPwd().equals(pwd)) {
					this.loginInfo = voList.get(0).getId();
					mainpage();
				}else {
					System.out.println("[ERROR]일치하는 계정이 없습니다.");
					System.out.println("[가입하시겠습니까?  1.YES  2.NO]");
					select = scanner.nextInt();
					
					if(select==1) {
						login();
					}else {
						System.out.println("[이용해주셔서 감사합니다!]");
					}
					
				}
			}
		} catch (Exception e) {
			System.out.println("[ERROR]일치하는 아이디가 없습니다 홈페이지로 돌아갑니다.");
			new MovieReview();
		}
	}//login() end

	
	
	private void mainpage() {
		
		System.out.println();
		System.out.println("-----------------------[MAIN PAGE]-----------------------");
		System.out.println();
		System.out.printf("안녕하세요 [%s]님!\n", loginInfo);
		System.out.println();
		System.out.println("1. 영화선택");
		System.out.println("2. 내 리뷰보기");
		select = scanner.nextInt();

		if (select == 1) {
			selectMovies();
		} else if (select == 2) {
			showMyReviews();
		} else {
			System.out.println("[ERROR]잘못된 입력입니다 홈페이지로 돌아갑니다.");
			mainpage();
		}
	}

	
	private void selectMovies() { // TODO: 영화선택(김다정)
		
		System.out.println("---------------------[Select Movies]--------------------");
		movieList = MovieDao.getInstance().select_movie_list();
		
		System.out.println("[번호  영화제목]");
		
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
			System.out.println("홈페이지로 돌아가시겠습니까? [1.YES  2.NO]");
			select = scanner.nextInt();
			
			if(select==1) {
				mainpage();
			}else if(select==2) {
				System.out.println("[이용해주셔서 감사합니다!]");
			}
		}
	}//selectMovies() end

	
	private void selectReview() { // TODO: 전체리뷰(김다정)
		
		selectReviewList = ReviewDao.getInstance().select_Review_List();
		
		System.out.println("-----------------------[Reviews]------------------------");
		System.out.println("[리뷰번호    영화번호    영화제목    닉네임    작성내용    작성일자]");
		System.out.println();
		
		for(selectReviewVO vo : selectReviewList) {
			System.out.printf("[%d - %d - %s - %s - %s - %s]\n", vo.getReviewIdx(), vo.getMoviewIdx(), vo.getMovieTitle(), vo.getUserId(),
														         vo.getReviewText(), vo.getReviewDate());
		}
		
		System.out.println("-------------------------------------------------------");
		System.out.println("1. 리뷰작성");
		System.out.println("2. 돌아가기");
		
		select = scanner.nextInt();
		
		if(select == 1) {
			insertReview();
		} else if (select == 2) {
			System.out.println("홈페이지로 돌아가시겠습니까? [1.YES  2.NO]");
			select = scanner.nextInt();
			
			if(select==1) {
				mainpage();
			}else if(select==2) {
				System.out.println("[이용해주셔서 감사합니다!]");
			}
		}
	}//selectReview() end
	
	
	
	private void insertReview() { // TODO: 리뷰작성(김다정) -> 영화제목까지 보이게 하기 movieTitle
		
		System.out.println();
		System.out.println("영화번호선택:");
		int movieIdx = scanner.nextInt();
		
		String id = loginInfo;
		
		System.out.println();
		System.out.println("리뷰입력:");
		String reviewText = scanner.next();
		
		ReviewVO vo  = new ReviewVO();

		vo.setMovieIdx(movieIdx);
		vo.setId(id);
		vo.setReviewText(reviewText);
		
		int res = ReviewDao.getInstance().insert_review(vo);
		
		System.out.println();
		System.out.printf("[%s]님 리뷰등록이 완료되었습니다.\n", id);
		
		System.out.println();
		//selectReview();
		
		selectReviewList = ReviewDao.getInstance().select_Review_List();
		
		System.out.println("----------------------[Review List]----------------------");
		System.out.println("[리뷰번호  영화번호  영화제목  닉네임  작성내용  작성일자]");
		System.out.println();
		
		for(selectReviewVO vo1 : selectReviewList) {
			System.out.printf("[%d - %d - %s - %s - %s - %s]\n", vo1.getReviewIdx(), vo1.getMoviewIdx(), vo1.getMovieTitle(), vo1.getUserId(),
														         vo1.getReviewText(), vo1.getReviewDate());
		}//for end
		
		System.out.println();
		System.out.println("홈페이지로 돌아가시겠습니까? [1.YES  2.NO]");
		select = scanner.nextInt();
		
		if(select==1) {
			mainpage();
		}else {
			System.out.println("[이용해주셔서 감사합니다!]");
		}
	}//insertReivew() end

	
	
	private void showMyReviews() { // TODO: 내 리뷰확인하기 넣기(최규범)
		
		//selectReviewVO srv = new selectReviewVO(loginInfo);
		selectReviewList   = ReviewDao.getInstance().showMyReview(loginInfo);
		
		System.out.println("--------------------[My Review List]-------------------");
		System.out.println("[리뷰번호  영화번호  영화제목  닉네임  작성내용  작성일자]");
		for(selectReviewVO vo : selectReviewList) {
			System.out.printf("[%d - %d - %s - %s - %s]\n", vo.getReviewIdx(), vo.getMoviewIdx(), vo.getMovieTitle(),
														    vo.getReviewText(), vo.getReviewDate());
		}
		
		System.out.println("-------------------------------------------------------");
		
		System.out.println("1. 리뷰등록");
		System.out.println("2. 리뷰수정");
		System.out.println("3. 리뷰삭제");
		System.out.println("4. 돌아가기");
		
		select = scanner.nextInt();
		
		if(select==1) {
			insertReview();
		}else if(select==2) {
			updateMyReview();
		} else if (select==3) {
			deleteMyReview();
		} else if (select==4) {
			System.out.println("홈페이지로 돌아가시겠습니까? [1.YES  2.NO]");
			select = scanner.nextInt();
			
			if(select==1) {
				mainpage();
			}else if(select==2) {
				System.out.println("[이용해주셔서 감사합니다!]");
			}
		}
	}//showMyReview() end

	
	private void deleteMyReview() { // TODO: 최규범 삭제 전, 해당 id의 리뷰목록 조회
		
		ReviewVO vo = new ReviewVO(loginInfo);

		reviewList = ReviewDao.getInstance().select_Review_My(vo);

		for (ReviewVO vo2 : reviewList) {
			System.out.printf("[%d - %d - %s - %s - %s]\n", vo2.getReviewIdx(), vo2.getMovieIdx(), vo2.getId(),
					vo2.getReviewDate(), vo2.getReviewText());
		}
		
		
		System.out.println("삭제할 리뷰번호를 입력하세요.");
		int deleteReview = scanner.nextInt();
		
		System.out.println("정말 삭제하시겠습니까?");
		System.out.println("1. 리뷰삭제");
		System.out.println("2. 돌아가기");
		
		select = scanner.nextInt();
		
		if (select == 1) {
			ReviewVO vo3 = new ReviewVO(deleteReview);
			ReviewDao.getInstance().DeleteReview(vo3);
			selectReviewList = ReviewDao.getInstance().select_Review_List();
			
			System.out.println("----------------------[Review List]----------------------");
			System.out.println("[리뷰번호  영화번호  영화제목  닉네임  작성내용  작성일자]");
			System.out.println();
			
			for(selectReviewVO vo1 : selectReviewList) {
				System.out.printf("[%d - %d - %s - %s - %s - %s]\n", vo1.getReviewIdx(), vo1.getMoviewIdx(), vo1.getMovieTitle(), vo1.getUserId(),
															         vo1.getReviewText(), vo1.getReviewDate());
			}
			System.out.println();
			System.out.print("[삭제]가 완료되었습니다 홈페이지로 돌아가시겠습니까?  [1.YES  2.NO]");
			select = scanner.nextInt();
			
			if(select==1) {
				mainpage();
			}else if(select==2) {
				System.out.println("[이용해주셔서 감사합니다!]");
			}
			//TODO : 삭제 후 전체리뷰조회
			
		} else if (select == 2) {
			
			System.out.println("홈페이지로 돌아가시겠습니까? [1.YES  2.NO]");
			select = scanner.nextInt();
			
			if(select==1) {
				mainpage();
			}else if(select==2) {
				System.out.println("[이용해주셔서 감사합니다!]");
			}
		}
	}//deleteMyReview() end

	
	
	private void updateMyReview() { // TODO: 최규범 수정 전, 해당 id의 리뷰목록 조회
		System.out.println("[수정할 리뷰번호를 입력하세요]");
		int suIdx = scanner.nextInt();
		
		System.out.println("[수정할 내용을 입력하세요]");
		String text = scanner.next();
		
		System.out.printf("[%s]로 수정하시겠습니까?  [1.수정   2.취소]", text);
		select = scanner.nextInt();
		
		if(select == 1) {
			ReviewVO vo = new ReviewVO(suIdx);
			vo.setReviewText(text);			
			ReviewDao.getInstance().updateReview(vo);
			
			ReviewVO vo2 = new ReviewVO(suIdx,loginInfo);			
			reviewList = ReviewDao.getInstance().selectUpdatedReview(vo2); // 수정한 것 조회하는 메소드
			
			for(ReviewVO vo3 : reviewList) { // 수정내용 출력하는 메소드
				System.out.printf("[%d - %d - %s - %s - %s]\n", vo3.getReviewIdx(), vo3.getMovieIdx(),
																vo3.getId(), vo3.getReviewDate(), vo3.getReviewText());
			}
			System.out.println();
			System.out.println("[수정]이 완료되었습니다 홈페이지로 돌아갑니다.");
			//TODO : 수정완료 후 전체리뷰조회
			mainpage();
		}else if (select == 2) {
			
			System.out.println("홈페이지로 돌아가시겠습니까? [1.YES  2.NO]");
			select = scanner.nextInt();
			
			if(select==1) {
				mainpage();
			}else if(select==2) {
				System.out.println("[이용해주셔서 감사합니다!]");
			}
			
		}
		
	}//updateMyReview() end

	public static void main(String[] args) {
		try {
			new MovieReview();
		} catch (Exception e) {
			System.out.println("[ERROR]잘못된 입력입니다 홈페이지로 돌아갑니다.");
			new MovieReview();
		}
	}//main end

}
