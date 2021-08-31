package com.github.shirahata777.repository.schedule;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SerachData {

	private static Logger log = LoggerFactory.getLogger(SerachData.class);

	public static String accept(Object table) {
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
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<? extends Object> cq = cb.createQuery(table.getClass());
			Root<? extends Object> rootEntry = cq.from(table.getClass());
			CriteriaQuery<? extends Object> all = cq.multiselect(rootEntry);

			TypedQuery<? extends Object> allQuery = session.createQuery(all);

			ObjectMapper mapper = new ObjectMapper();
			String jsonString = mapper.writeValueAsString(allQuery.getResultList());

			sendData = jsonString;
		} catch (Exception e) {
			if (transaction != null) {
				log.warn(e.toString());
				transaction.rollback();
			}
			log.warn(e.toString());

			sendData = "No Get Data!";
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return sendData;
	}

}
