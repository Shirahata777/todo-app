package com.github.shirahata777.dao.repository.todo;

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
import com.github.shirahata777.dao.todo.TodoTable;

public class TodoRepository {

	private static Logger log = LoggerFactory.getLogger(TodoRepository.class);

	private Configuration cfg = null;
	private SessionFactory sessionFactory = null;
	private Session session = null;
	private Transaction transaction = null;

	public TodoRepository() {
		cfg = new Configuration().configure();
		sessionFactory = cfg.buildSessionFactory();
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
	}

	public List<TodoTable> findAll(int limit, int offset) {

		List<TodoTable> resultList = new ArrayList<>();

		try {

			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<TodoTable> cq = cb.createQuery(TodoTable.class);
			Root<TodoTable> rootEntry = cq.from(TodoTable.class);
			CriteriaQuery<TodoTable> all = cq.select(rootEntry);

			Query<TodoTable> allQuery = session.createQuery(all).setFirstResult(offset).setMaxResults(limit);

			resultList = allQuery.getResultList();

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

		return resultList;

	}

	public TodoTable findDetail(int todoNo) {
		try {
			LockMode lockMode = LockMode.NONE;
			TodoTable detailQuery = session.get(TodoTable.class, todoNo, lockMode);

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

	public long save(JsonObject json) {
		TodoTable todoTable = new TodoTable();

		todoTable.setUserNo(Integer.parseInt(json.get("userno").toString()));
		todoTable.setTitle(json.get("title").toString());
		todoTable.setContent(json.get("content").toString());
		long todoId = MainRepository.save(todoTable);
		
		return todoId;
	}
}
