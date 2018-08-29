class BreakExample {
	public static void main(String[] args) {
		for (int i=0; i<10; i++){
			if (i == 5){
				break;
			}
			System.out.println("免仿" + i);
		}

		规弊府:
		for (int i=0; i<10; i++){
			for (int j=0; j<10; j++){
				System.out.println(i + "," + j);
				if(j==5){
					break 规弊府;
				}
			}
		}
	}
}
