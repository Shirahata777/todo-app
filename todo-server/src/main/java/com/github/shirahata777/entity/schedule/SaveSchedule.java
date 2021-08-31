package com.github.shirahata777.entity.schedule;

import java.sql.Date;

import javax.json.JsonObject;

import com.github.shirahata777.dao.table.todo.ScheduleTable;
import com.github.shirahata777.entity.SaveData;

public class SaveSchedule {

	public long accept(JsonObject json, long todoId) {
		ScheduleTable scheduleTable = new ScheduleTable();

		scheduleTable.setTodoNo((int) todoId);
		scheduleTable.setStart(Date.valueOf(json.get("start").toString().replaceAll("\"", "")));
		scheduleTable.setEnd(Date.valueOf(json.get("end").toString().replaceAll("\"", "")));

		long scheduleId = SaveData.accept(scheduleTable);

		return scheduleId;

	}

}
