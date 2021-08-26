package com.github.shirahata777.dao.table.todo;

import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

//@Entity
//@Table(name = "schedule")
public class ScheduleTable {

	@Id
//	@Column(name = "scheduleno")
	private int scheduleNo;

	@NotEmpty
//	@Column(name = "todono")
	private int todoNo;

	@NotEmpty
//	@Column(name = "startDay")
	private String start;

	@NotEmpty
//	@Column(name = "endDay")
	private String end;

	public int getScheduleNo() {
		return scheduleNo;
	}

	public void setScheduleNo(int scheduleNo) {
		this.scheduleNo = scheduleNo;
	}

	public int getTodoNo() {
		return todoNo;
	}

	public void setTodoNo(int todoNo) {
		this.todoNo = todoNo;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

}
