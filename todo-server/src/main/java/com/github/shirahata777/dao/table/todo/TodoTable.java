package com.github.shirahata777.dao.table.todo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="todo")
public class TodoTable {

	@Id
	@Column(name = "todono")
	private int todoNo;

	@NotEmpty
	@Column(name = "name")
	private String name;

	@NotEmpty
	@Size(min = 1, max = 255)
	@Column(name = "content")
	private String content;

	public int getTodono() {
		return todoNo;
	}

	public void setTodono(int todoNo) {
		this.todoNo = todoNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
