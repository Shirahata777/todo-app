package com.github.shirahata777.dao;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.github.shirahata777.query.TodoQuery;

public class TodoDao {

	/**
	 * セッション
	 */
	private Session session;

	/**
     * コンストラクタ
     * @param session セッション
     */
    public TodoDao(Session session){
        this.session = session;
    }

	/**
	 * 指定したIDのを取得する
	 * 
	 * @param id   ID
	 * @param lock 行ロックを行うかどうか
	 * @return Employee
	 * @throws SQLException
	 */
//	public TodoDao get(Integer id, boolean lock) throws HibernateException {
//		LockMode lockMode = LockMode.NONE;
//		if (lock) {
//			lockMode = LockMode.UPGRADE;
//		}
//		return (Employee) session.get(Employee.class, id, lockMode);
//	}

	/**
	 * 部門名に対応するのリストを返す
	 * 
	 * @param departmentName 部門名
	 * @param lock           行ロックを行うかどうか
	 * @return List
	 * @throws HibernateException
	 */
//	public List getListByDepartmentName(String departmentName, boolean lock) throws HibernateException {
//		Query query = session.getNamedQuery("entity.Employee.getListByDepartmentName");
//		query.setParameter("departmentName", departmentName);
//		if (lock) {
//			query.setLockMode("e", LockMode.UPGRADE);
//		}
//
//		return query.list();
//	}

	/**
	 * 追加メソッド
	 * 
	 * @param todo 
	 */
	public void insert(TodoQuery todo) {
		session.save(todo);
	}

	/**
	 * 削除メソッド
	 * 
	 * @param emp 
	 */
	public void delete(TodoQuery todo) {
		session.delete(todo);
	}

	/**
	 * 更新メソッド
	 * 
	 * @param emp 
	 */
	public void update(TodoQuery todo){
		 session.update(todo);
	}

	/**
	 * サンプルコードメイン
	 * 
	 * @param query
	 */
	public void main(TodoQuery query) throws Exception {
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
	}
}
