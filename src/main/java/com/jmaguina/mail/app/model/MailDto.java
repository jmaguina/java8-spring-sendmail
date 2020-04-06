package com.jmaguina.mail.app.model;

/**
 * Created by Jorge Maguina on 28/10/2017.
 */
public class MailDto {

    public String name;
    public String email;
    public String description;

    public MailDto() {
    }

    public MailDto(String name, String email, String description) {
        this.name = name;
        this.email = email;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
