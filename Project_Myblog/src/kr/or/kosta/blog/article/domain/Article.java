package kr.or.kosta.blog.article.domain;

public class Article {
	
	private int article_id;
	private int board_id;
	private String writer;
	private String subject;
	private String content;
	private String regdate;
	private int hitCount;
	private String ip;
	private String passwd;
	private int group_no;
	private int level_no;
	private int order_no;
	

	public Article() {}
	
	public Article(int article_id) {
		super();
		this.article_id = article_id;
	}

	public Article(String writer, String subject, String content,
			String ip, String passwd) {
		super();
		this.writer = writer;
		this.subject = subject;
		this.content = content;
		this.ip = ip;
		this.passwd = passwd;
	}
	
	public Article(int article_id, String writer, String subject, String content,
			String ip, String passwd) {
		super();
		this.article_id = article_id;
		this.writer = writer;
		this.subject = subject;
		this.content = content;
		this.ip = ip;
		this.passwd = passwd;
	}
	
	public Article(int board_id, String writer, String subject, String content,
			String ip, String passwd, int group_no, int level_no, int order_no) {
		super();
		this.board_id = board_id;
		this.writer = writer;
		this.subject = subject;
		this.content = content;
		this.ip = ip;
		this.passwd = passwd;
		this.group_no = group_no;
		this.level_no = level_no;
		this.order_no = order_no;
	}
	
	
	public Article(int article_id, int board_id, String writer, String subject, String content, String regdate,
			int hitCount, String ip, String passwd, int group_no, int level_no, int order_no) {
		super();
		this.article_id = article_id;
		this.board_id = board_id;
		this.writer = writer;
		this.subject = subject;
		this.content = content;
		this.regdate = regdate;
		this.hitCount = hitCount;
		this.ip = ip;
		this.passwd = passwd;
		this.group_no = group_no;
		this.level_no = level_no;
		this.order_no = order_no;
	}


	public int getArticle_id() {
		return article_id;
	}


	public void setArticle_id(int article_id) {
		this.article_id = article_id;
	}


	public int getBoard_id() {
		return board_id;
	}


	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}


	public String getWriter() {
		return writer;
	}


	public void setWriter(String writer) {
		this.writer = writer;
	}


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getRegdate() {
		return regdate;
	}


	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}


	public int getHitcount() {
		return hitCount;
	}


	public void setHitcount(int hitCount) {
		this.hitCount = hitCount;
	}


	public String getIp() {
		return ip;
	}


	public void setIp(String ip) {
		this.ip = ip;
	}


	public String getPasswd() {
		return passwd;
	}


	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}


	public int getGroup_no() {
		return group_no;
	}


	public void setGroup_no(int group_no) {
		this.group_no = group_no;
	}


	public int getLevel_no() {
		return level_no;
	}


	public void setLevel_no(int level_no) {
		this.level_no = level_no;
	}


	public int getOrder_no() {
		return order_no;
	}


	public void setOrder_no(int order_no) {
		this.order_no = order_no;
	}

	@Override
	public String toString() {
		return "Article [article_id=" + article_id + ", board_id=" + board_id + ", writer=" + writer + ", subject="
				+ subject + ", content=" + content + ", regdate=" + regdate + ", hitCount=" + hitCount + ", ip=" + ip
				+ ", passwd=" + passwd + ", group_no=" + group_no + ", level_no=" + level_no + ", order_no=" + order_no
				+ "]";
	}
	
}