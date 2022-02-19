package com.rest.pojo.workspace;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.HashMap;

//@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@JsonIgnoreProperties(value="id",allowSetters = true)
public class WorkSpace {

    //@JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonIgnore
    private int i;
    private String name;
    private String type;
    private String description;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String id;
    //@JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonIgnore
    HashMap<String,String> myhashmap=new HashMap<String,String>();

    public WorkSpace(){

    }

    public WorkSpace(String name, String type, String description){
        this.name=name;
        this.type=type;
        this.description=description;
    }

    public HashMap<String, String> getMyhashmap() {
        return myhashmap;
    }

    public void setMyhashmap(HashMap<String, String> myhashmap) {
        this.myhashmap = myhashmap;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }




}
