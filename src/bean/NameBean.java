package bean;

public class NameBean {

	private String name;

	public NameBean(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "NameBean [name=" + name + "]";
	}

}
