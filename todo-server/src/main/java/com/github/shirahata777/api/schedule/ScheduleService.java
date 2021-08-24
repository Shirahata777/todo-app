package com.github.shirahata777.api.schedule;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.shirahata777.api.todo.TodoService;
import com.github.shirahata777.dao.table.todo.ScheduleTable;
import com.github.shirahata777.dao.table.todo.TodoTable;
import com.github.shirahata777.entity.schedule.SaveData;

import io.helidon.webserver.Routing;
import io.helidon.webserver.ServerRequest;
import io.helidon.webserver.ServerResponse;
import io.helidon.webserver.Service;

public class ScheduleService implements Service {

	private static Logger log = LoggerFactory.getLogger(TodoService.class);

	@Override
	public void update(Routing.Rules rules) {
		rules.get("/schedule/{userNo}", this::getScheduleandler);
	}

	public void saveFormDataHandler(ServerRequest request, ServerResponse response) {

		request.content().as(JsonObject.class).thenAccept(json -> {
			ScheduleTable scheduleTable = new ScheduleTable();
			scheduleTable.setTodono(Integer.parseInt(json.get("todono").toString()));
			scheduleTable.setStartDay(json.get("start").toString());
			scheduleTable.setEndDay(json.get("end").toString());
			SaveData.accept(json, scheduleTable);
		});
	}

	private void getScheduleandler(ServerRequest request, ServerResponse response) {

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
			CriteriaQuery<ScheduleTable> cq = cb.createQuery(ScheduleTable.class);
			Root<ScheduleTable> rootEntry = cq.from(ScheduleTable.class);
			CriteriaQuery<ScheduleTable> all = cq.select(rootEntry);

			TypedQuery<ScheduleTable> allQuery = session.createQuery(all);

			ObjectMapper mapper = new ObjectMapper();
			String jsonString = mapper.writeValueAsString(allQuery.getResultList());

			response.send(jsonString);
		} catch (Exception e) {
			if (transaction != null) {
				log.warn(e.toString());
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
			TodoTable detailQuery = session.get(TodoTable.class, todoNo, lockMode);

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
