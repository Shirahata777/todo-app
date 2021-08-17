package com.github.shirahata777.dao.table.todo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "schedule")
public class ScheduleTable {

	@Id
	@Column(name = "scheduleno")
	private int scheduleNo;

	@NotEmpty
	@Column(name = "todono")
	private int todono;

	@NotEmpty
	@Column(name = "startDay")
	private String startDay;

	@NotEmpty
	@Column(name = "endDay")
	private String endDay;

	public int getScheduleno() {
		return scheduleNo;
	}

	public void setScheduleno(int scheduleNo) {
		this.scheduleNo = scheduleNo;
	}

	public int getTodono() {
		return todono;
	}

	public void setTodono(int todono) {
		this.todono = todono;
	}

	public String getStartDay() {
		return startDay;
	}

	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}

	public String getEndDay() {
		return endDay;
	}

	public void setEndDay(String endDay) {
		this.endDay = endDay;
	}

}
