package ltw.vn.Entity;

import java.io.Serializable;
import java.util.Set;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "categories") 
public class Category implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	private Integer categoryId;

	@Column(name = "categorycode", columnDefinition = "nvarchar(255)")
	private String categoryCode;

	@Column(name = "categoryname", columnDefinition = "nvarchar(255)")
	private String categoryName;

	@Column(name = "images")
	private String images;

	@Column(name = "status")
	private Boolean status;

	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Video> videos;
}
