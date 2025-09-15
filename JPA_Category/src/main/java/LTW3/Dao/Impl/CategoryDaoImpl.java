package LTW3.Dao.Impl;

import java.util.List;

import LTW3.Configs.JPA_Config;
import LTW3.Dao.CategoryDao;
import LTW3.Entity.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class CategoryDaoImpl implements CategoryDao {

    @Override
    public void insert(Category category) {
        EntityManager enma = JPA_Config.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            enma.persist(category); // ✅ luôn dùng persist cho insert
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (trans.isActive()) {
                trans.rollback();
            }
            throw e;
        } finally {
            enma.close();
        }
    }

    @Override
    public void update(Category category) {
        EntityManager enma = JPA_Config.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            enma.merge(category); // ✅ chỉ merge khi update
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (trans.isActive()) {
                trans.rollback();
            }
            throw e;
        } finally {
            enma.close();
        }
    }

    @Override
    public void delete(int cateid) throws Exception {
        EntityManager enma = JPA_Config.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            Category category = enma.find(Category.class, cateid);
            if (category != null) {
                enma.remove(category);
            } else {
                throw new Exception("Không tìm thấy category với id = " + cateid);
            }
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (trans.isActive()) {
                trans.rollback();
            }
            throw e;
        } finally {
            enma.close();
        }
    }

    @Override
    public Category findById(int categoryId) {
        EntityManager enma = JPA_Config.getEntityManager();
        try {
            return enma.find(Category.class, categoryId);
        } finally {
            enma.close();
        }
    }

    @Override
    public List<Category> findAll() {
        EntityManager enma = JPA_Config.getEntityManager();
        try {
            TypedQuery<Category> query = enma.createNamedQuery("Category.findAll", Category.class);
            return query.getResultList();
        } finally {
            enma.close();
        }
    }

    @Override
    public List<Category> findAll(int page, int pagesize) {
        EntityManager enma = JPA_Config.getEntityManager();
        try {
            TypedQuery<Category> query = enma.createNamedQuery("Category.findAll", Category.class);
            query.setFirstResult(page * pagesize);
            query.setMaxResults(pagesize);
            return query.getResultList();
        } finally {
            enma.close();
        }
    }

    @Override
    public List<Category> findByCategoryname(String catname) {
        EntityManager emna = JPA_Config.getEntityManager();
        try {
            String jpql = "SELECT c FROM Category c WHERE c.categoryName LIKE :catname";
            TypedQuery<Category> query = emna.createQuery(jpql, Category.class);
            query.setParameter("catname", "%" + catname + "%");
            return query.getResultList();
        } finally {
            emna.close();
        }
    }

    @Override
    public int count() {
        EntityManager emna = JPA_Config.getEntityManager();
        try {
            String jpql = "SELECT COUNT(c) FROM Category c";
            Query query = emna.createQuery(jpql);
            Long result = (Long) query.getSingleResult();
            return result.intValue();
        } finally {
            emna.close();
        }
    }

    @Override
    public List<Category> findByUserId(int userId) {
        EntityManager emna = JPA_Config.getEntityManager();
        try {
            String jpql = "SELECT c FROM Category c WHERE c.user.userId = :userId";
            TypedQuery<Category> query = emna.createQuery(jpql, Category.class);
            query.setParameter("userId", userId);
            return query.getResultList();
        } finally {
            emna.close();
        }
    }
}
