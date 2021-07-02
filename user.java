package empapp.net.user;

public class user {
	protected int id;
	protected String fname;
	protected String lname;
	protected String salary;
	protected String department;
	protected String position;
	protected String email;
	protected String pnum;
	protected byte[] image;
	
	
	
	
	/**
	 * @param fname
	 * @param lname
	 * @param salary
	 * @param department
	 * @param position
	 * @param email
	 * @param pnum
	 * @param image
	 */
	public user(String fname, String lname, String salary, String department, String position, String email,
			String pnum, byte[] image) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.salary = salary;
		this.department = department;
		this.position = position;
		this.email = email;
		this.pnum = pnum;
		this.image = image;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getemail() {
		return email;
	}
	public void setemail(String email) {
		this.email = email;
	}
	public String getPnum() {
		return pnum;
	}
	public void setPnum(String pnum) {
		this.pnum = pnum;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public String getFname1() {
		
		return null;
	}
	
	
	
}
