package vo;

public class ReviewVO {
	int reviewIdx;
	int movieIdx;
	String movieTitle;
	String id;
	String reviewDate;
	String reviewText;
	
	public ReviewVO() {
	
	}
	public ReviewVO(int reviewIdx, String id) {
		super();
		this.reviewIdx = reviewIdx;
		this.id = id;
	}
	public ReviewVO(int updateReview) {
		this.reviewIdx = updateReview;
	}
	public ReviewVO(String loginInfo) {
		// TODO Auto-generated constructor stub
	}
	public int getReviewIdx() {
		return reviewIdx;
	}
	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}
	public void setReviewIdx(int reviewIdx) {
		this.reviewIdx = reviewIdx;
	}
	public int getMovieIdx() {
		return movieIdx;
	}
	public void setMovieIdx(int movieIdx) {
		this.movieIdx = movieIdx;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getReviewDate() {
		return reviewDate;
	}
	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}
	public String getReviewText() {
		return reviewText;
	}
	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}
	
	

}

