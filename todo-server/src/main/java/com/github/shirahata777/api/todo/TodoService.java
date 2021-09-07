package com.github.shirahata777.api.todo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.json.JsonObject;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.LoggerFactory;

import com.github.shirahata777.dao.repository.schedule.ScheduleRepository;
import com.github.shirahata777.dao.repository.todo.TodoRepository;
import com.github.shirahata777.dao.schedule.ScheduleTable;
import com.github.shirahata777.dao.todo.TodoTable;

import io.helidon.common.http.Parameters;
import io.helidon.webserver.Routing;
import io.helidon.webserver.ServerRequest;
import io.helidon.webserver.ServerResponse;
import io.helidon.webserver.Service;

import org.slf4j.Logger;

public class TodoService implements Service {

	private static Logger log = LoggerFactory.getLogger(TodoService.class);

	@Override
	public void update(Routing.Rules rules) {
		rules.post("/todo/save", this::saveFormDataHandler).get("/todo", this::getAllTodoDataHandler)
				.get("/todo/{todoNo}", this::getDetailTodoDataHandler);
	}

	private void saveFormDataHandler(ServerRequest request, ServerResponse response) {

		request.content().as(JsonObject.class).thenAccept(json -> {

			TodoRepository todoRepository = new TodoRepository();
			long todoId = todoRepository.save(json);

			ScheduleRepository scheduleRepository = new ScheduleRepository();
			long scheduleId = scheduleRepository.save(json, todoId);

			String sendData = "No Saved";

			if (!(todoId > 0L && scheduleId > 0L)) {
				sendData = "No Saved";
			} else {
				sendData = "Save OK!";
			}

			response.send(sendData);
		});
	}

	private void getAllTodoDataHandler(ServerRequest request, ServerResponse response) {

		ObjectMapper mapper = new ObjectMapper();
		TodoRepository todoRepository = new TodoRepository();
		ScheduleRepository scheduleRepository = new ScheduleRepository();
		Parameters p = request.queryParams();
		int limit = 100;
		int offset = 0;
		String begin = "";
		String close = "";
		try {
			limit = Integer.parseInt(p.first("limit").get());
		} catch (NoSuchElementException e) {
			log.warn("limit set default param: 100");
		}
		try {
			offset = Integer.parseInt(p.first("offset").get());
		} catch (NoSuchElementException e) {
			log.warn("offset set default param: 0");
		}
		try {
			begin = p.first("begin").get();
		} catch (NoSuchElementException e) {
			log.warn("begin no set");
		}
		try {
			close = p.first("close").get();
		} catch (NoSuchElementException e) {
			log.warn("close no set");
		}

		String jsonString = "";

		try {
			List<TodoTable> todo = todoRepository.findAll(limit, offset);
			List<ScheduleTable> schedule = scheduleRepository.findAll();
			jsonString = mapper.writeValueAsString(createSendData(todo, schedule));
		} catch (IOException e) {
			jsonString = "No Data";
			log.warn(e.toString());
			e.printStackTrace();
		}

		response.send(jsonString);

	}

	public void getDetailTodoDataHandler(ServerRequest request, ServerResponse response) {

		int todoNo = Integer.parseInt(request.path().param("todoNo"));

		ObjectMapper mapper = new ObjectMapper();

		TodoRepository detailTodo = new TodoRepository();
		String jsonString = "";

		try {
			jsonString = mapper.writeValueAsString(detailTodo.findDetail(todoNo));
		} catch (IOException e) {
			jsonString = "No Data";
			log.warn(e.toString());
			e.printStackTrace();
		}

		response.send(jsonString);
	}

	private List<Map<String, Object>> createSendData(List<TodoTable> todo, List<ScheduleTable> schedule) {
		List<Map<String, Object>> result = new ArrayList<>();
		for (TodoTable t : todo) {
			for (ScheduleTable s : schedule) {
				if (t.getTodoNo() == s.getTodoNo()) {
					Map<String, Object> m = new LinkedHashMap<>();
					m.put("title", t.getTitle());
					m.put("content", t.getContent());
					m.put("todo_no", t.getTodoNo());
					m.put("user_no", t.getUserNo());
					m.put("schedule_no", s.getScheduleNo());
					m.put("start", s.getStart());
					m.put("end", s.getEnd());
					result.add(m);
				}
			}
		}

		return result;
	}

}
