import java.util.Scanner;

class IfExample {
	public static void main(String[] args) {
		int score = 99;
		if(score < 65){
			System.out.println("Fail");
		} else {
			System.out.println("Success");
		}
		if (score % 2 == 0){
			System.out.println("짝수");
		} else {
			System.out.println("홀수");
		}
		
		// score 입력
		System.out.print("점수 : ");
		Scanner sc = new Scanner(System.in);
		score = sc.nextInt();

		if(score >=90) {
			System.out.println("수");
		} else if(score >= 80){
			System.out.println("우");
		} else if(score >= 70){
			System.out.println("미");
		} else if(score >= 60){
			System.out.println("양");
		} else {
			System.out.println("가");
		}
	}
}