class OperatorExample {
	public static void main(String[] args) {
		int x = 50, y = 20;
		
		// ���������
		System.out.println("��� : " + x + y);
		System.out.println("������ : " + (x % y));

		// ���մ��Կ�����
		x += y;
		System.out.println(x);

		// ����ȯ������
		double weight = 78.34343;
		System.out.println((int)weight);

		// ��Ʈ������
		// ��Ʈ�����ڷ� �����ϴ� ���� �� ������.
		x = 10;
		System.out.println(x * 2 * 2 * 2);
		System.out.println(x << 3);

		// ���ǻ��׿�����
		int a=2, b=3, c=1, max;
		max = (a>b) ? a : b;
		max = (b>c) ? b : c;
		System.out.println("max : " + max);
	}
}
