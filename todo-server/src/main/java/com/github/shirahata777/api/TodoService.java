package com.github.shirahata777.api;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.json.JsonObject;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.slf4j.LoggerFactory;

import io.helidon.webserver.Routing;
import io.helidon.webserver.ServerRequest;
import io.helidon.webserver.ServerResponse;
import io.helidon.webserver.Service;

import com.github.shirahata777.dao.TodoDao;
import com.github.shirahata777.dao.table.TodoTable;
import org.slf4j.Logger;

public class TodoService implements Service {

	private static Logger log = LoggerFactory.getLogger(TodoService.class);

	@Override
	public void update(Routing.Rules rules) {
		rules.post("/save", this::saveFormDataHandler).get("/get_all", this::getFormDataHandler);
	}

	private void saveFormDataHandler(ServerRequest request, ServerResponse response) {

		request.content().as(JsonObject.class).thenAccept(json -> {
			TodoTable todoTable = new TodoTable();
			Configuration cfg = null;
			SessionFactory sessionFactory = null;
			Session session = null;
			Transaction transaction = null;
			try {
				// this line is never reached
				todoTable.setName(json.get("name").toString());
				todoTable.setContent(json.get("content").toString());

				// 構成情報の読み込み
				cfg = new Configuration().configure();
				// セッションファクトリをビルド
				sessionFactory = cfg.buildSessionFactory();
				// セッションを取得
				session = sessionFactory.openSession();
				// トランザクションを開始
				transaction = session.beginTransaction();
				session.save(todoTable);
				// コミット
				transaction.commit();
				response.send("Save OK!");
			} catch (Exception e) {
				// ロールバック
				if (transaction != null) {
					transaction.rollback();
				}
				e.printStackTrace();
				response.send("No Saved!");
			} finally {
				if (session != null) {
					session.close();
				}
			}

		});
	}

//	TODO：作成途中

	private void getFormDataHandler(ServerRequest request, ServerResponse response) {
		List<Map<String, String>> todoDataList = new ArrayList<>();
		Map<String, String> todoMap = new LinkedHashMap<>();

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
//		    return allQuery.getResultList();
//			List<TodoTable> allTodoData = session.createQuery("SELECT a FROM todo a", TodoTable.class).getResultList(); 
//
//			for (TodoTable todo : allTodoData) {
//				todoMap.put("name", todo.getName());
//				todoMap.put("content", todo.getContent());
//				todoDataList.add(todoMap);
//			}

			ObjectMapper mapper = new ObjectMapper();
			String jsonString = mapper.writeValueAsString(allQuery.getResultList());

			response.send(jsonString);
		} catch (Exception e) {
			// ロールバック
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
