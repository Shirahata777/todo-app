package com.github.shirahata777.dao.table.todo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity
//@Table(name="todo")
public class TodoTable {

	@Id
//	@GeneratedValue
//	@Column(name = "todono")
	private int todoNo;

	@NotEmpty
//	@Column(name = "userno")
	private int userNo;
	
	@NotEmpty
//	@Column(name = "title")
	private String title;

	@NotEmpty
	@Size(min = 1, max = 255)
//	@Column(name = "content")
	private String content;

	public int getTodono() {
		return todoNo;
	}

	public void setTodono(int todoNo) {
		this.todoNo = todoNo;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
