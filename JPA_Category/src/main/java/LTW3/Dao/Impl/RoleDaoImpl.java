package LTW3.Dao.Impl;

import LTW3.Dao.RoleDao;
import LTW3.Entity.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class RoleDaoImpl implements RoleDao {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("datasource");

	@Override
	public Role findById(int roleId) {
		EntityManager em = emf.createEntityManager();
		try {
			return em.find(Role.class, roleId);
		} finally {
			em.close();
		}
	}

	@Override
	public Role findByName(String name) {
		EntityManager em = emf.createEntityManager();
		try {
			TypedQuery<Role> query = em.createQuery("SELECT r FROM Role r WHERE r.name = :name", Role.class);
			query.setParameter("name", name);
			List<Role> results = query.getResultList();
			return results.isEmpty() ? null : results.get(0);
		} finally {
			em.close();
		}
	}

	@Override
	public List<Role> findAll() {
		EntityManager em = emf.createEntityManager();
		try {
			TypedQuery<Role> query = em.createQuery("SELECT r FROM Role r", Role.class);
			return query.getResultList();
		} finally {
			em.close();
		}
	}

	@Override
	public void insert(Role role) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.persist(role);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (trans.isActive())
				trans.rollback();
		} finally {
			em.close();
		}
	}

	@Override
	public void update(Role role) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.merge(role);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (trans.isActive())
				trans.rollback();
		} finally {
			em.close();
		}
	}

	@Override
	public void delete(int roleId) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			Role role = em.find(Role.class, roleId);
			if (role != null) {
				em.remove(role);
			}
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (trans.isActive())
				trans.rollback();
		} finally {
			em.close();
		}
	}
}
