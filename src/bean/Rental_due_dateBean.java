package bean;

public class Rental_due_dateBean {

	private String rental_due_date;
	private int id;

	public Rental_due_dateBean(String rental_due_date) {
		super();
		this.rental_due_date = rental_due_date;
	}

	public Rental_due_dateBean(int id, String rental_due_date) {
		// TODO 自動生成されたコンストラクター・スタブ
		super();
		this.id = id;
		this.rental_due_date = rental_due_date;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	public String getRental_due_date() {
		return rental_due_date;
	}

	public void setRental_due_date(String rental_due_date) {
		this.rental_due_date = rental_due_date;
	}

	@Override
	public String toString() {
		return "Rental_due_dateBean [rental_due_date=" + rental_due_date + ", id=" + id + "]";
	}





}
