package kr.or.kosta.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import kr.or.kosta.bin.Client;
import kr.or.kosta.boundary.ChatServer;

public class Room {

	private String roomTitle, nickName;
	private int roomNumber;
	private int maxPeople;
	private Hashtable<String, Client> clientsList = new Hashtable<String, Client>();
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
	
	public List<Client> getClientsList() {
//		List<Client> list = new ArrayList();
//		list.addAll(clientsList.values()); 
//		return list;
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

	public boolean isEnterable( ) {
		return maxPeople > getClientsCount(); 
	}

	public void addClientsList(Client client) {
		System.out.println("추가될 사람 : " + client.getNickName());
		List<Client> clList = this.getClientsList();
		Client cl;
		System.out.println("-------------- 추가 전 들어있는 사람 ---------------");
		for (int i = 0; i < clList.size(); i++) {
			cl = (Client)clList.get(i);
			System.out.print(cl.getNickName());
		}
		System.out.println("\n-------------------------");
		clientsList.put(client.getNickName(), client);
		List<Client> clList1 = getClientsList();
		System.out.println("-------------- 추가후 들어있는 사람 ---------------");
		for (int i = 0; i < clList1.size(); i++) {
			cl = (Client)clList1.get(i);
			System.out.print(cl.getNickName());
		}
		System.out.println("\n-------------------------");
	}
	
	public void removeClientsList(Client client) {
		clientsList.remove(client.getNickName());
	}

}
