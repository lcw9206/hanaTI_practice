class MethodExample {

	static void printMessage(String name, String message){
		System.out.println("["+ name +"] : " + message);
	}

	static int sum(int x, int y, int z){
		return x + y + z;
	}


	public static void main(String[] args) {
		String name = "방그리";
		String message = "4시 23분";
		printMessage(name, message);
			}
}
