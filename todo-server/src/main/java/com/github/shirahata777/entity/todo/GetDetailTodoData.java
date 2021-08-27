package com.github.shirahata777.entity.todo;

import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.shirahata777.dao.table.todo.TodoTable;

public class GetDetailTodoData {
	
	private static Logger log = LoggerFactory.getLogger(GetDetailTodoData.class);

	public String accept(int todoNo) {
		Configuration cfg = null;
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		
		String jsonString = "";

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
			TodoTable detailQuery = session.get(TodoTable.class, todoNo, lockMode);

			ObjectMapper mapper = new ObjectMapper();
			jsonString = mapper.writeValueAsString(detailQuery);

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			log.warn(e.toString());
			jsonString = "No Get Data!";
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return jsonString;
	}

}
