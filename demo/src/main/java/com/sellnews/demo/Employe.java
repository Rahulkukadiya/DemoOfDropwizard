package com.sellnews.demo;

import java.sql.Timestamp;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Employe_DetailsWithTimeStamp")
public class Employe {
	@Id
	@GeneratedValue
	private long id;
	@Column(name = "Emp_firstName")
	private String firstname;
	@Column(name = "Emp_lastName")
	private String lastname;
	@Column(name = "Emp_position")
	private String position;
	@Column(name = "Emp_phone")
	private String phone;
	@Column(name = "Emp_email")
	private String e_mail;
	@Column(name = "Emp_Status")
	private String emp_Status = "true";
	@Column(name="Emp_RegisterDate")
	private Timestamp emp_registerDate;
	@Column(name="LastModified")
	private Timestamp lastModified;
	public Timestamp getLastModified() {
		return lastModified;
	}

	public void setLastModified(Timestamp lastModified) {
		this.lastModified = lastModified;
	}

	public Employe() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getE_mail() {
		return e_mail;
	}

	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}

	public String getEmp_Status() {
		return emp_Status;
	}

	public void setEmp_Status(String emp_Status) {
		this.emp_Status = emp_Status;
	}
	public Timestamp getEmp_registerDate() {
		return emp_registerDate;
	}

	public void setEmp_registerDate(Timestamp emp_registerDate) {
		this.emp_registerDate = emp_registerDate;
	}

	@Override
	public String toString() {
		return new StringBuffer().append("[").append(" fn :").append(this.firstname).append(" ln :")
				.append(this.lastname).append(" mn :").append(this.phone).append(" em :").append(this.e_mail)
				.append(" pos :").append(this.position).append(" id :").append(this.id).append("]").toString();
	}

}
