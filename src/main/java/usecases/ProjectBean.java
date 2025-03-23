package usecases;

import entities.Project;
import lombok.Getter;
import lombok.Setter;
import persistence.ProjectDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Model
public class ProjectBean implements Serializable {

    @Inject
    private ProjectDAO projectDAO;

    @Getter
    @Setter
    private Project project = new Project();

    @Getter
    private List<Project> projects;

    @PostConstruct
    public void init() {
        loadProjects();
    }

    @Transactional
    public void create() {
        projectDAO.persist(project);
    }

    private void loadProjects() {
        projects = projectDAO.findAll();
    }
}
