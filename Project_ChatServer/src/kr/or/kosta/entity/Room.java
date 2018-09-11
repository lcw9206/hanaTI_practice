package kr.or.kosta.entity;

import kr.or.kosta.boundary.ChatServer;

public class Room {

	private String roomTitle;
	private int roomNumber = 0;
	private int maxPeople;
	private int joinPeople;
	
	ChatServer chatServer;
	
	// Default 생성자
	public Room(String roomTitle, int maxPeople) {
		this.roomTitle = roomTitle;
		this.maxPeople = maxPeople;
		roomNumber++;
		// 생성 = 접속이므로 1
		joinPeople = 1;
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

}
