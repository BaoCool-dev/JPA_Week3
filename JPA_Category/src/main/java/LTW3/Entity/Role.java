package LTW3.Entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    private String name; // ADMIN, USER

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private List<User> users;

    // getter setter
}
