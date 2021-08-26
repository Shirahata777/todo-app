package com.github.shirahata777.entity;

import javax.json.JsonObject;
import java.math.BigInteger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SaveData {
	private static Logger log = LoggerFactory.getLogger(SaveData.class);

	public static Long accept(Object table) {
		Long sendData;

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
			Long lastId = ((BigInteger) session.createSQLQuery("SELECT LAST_INSERT_ID()").uniqueResult()).longValue();
			sendData = lastId;
		} catch (Exception e) {
			// ロールバック
			if (transaction != null) {
				transaction.rollback();
			}
			log.warn(e.toString());
			e.printStackTrace();
			sendData = 0L;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return sendData;
	}

}
