package com.github.shirahata777.dao.repository.todo;

import javax.json.JsonObject;

import com.github.shirahata777.dao.repository.SaveData;
import com.github.shirahata777.dao.todo.TodoTable;

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
