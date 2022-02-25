package com.rest.pojo.collection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FolderBaseResponse extends FolderBase{


    List<RequestRootBaseResponse> item;


    public FolderBaseResponse(){

    }

    public FolderBaseResponse(String name, List<RequestRootBaseResponse> item){
        super(name);
        this.item = item;

    }



    public List<RequestRootBaseResponse> getItem() {
        return item;
    }

    public void setItem(List<RequestRootBaseResponse> item) {
        this.item = item;
    }






}
