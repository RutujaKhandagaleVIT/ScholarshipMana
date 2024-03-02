package com.ischolar.ischolar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Application")
public class ApplicationForm {
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private int id;
	private String firstName;
	private String middleName;
	private String lastName;
	private String email;
	private String contact;
	private String gender;
	private String dob;
	private String disability;
	private String address;
	private String category;
	private String cast;
	private int income;
	private String college;
	private String branch;
	private int year;
	private int gradution_marks;
	private int twelth_marks;
	private int tenth_marks;
	private int diploma_marks;
	private String parents_name;
	private String parests_occupation;
	private String institute;
	private String department;
	private String ddo;
	private String scheme;
	private boolean editable;
	@Column(length=300)
	private String message;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isEditable() {
		return editable;
	}
	public void setEditable(boolean editable) {
		this.editable = editable;
	}
	@ManyToOne
	private User user;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getDisability() {
		return disability;
	}
	public void setDisability(String disability) {
		this.disability = disability;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCast() {
		return cast;
	}
	public void setCast(String cast) {
		this.cast = cast;
	}
	public int getIncome() {
		return income;
	}
	public void setIncome(int income) {
		this.income = income;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getGradution_marks() {
		return gradution_marks;
	}
	public void setGradution_marks(int gradution_marks) {
		this.gradution_marks = gradution_marks;
	}
	public int getTwelth_marks() {
		return twelth_marks;
	}
	public void setTwelth_marks(int twelth_marks) {
		this.twelth_marks = twelth_marks;
	}
	public int getTenth_marks() {
		return tenth_marks;
	}
	public void setTenth_marks(int tenth_marks) {
		this.tenth_marks = tenth_marks;
	}
	public int getDiploma_marks() {
		return diploma_marks;
	}
	public void setDiploma_marks(int diploma_marks) {
		this.diploma_marks = diploma_marks;
	}
	public String getParents_name() {
		return parents_name;
	}
	public void setParents_name(String parents_name) {
		this.parents_name = parents_name;
	}
	public String getParests_occupation() {
		return parests_occupation;
	}
	public void setParests_occupation(String parests_occupation) {
		this.parests_occupation = parests_occupation;
	}
	public String getInstitute() {
		return institute;
	}
	public void setInstitute(String institute) {
		this.institute = institute;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDdo() {
		return ddo;
	}
	public void setDdo(String ddo) {
		this.ddo = ddo;
	}
	public String getScheme() {
		return scheme;
	}
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "ApplicationForm [id=" + id + ", firstName=" + firstName + ", middleName=" + middleName + ", lastName="
				+ lastName + ", email=" + email + ", contact=" + contact + ", gender=" + gender + ", dob=" + dob
				+ ", disability=" + disability + ", address=" + address + ", category=" + category + ", cast=" + cast
				+ ", income=" + income + ", college=" + college + ", branch=" + branch + ", year=" + year
				+ ", gradution_marks=" + gradution_marks + ", twelth_marks=" + twelth_marks + ", tenth_marks="
				+ tenth_marks + ", diploma_marks=" + diploma_marks + ", parents_name=" + parents_name
				+ ", parests_occupation=" + parests_occupation + ", institute=" + institute + ", department="
				+ department + ", ddo=" + ddo + ", scheme=" + scheme + ", editable=" + editable + ", message=" + message
				+ ", user=" + user + "]";
	}
}
