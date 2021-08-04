package com.github.shirahata777.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import io.helidon.webserver.Routing;
import io.helidon.webserver.ServerRequest;
import io.helidon.webserver.ServerResponse;
import io.helidon.webserver.Service;

import com.github.shirahata777.dao.TodoDao;
import com.github.shirahata777.query.TodoQuery;

public class Todoing implements Service {

	@Override
	public void update(Routing.Rules rules) {
		rules.get("/save", this::saveFormDataHandler); // .get("/get_all", this::getFormDataHandler)
	}

	private void saveFormDataHandler(ServerRequest request, ServerResponse response) {
		Map<String, List<String>> params = request.queryParams().toMap();

		TodoQuery query = new TodoQuery();

		String name = params.get("name").get(0);
		String content = params.get("content").get(0);

		query.setName(name);
		query.setContent(content);

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

			TodoDao dao = new TodoDao(session);
			dao.insert(query);

			// コミット
			transaction.commit();
		} catch (Exception e) {
			// ロールバック
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			// 常にセッションを閉じる
			if (session != null) {
				session.close();
			}
		}
	

		sendResponse(response, "Save OK!");
	}
	
//	TODO：作成途中

//	private void getFormDataHandler(ServerRequest request, ServerResponse response) {
//		List<Map<String, String>> formDataList = new ArrayList<>();
//
//		TodoDao todo = new TodoDao();
//		formDataList = todo.getAllFormData();
//
//		ObjectMapper mapper = new ObjectMapper();
//
//		String jsonString = null;
//		try {
//			jsonString = mapper.writeValueAsString(formDataList);
//		} catch (IOException e) {
//			jsonString = "No Create jsonObject";
//			e.printStackTrace();
//		}
//
//		sendResponse(response, jsonString);
//	}

	private void sendResponse(ServerResponse response, String msg) {
		response.send(msg);
	}

}
