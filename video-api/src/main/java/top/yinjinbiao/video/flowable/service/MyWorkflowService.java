package top.yinjinbiao.video.flowable.service;

import org.flowable.engine.repository.Deployment;
import org.flowable.task.api.Task;

import java.util.List;

public interface MyWorkflowService {

    /**
     * 流程定义
     */
    void createDeployment();

    /**
     * 查询流程部署
     * @return
     */
    List<Deployment> pageAllDeployment(int start,int count);

    void startProcess();

    /**
     * 查询待办任务
     * @param assignee
     * @return
     */
    List<Task> getTasks(String assignee);
}
