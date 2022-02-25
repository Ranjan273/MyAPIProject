package com.rest.pojo.collection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CollectionRootResponse extends CollectionRootBase{

    CollectionBaseResponse collection;

    public CollectionRootResponse(){

    }

    public CollectionRootResponse(CollectionBaseResponse collection){
        this.collection=collection;
    }

    public CollectionBaseResponse getCollection() {
        return collection;
    }

    public void setCollection(CollectionBaseResponse collection) {
        this.collection = collection;
    }


}
