package bean;

public class Detail_BookBean {

	private int id;
	//	外部キー（bookTbl）
	private String book_isbn;
	//	外部キー(memberTbl)
	private String title;


	public Detail_BookBean() {
		super();
	}

	public Detail_BookBean(int id, String book_isbn, String title) {
		super();
		this.id = id;
		this.book_isbn = book_isbn;
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBook_isbn() {
		return book_isbn;
	}

	public void setBook_isbn(String book_isbn) {
		this.book_isbn = book_isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


}