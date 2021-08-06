package com.github.shirahata777.utils;

import java.util.List;


import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

import com.github.shirahata777.dao.table.TodoTable;

public class TodoEntityUtils {

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
//		todoTable.setTodono(Integer.parseInt(json.get("id") == null ? "0" : json.getString("todo_no")));
		todoTable.setName((json.get("name") == null ? null : json.getString("name")).toString());
		todoTable.setContent((json.get("content") == null ? null : json.getString("content")).toString());

		return todoTable;
	}

}
