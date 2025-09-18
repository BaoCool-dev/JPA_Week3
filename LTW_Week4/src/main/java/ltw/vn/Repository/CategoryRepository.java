package ltw.vn.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import ltw.vn.Entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

	// Tìm kiếm theo tên
	List<Category> findByCategoryNameContaining(String name);

	// Tìm kiếm + phân trang
	Page<Category> findByCategoryNameContaining(String name, Pageable pageable);
}
