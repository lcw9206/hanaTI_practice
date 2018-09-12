package kr.or.kosta.entity;

import java.util.List;
import java.util.Vector;

import kr.or.kosta.bin.Client;
import kr.or.kosta.boundary.ChatServer;

public class Room {

	private String roomTitle, nickName;
	private int roomNumber;
	private int maxPeople;
	// 리스트에 담아서 
	Vector<Client> clientsList = new Vector<Client>();
	ChatServer chatServer;
	
	// Default 생성자
	public Room(String nickName, String roomTitle, int maxPeople) {
		this.nickName = nickName;
		this.roomTitle = roomTitle;
		this.maxPeople = maxPeople;
		roomNumber++;
	}
	
	public int getClientsCount() {
		return clientsList.size();
	}
	
	public List getClientsList() {
		return (List)clientsList;
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

	public boolean isEnterable( ) {
		return maxPeople > getClientsCount(); 
	}

	public void addClientsList(Client client) {
		clientsList.add(client);
	}
	
	public void removeClientsList(Client client) {
		clientsList.remove(client);
	}

}
