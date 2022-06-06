package bean;

import java.util.Date;

public class ReturnBean {
	//	主キー
	private int id;
	//	外部キー（detailTbl）
	private int detail_Id;
	//	外部キー(memberTbl)
	private int member_Id;

	private Date rental_date;

	private Date rental_due_date;

	private Date returned_date;

	private String name;

	private String title;

	public ReturnBean() {
		super();
	}

	// 全件
	public ReturnBean(int id, int detail_Id, int member_Id, Date rental_date, Date rental_due_date, Date returned_date,
			String name, String title) {
		super();
		this.id = id;
		this.detail_Id = detail_Id;
		this.member_Id = member_Id;
		this.rental_date = rental_date;
		this.rental_due_date = rental_due_date;
		this.returned_date = returned_date;
		this.name = name;
		this.title = title;
	}

	// 返却画面
	public ReturnBean(int detail_Id, int member_Id, Date rental_date, Date rental_due_date) {
		super();
		this.member_Id = member_Id;
		this.detail_Id = detail_Id;
		this.rental_date = rental_date;
		this.rental_due_date = rental_due_date;
	}

	// 確認、完了
	public ReturnBean(String name, String title, Date rental_due_date) {
		super();
		this.name = name;
		this.title = title;
		this.rental_due_date = rental_due_date;
	}

	// 履歴
	public ReturnBean(int detail_Id, int member_Id, Date rental_date, Date rental_due_date, Date returned_date) {
		super();
		this.member_Id = member_Id;
		this.detail_Id = detail_Id;
		this.rental_date = rental_date;
		this.rental_due_date = rental_due_date;
		this.returned_date = returned_date;
	}

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

	public Date getRental_date() {
		return rental_date;
	}

	public void setRental_date(Date rental_date) {
		this.rental_date = rental_date;
	}

	public Date getRental_due_date() {
		return rental_due_date;
	}

	public void setRental_due_date(Date rental_due_date) {
		this.rental_due_date = rental_due_date;
	}

	public Date getReturned_date() {
		return returned_date;
	}

	public void setReturned_date(Date returned_date) {
		this.returned_date = returned_date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "ReturnBean [id=" + id + ", detail_Id=" + detail_Id + ", member_Id=" + member_Id + ", rental_date="
				+ rental_date + ", rental_due_date=" + rental_due_date + ", returned_date=" + returned_date + ", name="
				+ name + ", title=" + title + "]";
	}

}
