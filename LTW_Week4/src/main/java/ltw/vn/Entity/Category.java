package ltw.vn.Entity;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;

import jakarta.persistence.GenerationType;

import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;

import lombok.Data;

import lombok.NoArgsConstructor;

@AllArgsConstructor

@NoArgsConstructor

@Data

@Entity

@Table(name = "Category")

public class Category implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CategoryId")
	private Long catergoryId;

	@Column(name = "CategoryCode", columnDefinition = "nvarchar(255)")
	private String categorycode;

	@Column(name = "CategoryName", columnDefinition = "nvarchar(255)")
	private String categoryname;

	@Column(name = "Images")
	private String images;

	@Column(name = "Status")
	private boolean status;
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	private Set<Video> videos;

	public Long getCatergoryId() {
		return catergoryId;
	}

}