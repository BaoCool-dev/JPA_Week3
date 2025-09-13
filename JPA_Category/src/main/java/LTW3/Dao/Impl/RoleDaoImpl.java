package LTW3.Dao.Impl;

import LTW3.Dao.RoleDao;
import LTW3.Entity.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class RoleDaoImpl implements RoleDao {
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("datasource");

    @Override
    public Role findByName(String name) {
		EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Role> query = em.createQuery("SELECT r FROM Role r WHERE r.name = :name", Role.class);
            query.setParameter("name", name);
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}
