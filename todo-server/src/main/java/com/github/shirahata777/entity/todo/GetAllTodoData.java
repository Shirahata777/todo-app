package com.github.shirahata777.entity.todo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.shirahata777.dao.table.todo.TodoTable;

public class GetAllTodoData {
	
	private static Logger log = LoggerFactory.getLogger(GetAllTodoData.class);

	public List<TodoTable> accept(int limit, int offset) {
		Configuration cfg = null;
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		List<TodoTable> resultList = new ArrayList<>();
		try {
			cfg = new Configuration().configure();
			sessionFactory = cfg.buildSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

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
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return resultList;

	}
}
