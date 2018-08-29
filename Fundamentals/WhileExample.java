class WhileExample {
	public static void main(String[] args) {
		int i = 0;

		while(i < 10){
			System.out.println("ÄÚ½ºÅ¸ 187±â");
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
		System.out.println("ÃÑ ÇÕ°è : " + sum);
		System.out.println("È¦¼ö ÇÕ°è : " + hol);
		System.out.println("Â¦¼ö ÇÕ°è : " + jjak);
	}
}
