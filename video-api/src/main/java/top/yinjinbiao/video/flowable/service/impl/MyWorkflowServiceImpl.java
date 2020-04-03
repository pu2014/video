package top.yinjinbiao.video.flowable.service.impl;

import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.repository.Deployment;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.yinjinbiao.video.flowable.service.MyWorkflowService;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MyWorkflowServiceImpl implements MyWorkflowService {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    public void createDeployment(){
        repositoryService.createDeployment().addClasspathResource("flowable/bpmn/one-task-process.bpmn20.xml").deploy();
    }

    public List<Deployment> pageAllDeployment(int start, int count){
        return repositoryService.createDeploymentQuery().orderByDeploymentTime().desc().listPage(start, count);
    }

    public void startProcess() {
        runtimeService.startProcessInstanceByKey("oneTaskProcess");
    }

    public List<Task> getTasks(String assignee) {
        return taskService.createTaskQuery().taskAssignee(assignee).list();
    }

}