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
			System.out.println("¦��");
		} else {
			System.out.println("Ȧ��");
		}
		
		// score �Է�
		System.out.print("���� : ");
		Scanner sc = new Scanner(System.in);
		score = sc.nextInt();

		if(score >=90) {
			System.out.println("��");
		} else if(score >= 80){
			System.out.println("��");
		} else if(score >= 70){
			System.out.println("��");
		} else if(score >= 60){
			System.out.println("��");
		} else {
			System.out.println("��");
		}
	}
}