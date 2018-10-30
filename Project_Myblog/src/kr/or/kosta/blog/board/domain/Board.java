package kr.or.kosta.blog.board.domain;

public class Board {
	
	private int board_id;
	private int category;
	private String title;
	private String description;
	
	public Board() {}
	
	public Board(int board_id, int category, String title, String description) {
		super();
		this.board_id = board_id;
		this.category = category;
		this.title = title;
		this.description = description;
	}

	public int getBoard_id() {
		return board_id;
	}

	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Post [board_id=" + board_id + ", category=" + category + ", title=" + title + ", description="
				+ description + "]";
	}
	
}