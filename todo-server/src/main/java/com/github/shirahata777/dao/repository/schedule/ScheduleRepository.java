package com.github.shirahata777.dao.repository.schedule;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.json.JsonObject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.shirahata777.dao.repository.MainRepository;
import com.github.shirahata777.dao.schedule.ScheduleTable;

public class ScheduleRepository {

	private static Logger log = LoggerFactory.getLogger(ScheduleRepository.class);

	private Configuration cfg = null;;
	private SessionFactory sessionFactory = null;
	private Session session = null;
	private Transaction transaction = null;

	public ScheduleRepository() {
		cfg = new Configuration().configure();
		sessionFactory = cfg.buildSessionFactory();
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
	}

	public List<ScheduleTable> findAll() {
		List<ScheduleTable> resultList = new ArrayList<>();

		try {
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<ScheduleTable> cq = cb.createQuery(ScheduleTable.class);
			Root<ScheduleTable> rootEntry = cq.from(ScheduleTable.class);
			CriteriaQuery<ScheduleTable> all = cq.select(rootEntry);

			Query<ScheduleTable> allQuery = session.createQuery(all);

			resultList = allQuery.getResultList();

			return resultList;
		} catch (Exception e) {
			if (transaction != null) {
				log.warn(e.toString());
				transaction.rollback();
			}
			log.warn(e.toString());
			return null;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public ScheduleTable findDetail(int scheduleNo) {
		try {
			LockMode lockMode = LockMode.NONE;
			ScheduleTable detailQuery = session.get(ScheduleTable.class, scheduleNo, lockMode);

			return detailQuery;

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			log.warn(e.toString());
			return null;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public long save(JsonObject json, long todoId) {
		ScheduleTable scheduleTable = new ScheduleTable();

		scheduleTable.setTodoNo((int) todoId);
		scheduleTable.setStart(Date.valueOf(json.get("start").toString().replaceAll("\"", "")));
		scheduleTable.setEnd(Date.valueOf(json.get("end").toString().replaceAll("\"", "")));

		long scheduleId = MainRepository.save(scheduleTable);

		return scheduleId;

	}

}
