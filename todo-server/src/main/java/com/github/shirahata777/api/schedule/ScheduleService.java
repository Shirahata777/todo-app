package com.github.shirahata777.api.schedule;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.json.JsonObject;

import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.shirahata777.dao.repository.SaveData;
import com.github.shirahata777.dao.schedule.ScheduleTable;

import io.helidon.webserver.Routing;
import io.helidon.webserver.ServerRequest;
import io.helidon.webserver.ServerResponse;
import io.helidon.webserver.Service;

public class ScheduleService implements Service {

	private static Logger log = LoggerFactory.getLogger(ScheduleService.class);

	@Override
	public void update(Routing.Rules rules) {
		rules.get("/schedule", this::serachScheduleHandler).get("/schedule/{scheduleNo}/detail",
				this::getDetailScheduleDataHandler);
	}

	public void saveScheduleDataHandler(ServerRequest request, ServerResponse response) {

		request.content().as(JsonObject.class).thenAccept(json -> {
			ScheduleTable scheduleTable = new ScheduleTable();
			scheduleTable.setTodoNo(Integer.parseInt(json.get("todono").toString()));
			scheduleTable.setStart(Date.valueOf(json.get("start").toString()));
			scheduleTable.setEnd(Date.valueOf(json.get("end").toString()));
			SaveData.accept(scheduleTable);
		});
	}

	private void serachScheduleHandler(ServerRequest request, ServerResponse response) {

		Configuration cfg = new Configuration().configure();
		SessionFactory sessionFactory = cfg.buildSessionFactory();
		Session session = sessionFactory.openSession();
		String queryText = "select * from schedule s";
		List allQuery = session.createSQLQuery(queryText).addEntity("s", ScheduleTable.class).list();

		ObjectMapper mapper = new ObjectMapper();
		String jsonString = null;
		try {
			jsonString = mapper.writeValueAsString(allQuery);
		} catch (IOException e) {
			log.warn(e.toString());
		}

		response.send(jsonString);

	}

	public void getDetailScheduleDataHandler(ServerRequest request, ServerResponse response) {
		Configuration cfg = null;
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;

		int scheduleNo = Integer.parseInt(request.path().param("scheduleNo"));
		try {

			cfg = new Configuration().configure();
			sessionFactory = cfg.buildSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			LockMode lockMode = LockMode.NONE;
			ScheduleTable detailQuery = session.get(ScheduleTable.class, scheduleNo, lockMode);

			ObjectMapper mapper = new ObjectMapper();
			String jsonString = mapper.writeValueAsString(detailQuery);

			response.send(jsonString);
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			log.warn(e.toString());
			response.send("No Get Data!");
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

}
