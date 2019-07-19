package com.pegasus.justicehub.models;


import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="JusticeEvents")
@JsonRootName("JusticeEvents")
public class JusticeEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column
    private String informer;

    @Column
    private String contacPerson;

    @Column
    private String eventTitle;

    @Column
    private String eventLocation;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date eventStartDate;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date eventEndDate;

    @Column
    private long peopleAttended;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getInformer() {
        return informer;
    }

    public void setInformer(String informer) {
        this.informer = informer;
    }

    public String getContacPerson() {
        return contacPerson;
    }

    public void setContacPerson(String contacPerson) {
        this.contacPerson = contacPerson;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public Date getEventStartDate() {
        return eventStartDate;
    }

    public void setEventStartDate(Date eventStartDate) {
        this.eventStartDate = eventStartDate;
    }

    public Date getEventEndDate() {
        return eventEndDate;
    }

    public void setEventEndDate(Date eventEndDate) {
        this.eventEndDate = eventEndDate;
    }

    public long getPeopleAttended() {
        return peopleAttended;
    }

    public void setPeopleAttended(long peopleAttended) {
        this.peopleAttended = peopleAttended;
    }
}
