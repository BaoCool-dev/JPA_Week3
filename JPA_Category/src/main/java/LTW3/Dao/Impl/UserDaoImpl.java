package LTW3.Dao.Impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;

import LTW3.Configs.JPA_Config;
import LTW3.Dao.UserDao;
import LTW3.Entity.User;

public class UserDaoImpl implements UserDao {

	@Override
	public User get(String userName) {
		EntityManager em = JPA_Config.getEntityManager();
		try {
			TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.userName = :userName", User.class);
			query.setParameter("userName", userName);
			List<User> results = query.getResultList();
			return results.isEmpty() ? null : results.get(0);
		} finally {
			em.close();
		}
	}

	@Override
	public boolean insert(User user) {
		EntityManager em = JPA_Config.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.persist(user);
			trans.commit();
			return true;
		} catch (Exception e) {
			if (trans.isActive())
				trans.rollback();
			System.err.println("Insert user failed: " + e.getMessage());
			e.printStackTrace();
			return false;
		} finally {
			em.close();
		}
	}

	@Override
	public boolean checkExistEmail(String email) {
		EntityManager em = JPA_Config.getEntityManager();
		try {
			TypedQuery<Long> query = em.createQuery("SELECT COUNT(u) FROM User u WHERE u.email = :email", Long.class);
			query.setParameter("email", email);
			return query.getSingleResult() > 0;
		} finally {
			em.close();
		}
	}

	@Override
	public boolean checkExistUsername(String username) {
		EntityManager em = JPA_Config.getEntityManager();
		try {
			TypedQuery<Long> query = em.createQuery("SELECT COUNT(u) FROM User u WHERE u.userName = :username",
					Long.class);
			query.setParameter("username", username);
			return query.getSingleResult() > 0;
		} finally {
			em.close();
		}
	}

	@Override
	public boolean checkExistPhone(String phone) {
		EntityManager em = JPA_Config.getEntityManager();
		try {
			TypedQuery<Long> query = em.createQuery("SELECT COUNT(u) FROM User u WHERE u.phone = :phone", Long.class);
			query.setParameter("phone", phone);
			return query.getSingleResult() > 0;
		} finally {
			em.close();
		}
	}

	@Override
	public void updateStatus(User user) {
		EntityManager em = JPA_Config.getEntityManager();
		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();

			// Tìm user theo email (dùng đúng tên entity)
			User u = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
					.setParameter("email", user.getEmail()).getSingleResult();

			// Cập nhật thuộc tính
			u.setStatus(user.getStatus());
			u.setCode(user.getCode());

			em.merge(u);
			trans.commit();
		} catch (Exception e) {
			if (trans.isActive())
				trans.rollback();
			System.err.println("Update status failed: " + e.getMessage());
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	@Override
	public void insertRegister(User user) {
		EntityManager em = JPA_Config.getEntityManager();
		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();
			em.persist(user);
			trans.commit();
		} catch (Exception e) {
			if (trans.isActive())
				trans.rollback();
			System.err.println("Register user failed: " + e.getMessage());
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	@Override
	public User findById(int userId) {
		EntityManager em = JPA_Config.getEntityManager();
		try {
			return em.find(User.class, userId);
		} finally {
			em.close();
		}
	}

	@Override
	public void update(User user) {
		EntityManager em = JPA_Config.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.merge(user);
			trans.commit();
		} catch (Exception e) {
			if (trans.isActive())
				trans.rollback();
			System.err.println("Update user failed: " + e.getMessage());
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	@Override
	public List<User> findAll() {
		EntityManager em = JPA_Config.getEntityManager();
		try {
			TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
			return query.getResultList();
		} finally {
			em.close();
		}
	}
}
