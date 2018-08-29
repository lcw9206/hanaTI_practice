class Gugudan {
	static void printGugudan(int num){
		for (int i = 1; i < 10; i++){
			for (int j = 2; j <= num; j++){
				System.out.print(j + "*" + i + "=" + j * i + "|" + "\t");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		printGugudan(6);
	}
}
