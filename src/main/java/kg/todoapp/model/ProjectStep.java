package kg.todoapp.model;

import javax.persistence.*;

@Entity
@Table(name = "project_steps")
public class ProjectStep {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    @Column(name = "days_to_deadline")
    private Integer daysToDeadline;
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

}
