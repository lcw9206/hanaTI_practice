
/**
 * 인터페이스 예제
 * 
 * @author 이철우
 *
 */
public class Unit {
	// Unit과 Weapon이 has-a 관계이므로 Unit에 무조건 Weapon을 생성해줘야한다.
	// name을 생성자로 생성하지말고 getter, setter를 이용해 필요할 때 생성한다.
	private Weapon weapon;

	private String name;

	public Unit() {
	}

	public Unit(String name) {
		this.name = name;
	}

	// 집합 관계일 경우, weapon을 매개변수로 넣어 생성자에서 초기화 해줘야 한다.
	/*
	 * public Unit(String name, Weapon weapon) { this.name = name; this.weapon =
	 * weapon; }
	 */

	public Weapon getWeapon() {
		return weapon;
	}

	public int getWEIGHT() {
		return weapon.WEIGHT;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void attack() {
		weapon.attack();
	}

	public static void main(String[] args) {
		Unit unit = new Unit("SCV");
		// 무기 전달
		Weapon weapon = null;
		// 업캐스팅
		weapon = new Gun();

		unit.setWeapon(weapon);
		unit.attack();

		weapon = new Sword();
		unit.attack();
	}
}
