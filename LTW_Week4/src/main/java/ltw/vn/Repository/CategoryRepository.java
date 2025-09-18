package ltw.vn.Repository;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import ltw.vn.Entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer > {


	 //Tìm Kiếm theo nội dung tên


	 List<Category> findByCategoryNameContaining(String name);


	 //Tìm kiếm và Phân trang


	 Page<Category> findByCategoryNameContaining(String name,Pageable pageable);


	}

