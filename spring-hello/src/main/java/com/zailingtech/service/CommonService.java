package com.zailingtech.service;

import com.zailingtech.dto.TaskDTO;
import org.activiti.engine.task.IdentityLink;

import java.util.List;
import java.util.Map;


public interface CommonService {


    /**
     * 启动流程实例
     * @param map 指定任务的办理人
     * @param key 流程的Id
     */
    void startProcessInstance(String key,Map map);

    /**
     * 查询个人任务
     * @param assignee 办理人
     * @return
     */
    List<TaskDTO> getTask(String assignee);

    /**
     * 完成任务
     * @param map 流程变量可选
     * @param taskId 任务Id
     */
    void completeTask(String taskId,Map map);

    /**
     * 个人历史任务查询
     * @param assignee  办理人
     * @param processId 流程实例Id
     * @return
     */
    List<TaskDTO> getHisTask(String assignee, String processId);

    /**
     * 查询流程状态
     * @param processInstanceId 流程实例Id
     */
    String  getStatus(String processInstanceId);

    /**
     * 查询当前人的组任务
     * @param assignee
     * @return
     */
    List<TaskDTO> getGroupTask(String assignee);

    /**
     * 查询任务的候选者
     * @param taskId
     * @return
     */
    List<IdentityLink> getAllAssignee(String taskId);

    /**
     * 拾取组任务
     * @param taskId   指定任务Id
     * @param assignee  指定办理人
     */
    void claim(String taskId,String assignee);

}
