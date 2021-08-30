package com.github.shirahata777.api.todo;

import java.io.IOException;
import java.util.NoSuchElementException;

import javax.json.JsonObject;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.LoggerFactory;

import com.github.shirahata777.entity.schedule.SaveSchedule;
import com.github.shirahata777.entity.todo.GetAllTodoData;
import com.github.shirahata777.entity.todo.GetDetailTodoData;
import com.github.shirahata777.entity.todo.SaveTodo;

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

			SaveTodo st = new SaveTodo();
			long todoId = st.accept(json);

			SaveSchedule ss = new SaveSchedule();
			long scheduleId = ss.accept(json, todoId);

			String sendData = "";

			if (!(todoId > 0 && scheduleId > 0)) {
				sendData = "No Saved";
			} else {
				sendData = "Save OK!";
			}

			response.send(sendData);
		});
	}

	private void getAllTodoDataHandler(ServerRequest request, ServerResponse response) {

		ObjectMapper mapper = new ObjectMapper();
		GetAllTodoData getAllTodo = new GetAllTodoData();
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
			jsonString = mapper.writeValueAsString(getAllTodo.accept(limit, offset));
		} catch (IOException e) {
			jsonString = "No Data";
			log.warn(e.toString());
			e.printStackTrace();
		}

		response.send(jsonString);

	}

	public void getDetailTodoDataHandler(ServerRequest request, ServerResponse response) {

		int todoNo = Integer.parseInt(request.path().param("todoNo"));

		GetDetailTodoData detailTodo = new GetDetailTodoData();
		String jsonString = detailTodo.accept(todoNo);

		response.send(jsonString);
	}

}
