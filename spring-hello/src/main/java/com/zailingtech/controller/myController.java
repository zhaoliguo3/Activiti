package com.zailingtech.controller;

import com.zailingtech.dto.ResultResponse;
import com.zailingtech.dto.TaskDTO;
import com.zailingtech.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class myController {

    @Autowired
    CommonService commonService;

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    @ResponseBody
    public String hello(){
        return "hello world?";
    }

    //启动流程实例
    @PostMapping("/start")
    public ResultResponse start(@RequestParam String key, @RequestParam(required = false) Map map){
        commonService.startProcessInstance(key,map);
       return ResultResponse.createSuccessResponse("流程实例已启动");
    }

    //查询当前个人任务
    @GetMapping("/task")
    public ResultResponse<List<TaskDTO>> taskQuery(@RequestParam String assignee){
        return ResultResponse.createSuccessResponse(commonService.getTask(assignee));
    }

    //完成个人任务
    @PostMapping("/complete")
    public ResultResponse complite(@RequestParam String taskId,@RequestParam(required = false) Map map){
       commonService.completeTask(taskId,map);

        return ResultResponse.createSuccessResponse("任务已完成");
    }

    //查询历史任务
    @GetMapping("/his")
    public ResultResponse<List<TaskDTO>> getHisTask(String assignee, String processId){
        return ResultResponse.createSuccessResponse(commonService.getHisTask(assignee,processId));
    }

    //查询流程状态
    @GetMapping("/status")
    public ResultResponse status(@RequestParam String processInstanceId){
        String status = commonService.getStatus(processInstanceId);
        return ResultResponse.createSuccessResponse(status);
    }

    //查询当前人的组任务
    @GetMapping("/group")
    public ResultResponse<TaskDTO> getGroupTask(@RequestParam String assignee){
        return ResultResponse.createSuccessResponse(commonService.getGroupTask(assignee));
    }

    //查询组任务的候选者
    @GetMapping("/canditate")
    public ResultResponse<List> getAll(@RequestParam String taskId){
        return ResultResponse.createSuccessResponse(commonService.getAllAssignee(taskId));
    }

    //拾取组任务
    @PostMapping("/claim")
    public ResultResponse claim(@RequestParam String taskId,@RequestParam String assignee){
        commonService.claim(taskId,assignee);
        return ResultResponse.createSuccessResponse("任务已拾取");
    }


}
