package top.yinjinbiao.video.flowable.controller;

import org.flowable.engine.repository.Deployment;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import top.yinjinbiao.video.flowable.dto.DeploymentDTO;
import top.yinjinbiao.video.flowable.service.MyWorkflowService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MyRestController {

    @Autowired
    private MyWorkflowService myWorkflowService;

    @PostMapping(value="/createDeploy")
    public void createDeploy() {
        myWorkflowService.createDeployment();
    }

    @GetMapping(value="/pageAllDeployment")
    public List<DeploymentDTO> pageAllDeployment() {
        List<DeploymentDTO> list = new ArrayList<>();
        List<Deployment> deployments = myWorkflowService.pageAllDeployment(0, 10);
        for (Deployment deployment: deployments) {
            DeploymentDTO dto = new DeploymentDTO();
            dto.setCategory(deployment.getCategory());
            dto.setDeploymentTime(deployment.getDeploymentTime());
            dto.setDerivedFrom(deployment.getDerivedFrom());
            dto.setEngineVersion(deployment.getEngineVersion());
            dto.setDerivedFromRoot(deployment.getDerivedFromRoot());
            dto.setId(deployment.getId());
            list.add(dto);
        }
        return list;
    }

    @PostMapping(value="/process")
    public void startProcessInstance() {
        myWorkflowService.startProcess();
    }

    @RequestMapping(value="/tasks", method= RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public List<TaskRepresentation> getTasks(@RequestParam String assignee) {
        List<Task> tasks = myWorkflowService.getTasks(assignee);
        List<TaskRepresentation> dtos = new ArrayList<TaskRepresentation>();
        for (Task task : tasks) {
            dtos.add(new TaskRepresentation(task.getId(), task.getName()));
        }
        return dtos;
    }

    static class TaskRepresentation {

        private String id;
        private String name;

        public TaskRepresentation(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }

    }

}