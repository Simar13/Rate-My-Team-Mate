package bean;


public class Customer {	

	private String nric;
	private String firstName;
	private String lastName;
	private String department;
	private String university;
	private String userid;
	private String password;
	public String question;	
	public String answer;
	public String email;
	public long phone;
	
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(String nric, String firstName, String lastName,
			String department, String university, String userid,
			String password, String question, String answer, String email,
			long phone) {
		super();
		this.nric = nric;
		this.firstName = firstName;
		this.lastName = lastName;
		this.department = department;
		this.university = university;
		this.userid = userid;
		this.password = password;
		this.question = question;
		this.answer = answer;
		this.email = email;
		this.phone = phone;
	}

	public String getNric() {
		return nric;
	}

	public void setNric(String nric) {
		this.nric = nric;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Customer [nric=" + nric + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", department=" + department
				+ ", university=" + university + ", userid=" + userid
				+ ", password=" + password + ", question=" + question
				+ ", answer=" + answer + ", email=" + email + ", phone="
				+ phone + "]";
	}
	
}
	
	
	