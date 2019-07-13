package com.pegasus.justicehub.models;


import java.util.List;

public class JusticeEventResponse {

    private List<JusticeEvent> all;

    public JusticeEventResponse(List<JusticeEvent> justiceEventList){
        this.all = justiceEventList;
    }

}
