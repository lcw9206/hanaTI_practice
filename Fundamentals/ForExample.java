class ForExample {
	public static void main(String[] args) {
		int sum = 0, odd = 0, even = 0;
		for(int i=1; i<=100; i++){
			sum += i;
			if (sum % 2 == 0){
				even += i;
			} else {
				odd += i;
			}
		}
		System.out.println("even" + even);
		System.out.println("odd" + odd);
		System.out.println("sum" + sum);
	}
}
