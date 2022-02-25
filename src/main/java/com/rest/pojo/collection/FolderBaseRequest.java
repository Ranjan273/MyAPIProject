package com.rest.pojo.collection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FolderBaseRequest extends FolderBase{


    List<RequestRootBaseRequest> item;


    public FolderBaseRequest(){

    }

    public FolderBaseRequest(String name,List<RequestRootBaseRequest> item){
        super(name);
        this.item = item;

    }



    public List<RequestRootBaseRequest> getItem() {
        return item;
    }

    public void setItem(List<RequestRootBaseRequest> item) {
        this.item = item;
    }






}
