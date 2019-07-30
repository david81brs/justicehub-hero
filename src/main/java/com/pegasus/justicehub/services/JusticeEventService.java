package com.pegasus.justicehub.services;

import com.pegasus.justicehub.repository.JusticeEventRepository;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@Service
public class JusticeEventService {

    private JusticeEventRepository justiceEventRepository;

    public JusticeEventService(JusticeEventRepository justiceEventRepository){
        this.justiceEventRepository = justiceEventRepository;
    }

    public String getSumPeopleAttended(){
        DecimalFormat df = new DecimalFormat("#,##0.0");
        return df.format(justiceEventRepository.getSumPeopleAttended());
    }
}
