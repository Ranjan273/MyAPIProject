package com.rest.pojo.workspace;

public class WorkspaceRoot {

    WorkSpace workspace;
    public WorkspaceRoot(){

    }

    public  WorkspaceRoot(WorkSpace workspace){
        this.workspace=workspace;
    }

    public WorkSpace getWorkspace() {
        return workspace;
    }

    public void setWorkspace(WorkSpace workspace) {
        this.workspace = workspace;
    }





}
