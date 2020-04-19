package kg.todoapp.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "project")
    private List<TaskGroup> taskGroups;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "project")
    private Set<ProjectStep> projectSteps;
    public Project() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
