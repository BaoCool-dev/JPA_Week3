package LTW3.Dao.Impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.List;

import LTW3.Configs.JPA_Config;
import LTW3.Dao.UserDao;
import LTW3.Entity.User;

public class UserDaoImpl implements UserDao {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("datasource");

	@Override
	public User get(String userName) {
		EntityManager em = emf.createEntityManager();
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
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(user);
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			e.printStackTrace();
			return false;
		} finally {
			em.close();
		}
	}

	@Override
	public boolean checkExistEmail(String email) {
		EntityManager em = emf.createEntityManager();
		try {
			TypedQuery<Long> query = em.createQuery("SELECT COUNT(u) FROM User u WHERE u.email = :email", Long.class);
			query.setParameter("email", email);
			Long count = query.getSingleResult();
			return count > 0;
		} finally {
			em.close();
		}
	}

	@Override
	public boolean checkExistUsername(String username) {
		EntityManager em = emf.createEntityManager();
		try {
			TypedQuery<Long> query = em.createQuery("SELECT COUNT(u) FROM User u WHERE u.userName = :username",
					Long.class);
			query.setParameter("username", username);
			Long count = query.getSingleResult();
			return count > 0;
		} finally {
			em.close();
		}
	}

	@Override
	public boolean checkExistPhone(String phone) {
		EntityManager em = emf.createEntityManager();
		try {
			TypedQuery<Long> query = em.createQuery("SELECT COUNT(u) FROM User u WHERE u.phone = :phone", Long.class);
			query.setParameter("phone", phone);
			Long count = query.getSingleResult();
			return count > 0;
		} finally {
			em.close();
		}
	}

	@Override
	public boolean sendEmail(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void updateStatus(User user) {
		EntityManager em = JPA_Config.getEntityManager(); // class config bạn đã có
		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();

			// Tìm user theo email
			User u = em.createQuery("SELECT u FROM Users u WHERE u.email = :email", User.class)
					.setParameter("email", user.getEmail()).getSingleResult();

			// Cập nhật thuộc tính
			u.setStatus(user.getStatus());
			u.setCode(user.getCode());

			// merge để cập nhật
			em.merge(u);

			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (trans.isActive()) {
				trans.rollback();
			}
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

	        // Lưu đối tượng user vào DB
	        em.persist(user);

	        trans.commit();
	    } catch (Exception e) {
	        e.printStackTrace();
	        if (trans.isActive()) {
	            trans.rollback();
	        }
	    } finally {
	        em.close();
	    }
	}
}
