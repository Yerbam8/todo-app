package kg.todoapp.project;

import kg.todoapp.model.Project;
import kg.todoapp.model.TaskGroup;
import kg.todoapp.model.TaskGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final TaskGroupRepository taskGroupRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, TaskGroupRepository taskGroupRepository) {
        this.projectRepository = projectRepository;
        this.taskGroupRepository = taskGroupRepository;
    }


    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }


    public Project createGroup(int projectId, LocalDateTime deadline) {
        if(taskGroupRepository.existsByDoneIsFalseAndProject_Id(projectId)){
            throw new
        }
    }
}
