package bean;

public class ReturnBean {

	//定義ー－－－－－－－－－－－－－－－－－－－－
	//	主キー
	private int id;
	//	外部キー（detailTbl）
	private int detail_Id;
	//	外部キー(memberTbl)
	private int member_Id;

	private String rental_date;

	private String rental_due_date;

	private String returned_date;

	private String name;
	//コンストラクターー－－－－－－－－－－－－－－－－－－－－－
	public ReturnBean() {
		super();
	}

	public ReturnBean(int detail_Id, int member_Id, String rental_date, String rental_due_date) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.detail_Id = detail_Id;
		this.member_Id = member_Id;
		this.rental_date = rental_date;
		this.rental_due_date = rental_due_date;
	}

	public ReturnBean(int id, int detail_Id, int member_Id, String rental_date, String rental_due_date) {
		this.id = id;
		this.detail_Id = detail_Id;
		this.member_Id = member_Id;
		this.rental_date = rental_date;
		this.rental_due_date = rental_due_date;
	}

	public ReturnBean(int id, int detail_Id, int member_Id, String rental_date, String rental_due_date,
			String returned_date) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.id = id;
		this.detail_Id = detail_Id;
		this.member_Id = member_Id;
		this.rental_date = rental_date;
		this.rental_due_date = rental_due_date;
		this.returned_date = returned_date;
	}

	public ReturnBean(String name) {
		this.name =name;
	}

	//ゲッター雪駄ーー－－－－－－－－－－－－－－－－－－－－－－－－－
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDetail_Id() {
		return detail_Id;
	}

	public void setDetail_Id(int detail_Id) {
		this.detail_Id = detail_Id;
	}

	public int getMember_Id() {
		return member_Id;
	}

	public void setMember_Id(int member_Id) {
		this.member_Id = member_Id;
	}

	public String getRental_date() {
		return rental_date;
	}

	public void setRental_date(String rental_date) {
		this.rental_date = rental_date;
	}

	public String getRental_due_date() {
		return rental_due_date;
	}

	public void setRental_due_date(String rental_due_date) {
		this.rental_due_date = rental_due_date;
	}

	public String getReturned_date() {
		return returned_date;
	}

	public void setReturned_date(String returned_date) {
		this.returned_date = returned_date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ReturnBean [id=" + id + ", detail_Id=" + detail_Id + ", member_Id=" + member_Id + ", rental_date="
				+ rental_date + ", rental_due_date=" + rental_due_date + ", returned_date=" + returned_date + ", name="
				+ name + "]";
	}
}


