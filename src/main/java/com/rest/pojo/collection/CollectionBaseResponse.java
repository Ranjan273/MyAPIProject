package com.rest.pojo.collection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CollectionBaseResponse extends CollectionBase {


    List<FolderBaseResponse> item;

    public CollectionBaseResponse(){

    }
    public CollectionBaseResponse(Info info, List<FolderBaseResponse> item){

        super(info);
        this.item=item;
    }

    public List<FolderBaseResponse> getItem() {
        return item;
    }

    public void setItem(List<FolderBaseResponse> item) {
        this.item = item;
    }




}
