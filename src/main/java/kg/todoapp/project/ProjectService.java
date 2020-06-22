package kg.todoapp.project;

import kg.todoapp.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    private ProjectRepository projectRepository;
    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> findAll(){
        return projectRepository.findAll();
    }

    public Project createProject(Project project){
        if(projectRepository.save(project))
    }

}
