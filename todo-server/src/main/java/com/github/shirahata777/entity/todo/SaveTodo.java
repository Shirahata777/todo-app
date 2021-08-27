package com.github.shirahata777.entity.todo;

import javax.json.JsonObject;

import com.github.shirahata777.dao.table.todo.TodoTable;
import com.github.shirahata777.entity.SaveData;

public class SaveTodo {

	public long accept(JsonObject json) {
		TodoTable todoTable = new TodoTable();

		todoTable.setUserNo(Integer.parseInt(json.get("userno").toString()));
		todoTable.setTitle(json.get("title").toString());
		todoTable.setContent(json.get("content").toString());
		long todoId = SaveData.accept(todoTable);
		return todoId;
	}

}
