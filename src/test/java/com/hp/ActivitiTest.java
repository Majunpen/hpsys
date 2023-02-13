package com.hp;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @Author: MJP
 * @Date: 2023/1/30 - 01 - 30 - 17:18
 * @Description: com.hp
 * @version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivitiTest {
    //部署流程
    @Autowired
    private RepositoryService repositoryService;
    @Test
    public void initDeploymentBPMN(){
        String fileName="bpmn/employee_holiday2.bpmn";
        //将定义好的流程部署到activity
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource(fileName)
                .name("员工请求流程")
                .deploy();
        //流程定义部署好后，可以获取到部署信息
        System.out.println("流程部署id--》" +deployment.getId());
        System.out.println("流程部署的时间-->" +
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(deployment.getDeploymentTime()));
        System.out.println("流程部署名称-->" + deployment.getName());

    }

    //启动流程控制
    @Autowired
    private RuntimeService runtimeService;
    @Test
    public void startProcessInstance(){
        //根据流程定义的key启动流程实例
        String processDefinitionKey ="hr_employee_holiday";
        //创建流程对象
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey);
        System.out.println("流程定义的id-->"+processInstance.getProcessDefinitionId());
        System.out.println("流程实例id-->"+processInstance.getProcessInstanceId());
    }

    //查看个人待办任务
    @Autowired
    private TaskService taskService;
    @Test
    public void TestPersonalTaskList(){
        String definitionKey="employee_holiday2";
        List<Task> tasks = taskService.createTaskQuery().processDefinitionKey(definitionKey).taskAssignee("zs").list();
    //遍历任务列表
        tasks.forEach(task->{
            System.out.println("任务id-->"+task.getId());
            System.out.println("任务的分配人-->"+task.getAssignee());
            System.out.println("任务的分配名称-->"+task.getName());
        });
    }

        //开始任务
        //任务办理
        @Test
        public void compleTask() {
            String definitionKey = "employee_holiday2";
            //查询到待办任务
            List<Task> tasks = taskService.createTaskQuery().processDefinitionKey(definitionKey)
                    .taskAssignee("zs").list();
            System.out.println("------待办任务执行前任务列表-----");
            tasks.forEach(task -> {
                System.out.println("任务id-->" + task.getId());
                System.out.println("任务的分配人-->" + task.getAssignee());
                System.out.println("任务的名称-->" + task.getName());
            });

            //办理任务
            tasks.forEach(task -> {
                //根据任务id，办理任务
                taskService.complete(task.getId());
            });

            System.out.println("------办理任务后任务列表-----");
            tasks = taskService.createTaskQuery().processDefinitionKey(definitionKey)
                    .taskAssignee("zs").list();
            tasks.forEach(task -> {
                System.out.println("任务id-->" + task.getId());
                System.out.println("任务的分配人-->" + task.getAssignee());
                System.out.println("任务的名称-->" + task.getName());
            });
        }


        //流程定义删除
    @Test
    public void deleteDeployment(){
        //流程部署的id
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().list();
        list.forEach(processDefinition -> {
            System.out.println("流程部署id-->"+processDefinition.getDeploymentId());
            /*设置true ，级联删除流程定义
            * 设置false非级联删除
            * 如果什么都不写，流程定义已经有流程实例则删除出错*/
            //repositoryService.deleteDeployment(processDefinition.getDeploymentId(),true);

        });
        //String deploymentId="566330e7-a08c-11ed-a7ce-4a89e7e7e5cd";
        String deploymentId="bbdc4150-a082-11ed-91b0-4a89e7e7e5cd";
        repositoryService.deleteDeployment(deploymentId,true);
    }




}
