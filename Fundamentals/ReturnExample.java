class ReturnExample {
	public static void main(String[] args) {
		System.out.println("프로그램 시작");
		for (int i=0; i<100 ; i++){
			System.out.println("파이팅!");
			if(i == 50){
				return ;

			}	
		}
		System.out.println("프로그램 종료");
	}
}
