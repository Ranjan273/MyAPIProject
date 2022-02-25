package com.rest.pojo.collection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CollectionRootRequest extends CollectionRootBase {

    CollectionBaseRequest collection;

    public CollectionRootRequest(){

    }

    public CollectionRootRequest(CollectionBaseRequest collection){
        this.collection=collection;
    }

    public CollectionBaseRequest getCollection() {
        return collection;
    }

    public void setCollection(CollectionBaseRequest collection) {
        this.collection = collection;
    }


}
