package com.rest.pojo.collection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CollectionBaseRequest extends CollectionBase{


    List<FolderBaseRequest> item;

    public CollectionBaseRequest(){

    }
    public CollectionBaseRequest(Info info, List<FolderBaseRequest> item){
        super(info);
        this.item=item;
    }

    public List<FolderBaseRequest> getItem() {
        return item;
    }

    public void setItem(List<FolderBaseRequest> item) {
        this.item = item;
    }




}
