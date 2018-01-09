package com.zailingtech.service.impl;

import com.zailingtech.dto.TaskDTO;
import com.zailingtech.service.CommonService;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
 class CommonServiceImpl implements CommonService {
    //注入为我们自动配置好的服务
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    HistoryService historyService;
    @Autowired
    RepositoryService repositoryService;
    @Autowired
    ProcessEngineConfiguration processEngineConfiguration;
    @Autowired
    ProcessEngineFactoryBean processEngine;

    @Override
    public void startProcessInstance(String key,Map map) {
        if (map == null)
            runtimeService.startProcessInstanceByKey(key);
        else
            runtimeService.startProcessInstanceByKey(key, map);

    }

    @Override
    public List<TaskDTO> getTask(String assignee) {
        List<Task> list=taskService.createTaskQuery().taskAssignee(assignee).list();
        List<TaskDTO> list1 = new ArrayList<>();
        for (Task task : list)
            list1.add(new TaskDTO(task.getId(),task.getName(),task.getProcessInstanceId(),task.getAssignee(),task.getCreateTime()));
        return list1;
    }

    @Override
    public void completeTask(String taskId,Map map) {
        if (map == null)
            taskService.complete(taskId);
        else
            taskService.complete(taskId,map);
    }

    @Override
    public List<TaskDTO> getHisTask(String assignee, String processId) {
        List<HistoricTaskInstance> list=null;
        if (assignee == null && processId != null)
            list=  historyService.createHistoricTaskInstanceQuery().processInstanceId(processId).list();
        if(assignee != null && processId == null)
            list= historyService.createHistoricTaskInstanceQuery().taskAssignee(assignee).list();
        if (assignee != null && processId != null)
            list=historyService.createHistoricTaskInstanceQuery().taskAssignee(assignee).processInstanceId(processId).list();


        List<TaskDTO> list1 = new ArrayList<>();
        for (HistoricTaskInstance task : list)
            list1.add(new TaskDTO(task.getId(),task.getName(),task.getProcessInstanceId(),task.getAssignee(),task.getCreateTime(),task.getEndTime()));
        return list1;
    }

    @Override
    public String getStatus(String processInstanceId) {
        ProcessInstance pi=runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        if(pi!=null){
            return "流程还在执行";
        }
        else return "流程已结束";
    }

    @Override
    public List<TaskDTO> getGroupTask(String assignee) {
      List<Task> list=  taskService.createTaskQuery().taskCandidateUser(assignee).list();
        List<TaskDTO> list1 = new ArrayList<>();
        for (Task task : list)
            list1.add(new TaskDTO(task.getId(),task.getName(),task.getProcessInstanceId(),task.getAssignee(),task.getCreateTime()));
        return list1;

    }

    @Override
    public List getAllAssignee(String taskId) {
        List<IdentityLink> allAssignee=taskService.getIdentityLinksForTask(taskId);
        List list=new ArrayList();
        for (IdentityLink identityLink:allAssignee) {
            list.add(identityLink.getUserId());
        }
        return list;
    }

    @Override
    public void claim(String taskId, String assignee) {
        taskService.claim(taskId,assignee);
    }


}
