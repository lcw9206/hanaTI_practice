class ForMission {
	public static void main(String[] args) {
		
		// 1
		for (int i = 0; i < 5; i++){
			for (int j = 0; j <= i; j++){
				System.out.print("*");
			}
			System.out.println(" ");
		}
		
		System.out.println(" ");

		// 2
		for (int i = 0; i < 5; i++){
			for (int j = 5; j > i; j--){
				System.out.print("*");
			}
			System.out.println(" ");
		}
		
		System.out.println(" ");

		// 3
		for (int i = 0; i < 5; i++){
			for (int j = 5; j > i; j--){
				System.out.print(" ");
			}
			for (int k = 1; k <= i; k++){
			System.out.print("*");
			}
			System.out.println("");
		}

		System.out.println(" ");

		// 4
		for (int i = 0; i < 5; i++){
			for (int j = 0; j < i; j++){
				System.out.print(" ");
			}
			for (int k = 5; k > i; k--){
			System.out.print("*");
			}
			System.out.println("");
		}

		System.out.println(" ");

		// 5
		for (int i = 0; i < 5; i++){
			for (int j = 0; j < (5 / i); j++){
				System.out.print(" ");
			}
			for (int k = 0; k <= i; k++){
			System.out.print("*");
			}
			for (int j = 0; j < (5 / i); j++){
				System.out.print(" ");
			}
			System.out.println("");
		}
	}
}
