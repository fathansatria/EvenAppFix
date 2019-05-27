package com.example.eventapp.Model;

import java.util.Date;

public class EventModel {

    private String eventName;
    private Date eventDate;
    private String eventPlace;
    private String description;

    //setter
    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setEventPlace(String eventPlace) {
        this.eventPlace = eventPlace;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    //getter
    public String getEventName() {
        return eventName;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public String getEventPlace() {
        return eventPlace;
    }

    public String getDescription() {
        return description;
    }
}
