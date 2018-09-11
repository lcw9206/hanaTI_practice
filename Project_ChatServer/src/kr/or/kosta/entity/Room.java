package kr.or.kosta.entity;

import kr.or.kosta.boundary.ChatServer;

public class Room {

	private String roomTitle, nickName;
	private int roomNumber;
	private int maxPeople;
	
	ChatServer chatServer;
	
	// Default 생성자
	public Room(String nickName, String roomTitle, int maxPeople) {
		this.nickName = nickName;
		this.roomTitle = roomTitle;
		this.maxPeople = maxPeople;
		roomNumber++;
	}
	
//	public int getClientsCount(String roomTitle) {
//		return joinList.get(roomTitle).length();
//	}
	
	
	public int getRoomNumber() {
		return roomNumber;
	}
	
	public String getRoomTitle() {
		return roomTitle;
	}
	public int getMaxPeople() {
		return maxPeople;
	}

}
