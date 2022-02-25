package com.rest.pojo.collection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Info {

    private String name;
    private String description;
    private String schema;
    private String postmanID;

    public Info(){

    }

    public Info(String name,String description,String schema,String postmanID){
        this.name=name;
        this.description=description;
        this.schema=schema;
        this.postmanID=postmanID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getPostmanID() {
        return postmanID;
    }

    public void setPostmanID(String postmanID) {
        this.postmanID = postmanID;
    }


}
