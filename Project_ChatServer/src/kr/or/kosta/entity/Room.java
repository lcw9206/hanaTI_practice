package kr.or.kosta.entity;

import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import kr.or.kosta.bin.Client;
import kr.or.kosta.boundary.ChatServer;

/**
 * 방 정보를 관리하는 클래스
 * 
 * @author 이철우
 *
 */
public class Room {

	private String roomTitle, nickName;
	private int roomNumber;
	private int maxPeople;

	/** 방 입장 인원 관리를 위한 Hashtable */
	private Hashtable<String, Client> clientsList = new Hashtable<String, Client>();
	ChatServer chatServer;

	/** 생성자 */
	public Room(String nickName, String roomTitle, int maxPeople) {
		this.nickName = nickName;
		this.roomTitle = roomTitle;
		this.maxPeople = maxPeople;
		roomNumber++;
	}

	/** Getter 메서드 */
	public int getClientsCount() {
		return clientsList.size();
	}

	public List<Client> getClientsList() {
		return Collections.list(Collections.enumeration(clientsList.values()));
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public String getRoomTitle() {
		return roomTitle;
	}

	public int getMaxPeople() {
		return maxPeople;
	}

	public String getNickName() {
		return nickName;
	}

	/** 방의 입장 여부를 판단하는 메서드 */
	public boolean isEnterable() {
		return maxPeople > getClientsCount();
	}

	/** 입장 인원 추가 메서드 */
	public void addClientsList(Client client) {
		clientsList.put(client.getNickName(), client);
	}

	/** 입장 인원 제거 메서드 */
	public void removeClientsList(Client client) {
		clientsList.remove(client.getNickName());
	}

}
