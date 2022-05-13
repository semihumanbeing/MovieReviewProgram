package main;

import java.util.Scanner;

public class MovieReview {

	static int select; // ���õǴ� ���� ���� ������ �ʿ� ���� ���⼭ ������ �� �ֽ��ϴ�.
	Scanner scanner = new Scanner(System.in);

	private int initDisplay() {
		System.out.println("��ȭ���� ����Ʈ�� ���Ű��� ȯ���մϴ�");
		System.out.println("1. �α����ϱ�");
		System.out.println("2. �����ϱ�");
		System.out.println("3. �����ϱ�");
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
		System.out.println("�ȳ���������");
	}

	private void signup() { // TODO: �����
		System.out.println("signup");
		mainpage();
	}

	private void login() { // TODO: �����
		System.out.println("login");
		mainpage();
	}

	private void mainpage() {
		System.out.println("�����������Դϴ�");
		System.out.println("1. ��ȭ�����ϱ�");
		System.out.println("2. ���� �� �� ����");
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

	private void selectMovies() { // TODO: �����
		System.out.println("selectMovies");
		System.out.println("��ȭ ����Ʈ");
		System.out.println("1. �۾���");
		System.out.println("2. ���� ����");
		System.out.println("3. ���ư���");
		select = scanner.nextInt();
		if(select ==1) {
			insertReview();
		} else if (select ==2) {
			selectReview();
		} else if (select ==3) {
			return;
		}
	}

	private void selectReview() { // TODO: ��ü���� �����ϱ��Դϴ�. �����
		
		
	}

	private void insertReview() { // TODO: �����
		System.out.println("�Է¿Ϸ�... ���亸��");
		selectReview();
		
	}
	
	private void showMyReviews() { // TODO: ���� �� ���� �����ϱ��Դϴ�. �����
		System.out.println("showMyReviews");
		System.out.println("1. �����ϱ�");
		System.out.println("2. �����ϱ�");
		System.out.println("3. ���ư���");
		select = scanner.nextInt();
		
		if(select == 1) {
			updateMyReview();
		} else if (select ==2) {
			deleteMyReview();
		} else if (select ==3) {
			return;
		}
		
	}

	private void deleteMyReview() { // TODO: �����
		System.out.println("������ ���� ��ȣ ����");
		mainpage();
	}

	private void updateMyReview() { // TODO: �����
		System.out.println("������ ���� ��ȣ ����");
		
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
