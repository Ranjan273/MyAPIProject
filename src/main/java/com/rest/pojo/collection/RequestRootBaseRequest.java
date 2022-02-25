package com.rest.pojo.collection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestRootBaseRequest extends RequestRootBase{

    RequestBaseRequest request;

    public RequestRootBaseRequest(){

    }

    public RequestRootBaseRequest(String name, RequestBaseRequest request){
        super(name);
        this.request=request;
    }

    public RequestBaseRequest getRequest() {
        return request;
    }

    public void setRequest(RequestBaseRequest request) {
        this.request = request;
    }




}
