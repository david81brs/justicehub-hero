package com.pegasus.justicehub.models;


import java.util.List;

public class JusticeEventResponse {

    private List<JusticeEvent> all;

    public JusticeEventResponse(List<JusticeEvent> justiceEventList){
        this.all = justiceEventList;
    }

    public List<JusticeEvent> getAll() {
        return all;
    }

    public void setAll(List<JusticeEvent> all) {
        this.all = all;
    }
}
