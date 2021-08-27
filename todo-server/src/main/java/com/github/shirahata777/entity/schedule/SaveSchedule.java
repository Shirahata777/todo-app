package com.github.shirahata777.entity.schedule;

import javax.json.JsonObject;

import com.github.shirahata777.dao.table.todo.ScheduleTable;
import com.github.shirahata777.entity.SaveData;

public class SaveSchedule {
	
	public long accept(JsonObject json, long todoId) {
		ScheduleTable scheduleTable = new ScheduleTable();

		scheduleTable.setTodoNo((int) todoId);
		scheduleTable.setStart(json.get("start").toString());
		scheduleTable.setEnd(json.get("end").toString());
		
		long scheduleId = SaveData.accept(scheduleTable);
		
		return scheduleId;
		
	}

}
