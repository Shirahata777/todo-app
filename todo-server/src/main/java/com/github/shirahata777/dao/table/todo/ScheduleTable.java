package com.github.shirahata777.dao.table.todo;

import java.sql.Date;

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
	private Date start;

	@NotEmpty
//	@Column(name = "endDay")
	private Date end;

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

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

}
