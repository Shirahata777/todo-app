package com.github.shirahata777.dao.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="user")
public class UserTable {

	@Id
	@Column(name = "userno")
	private int userNo;

	@NotEmpty
	@Column(name = "name")
	private String name;

	@Email
	@NotEmpty
	@Column(name = "email")
	private String email;
//	private String password;

	public int getUserId() {
		return userNo;
	}

	public void setUserId(int userNo) {
		this.userNo = userNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
//	public String getPassword() {
//		return password;
//	}
//	public void setPassword(String password) {
//		this.password = password;
//	}

}
