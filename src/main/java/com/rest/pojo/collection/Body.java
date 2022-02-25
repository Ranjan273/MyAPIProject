package com.rest.pojo.collection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class Body {

    private String mode;
    private String raw;
    @JsonIgnoreProperties(ignoreUnknown = true)
    public Body(){

    }
    public Body(String mode,String raw){
        this.raw=raw;
        this.mode=mode;

    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }



}
