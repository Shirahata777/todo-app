package com.github.shirahata777.utils;

import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

import com.github.shirahata777.dao.table.todo.TodoTable;

public class TodoEntityUtils {
	
	TodoEntityUtils() {
		
	}

	public static JsonObject toJsonObject(TodoTable todoTable) {
		return Json.createObjectBuilder().add("todo_no", todoTable.getTodono()).add("name", todoTable.getName())
				.add("content", todoTable.getContent()).build();
	}

	public static JsonArray toJsonArray(List<TodoTable> todoTables) {
		JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
		todoTables.forEach(p -> {
			jsonArrayBuilder.add(toJsonObject(p));
		});

		return jsonArrayBuilder.build();
	}

	public static TodoTable fromJsonObject(JsonObject json) {
		TodoTable todoTable = new TodoTable();
		todoTable.setName((json.get("name") == null ? "No Name" : json.getString("name")).toString());
		todoTable.setContent((json.get("content") == null ? "No Content" : json.getString("content")).toString());

		return todoTable;
	}

}
