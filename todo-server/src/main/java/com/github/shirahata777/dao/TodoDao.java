package com.github.shirahata777.dao;


import org.hibernate.Session;

import com.github.shirahata777.dao.table.TodoTable;

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
	public void insert(TodoTable todo) {
		session.save(todo);
	}

	/**
	 * 削除メソッド
	 * 
	 * @param emp 
	 */
	public void delete(TodoTable todo) {
		session.delete(todo);
	}

	/**
	 * 更新メソッド
	 * 
	 * @param emp 
	 */
	public void update(TodoTable todo){
		 session.update(todo);
	}
	
		
}
