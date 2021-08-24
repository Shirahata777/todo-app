package com.github.shirahata777.entity.schedule;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.json.JsonObject;

import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.shirahata777.api.todo.TodoService;
import com.github.shirahata777.dao.table.todo.ScheduleTable;

public class SaveData {
	private static Logger log = LoggerFactory.getLogger(TodoService.class);

	public static String accept(JsonObject json, Object table) {
		String sendData = "";

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
			session.save(table);
			// コミット
			transaction.commit();
			sendData = "Save OK!";
		} catch (Exception e) {
			// ロールバック
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			sendData = "No Saved!";
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return sendData;
	}

}
