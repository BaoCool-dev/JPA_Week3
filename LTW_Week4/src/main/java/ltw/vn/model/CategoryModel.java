package ltw.vn.model;

import org.hibernate.validator.constraints.Length;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@SuppressWarnings("deprecation")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class CategoryModel {
	private Long catergoryId;
	private String categorycode;
	@NotEmpty
	@Length(min=5)
	private String categoryname;
	private String images;
	private boolean status;
	private MultipartFile imageFile;
}
