package com.github.shirahata777.api.todo;

import javax.json.JsonObject;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.slf4j.LoggerFactory;

import com.github.shirahata777.dao.table.todo.ScheduleTable;
import com.github.shirahata777.dao.table.todo.TodoTable;
import com.github.shirahata777.dao.table.user.UserTable;
import com.github.shirahata777.entity.SaveData;
import com.github.shirahata777.entity.schedule.SerachData;

import io.helidon.webserver.Routing;
import io.helidon.webserver.ServerRequest;
import io.helidon.webserver.ServerResponse;
import io.helidon.webserver.Service;

import org.slf4j.Logger;

public class TodoService implements Service {

	private static Logger log = LoggerFactory.getLogger(TodoService.class);

	@Override
	public void update(Routing.Rules rules) {
		rules
			.post("/todo/save", this::saveFormDataHandler)
			.get("/todo", this::getAllFormDataHandler)
			.get("/todo/{todoNo}/detail", this::getDetailFormDataHandler);
	}

	private void saveFormDataHandler(ServerRequest request, ServerResponse response) {

		request.content().as(JsonObject.class).thenAccept(json -> {
			TodoTable todoTable = new TodoTable();

			todoTable.setUserNo(Integer.parseInt(json.get("userno").toString()));
			todoTable.setTitle(json.get("title").toString());
			todoTable.setContent(json.get("content").toString());
			long todoId = SaveData.accept(todoTable);
			ScheduleTable scheduleTable = new ScheduleTable();
			scheduleTable.setTodoNo((int) todoId);
			scheduleTable.setStart(json.get("start").toString());
			scheduleTable.setEnd(json.get("end").toString());
			long scheduleId = SaveData.accept(scheduleTable);
			String sendData = "Save OK!";
			
			if (!(todoId > 0 && scheduleId > 0)) {
				sendData = "No Saved";
			}
			
			response.send(sendData);
		});
	}

	private void getAllFormDataHandler(ServerRequest request, ServerResponse response) {

		
		TodoTable todoTable = new TodoTable();
		Configuration cfg = null;
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		try {

			// 構成情報の読み込み
			cfg = new Configuration().configure();
			// セッションファクトリをビルド
			sessionFactory = cfg.buildSessionFactory();
			// セッションを取得
			session = sessionFactory.openSession();
			// トランザクションを開始
			transaction = session.beginTransaction();
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<TodoTable> cq = cb.createQuery(TodoTable.class);
			Root<TodoTable> rootEntry = cq.from(TodoTable.class);
			CriteriaQuery<TodoTable> all = cq.select(rootEntry);

			TypedQuery<TodoTable> allQuery = session.createQuery(all);

			ObjectMapper mapper = new ObjectMapper();
			String jsonString = mapper.writeValueAsString(allQuery.getResultList());

			response.send(jsonString);
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
				
			}
			e.printStackTrace();
			response.send("No Get Data!");
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	// TODO 作成中
	public void getDetailFormDataHandler(ServerRequest request, ServerResponse response) {
		Configuration cfg = null;
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;

		int todoNo = Integer.parseInt(request.path().param("todoNo"));
		try {

			// 構成情報の読み込み
			cfg = new Configuration().configure();
			// セッションファクトリをビルド
			sessionFactory = cfg.buildSessionFactory();
			// セッションを取得
			session = sessionFactory.openSession();
			// トランザクションを開始
			transaction = session.beginTransaction();
			LockMode lockMode = LockMode.NONE;
			TodoTable detailQuery =  session.get(TodoTable.class, todoNo, lockMode);

			ObjectMapper mapper = new ObjectMapper();
			String jsonString = mapper.writeValueAsString(detailQuery);

			response.send(jsonString);
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			response.send("No Get Data!");
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

}
