package kg.todoapp.adapter;

import kg.todoapp.model.Project;
import kg.todoapp.logic.ProjectRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SqlProjectRepository extends JpaRepository<Project,Integer>, ProjectRepository {


    @Override
    @Query("from Project p join fetch p.projectSteps ")
    List<Project> findAll();
}
