class WhileExample {
	public static void main(String[] args) {
		int i = 0;

		while(i < 10){
			System.out.println("�ڽ�Ÿ 187��");
			i++;
		}

		i = 0;
		int sum = 0, hol = 0, jjak = 0;
	
		while(i <= 100){
			sum += i;
			if(i % 2 == 0){
				jjak += i;
			} else {
				hol += i;
			}
			i++;
		}
		System.out.println("�� �հ� : " + sum);
		System.out.println("Ȧ�� �հ� : " + hol);
		System.out.println("¦�� �հ� : " + jjak);
	}
}
