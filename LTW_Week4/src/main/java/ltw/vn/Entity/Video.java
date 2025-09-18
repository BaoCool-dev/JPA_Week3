package ltw.vn.Entity;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "videos")
public class Video implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "video_id")
	private Integer videoId;

	@Column(name = "active")
	private Boolean active;

	@Column(name = "description", columnDefinition = "nvarchar(MAX)")
	private String description;

	@Column(name = "poster", columnDefinition = "nvarchar(255)")
	private String poster;

	@Column(name = "title", nullable = false, columnDefinition = "nvarchar(255)")
	private String title;

	@Column(name = "views")
	private Integer views;

	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;
}
