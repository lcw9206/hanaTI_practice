
/**
 * 다중구현을 이용한 클래스 정의
 * 
 * @author 이철우
 *
 */
public class SamsongTV implements RemoteControl, Browsable {

	private int currentChannel;
	private int currentVolume;

	public SamsongTV() {
		super();
	}

	public SamsongTV(int currentChannel, int currentVolume) {
		super();
		this.currentChannel = currentChannel;
		this.currentVolume = currentVolume;
	}

	public int getCurrentChannel() {
		return currentChannel;
	}

	public void setCurrentChannel(int currentChannel) {
		this.currentChannel = currentChannel;
	}

	public int getCurrentVolume() {
		return currentVolume;
	}

	public void setCurrentVolume(int currentVolume) {
		this.currentVolume = currentVolume;
	}

	@Override
	public void surfing(String url) {
		System.out.println(url + "을 화면에 랜더링합니다..");

	}

	@Override
	public void turnOn() {
		System.out.println("전원을 켭니다..");

	}

	@Override
	public void turnOff() {
		System.out.println("전원을 끕니다..");

	}

	@Override
	public void volumeUp() {
		if (currentVolume < MAX_VOLUME) {
			currentVolume++;
		} else {
			System.out.println("MAX 볼륨입니다..");
		}
	}

	@Override
	public void volumeDown() {
		currentVolume--;
	}

	@Override
	public void setVolume(int volume) {
		currentVolume = volume;
	}

	@Override
	public void setChannel(int channel) {
		currentChannel = channel;
	}

	public static void main(String[] args) {
		// 인터페이스를 부모 타입으로 생성해 유연성을 높인다.
		// 업
		RemoteControl tv = new SamsongTV();
		tv.turnOn();
		tv.setChannel(15);
		// 다운 캐스팅이 필요하다. 새로 추가된 메서드이므로 RemoteControl 타입에는 존재하지 않음.
		// SamsongTV tv1 = (SamsongTV)tv;
		// tv1.getCurrentChannel();
		System.out.println(((SamsongTV) tv).getCurrentChannel());
		tv.volumeUp();
		tv.volumeUp();
		tv.volumeUp();
		tv.volumeUp();
		tv.volumeUp();
		tv.volumeDown();

		tv.turnOff();

	}

}
