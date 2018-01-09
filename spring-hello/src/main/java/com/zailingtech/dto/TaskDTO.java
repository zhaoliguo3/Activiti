package com.zailingtech.dto;

import java.util.Date;

public class TaskDTO {
    private String id;
    private String processInsId; //流程实例id
    private String name;
    private Date startTime;
    private Date endTime;
    private String assignee;

    public TaskDTO(String id, String name,String processInsId,String assignee,Date... dates) {
        this.id = id;
        this.name = name;
        this.processInsId = processInsId;
        this.assignee=assignee;
        if (dates!=null){
            switch (dates.length){
                case 1:
                    startTime = dates[0];
                    break;
                case 2:
                    startTime = dates[0];
                    endTime = dates[1];
                    break;
                default:
                    break;
            }
        }
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getProcessInsId() {
        return processInsId;
    }

    public void setProcessInsId(String processInsId) {
        this.processInsId = processInsId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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

    @Override
    public String toString() {
        return "TaskDTO{" +
                "id='" + id + '\'' +
                ", processInsId='" + processInsId + '\'' +
                ", name='" + name + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
