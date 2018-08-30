import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapExample {

	public static void main(String[] args) {
		// Key, Value로 구성된 Map
		Map<String, String> map = new HashMap<>();
		map.put("680313", "김기정");
		map.put("920619", "콩콩");
		map.put("620313", "킹기정");
		map.put("640313", "갓기정");
		
		map.containsKey("640413");
		
		// 아래와 같이 중복 시, 덮어버린다. 이를 방지하고자 map.containsKey를 
		map.put("640313", "콩기정");
		
		System.out.println(map.get("640313"));
		
		// Map의 key List 출력 
		Set<String> keyList = map.keySet();
		for (String string : keyList) {
			System.out.println(string);
		}

		// Map의 value List 출력
		Collection<String> valueList = map.values();
		for (String string : valueList) {
			System.out.println(string);
		}
	}

}
