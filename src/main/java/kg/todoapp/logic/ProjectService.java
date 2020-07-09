package kg.todoapp.logic;

import kg.todoapp.TaskConfigurationProperties;
import kg.todoapp.model.Project;
import kg.todoapp.model.Task;
import kg.todoapp.model.TaskGroup;
import kg.todoapp.model.TaskGroupRepository;
import kg.todoapp.model.projection.GroupReadModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final TaskGroupRepository taskGroupRepository;
    private final TaskConfigurationProperties taskConfigurationProperties;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, TaskGroupRepository taskGroupRepository, TaskConfigurationProperties taskConfigurationProperties) {
        this.projectRepository = projectRepository;
        this.taskGroupRepository = taskGroupRepository;
        this.taskConfigurationProperties = taskConfigurationProperties;
    }

    public List<Project> readAll() {
        return projectRepository.findAll();
    }

    public Project createProject(final Project toSave) {
        return projectRepository.save(toSave);
    }


    public GroupReadModel createGroup(int projectId, LocalDateTime deadline) {
        if (!taskConfigurationProperties.isAllowMultipleTasksFromTemplate() && taskGroupRepository.existsByDoneIsFalseAndProject_Id(projectId)) {
            throw new IllegalArgumentException("Only one undone group from project is allowed");
        }
        TaskGroup resultGroup =projectRepository.findById(projectId)
                .map(project -> {
                    TaskGroup result = new TaskGroup();
                    result.setDescription(project.getDescription());
                    result.setTasks(project.getProjectSteps().stream()
                            .map(step -> new Task(step.getDescription(), deadline.plusDays(step.getDaysToDeadline()))).collect(Collectors.toSet())

                    );
                    return result;
                }).orElseThrow(()->new IllegalArgumentException("Project with given id not found"));
    return  new GroupReadModel(resultGroup);
    }
}
