package LTW3.Service;

import java.util.List;


import LTW3.Entity.Category;

public interface CategoryService {

	int count();

	List<Category> finAll(int page, int pagesize);

	List<Category> findByCategoryname(String catname);

	List<Category> findAll();

	Category findById(int cateid);

	void delete(int cateid) throws Exception;

	void update(Category category);

	void insert(Category category);

	
	
}
