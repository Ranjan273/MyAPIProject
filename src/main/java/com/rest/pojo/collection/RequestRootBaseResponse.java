package com.rest.pojo.collection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestRootBaseResponse extends RequestRootBase{

    RequestBaseResponse request;

    public RequestRootBaseResponse(){

    }

    public RequestRootBaseResponse(String name, RequestBaseResponse request){
        super(name);
        this.request=request;
    }
    public RequestBaseResponse getRequest() {
        return request;
    }

    public void setRequest(RequestBaseResponse request) {
        this.request = request;
    }




}
