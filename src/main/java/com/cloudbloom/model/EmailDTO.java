package com.cloudbloom.model;

import java.util.Map;

public class EmailDTO {

    private String to;
    private String subject;
    private Map<String, Object> emailData;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Map<String, Object> getEmailData() {
        return emailData;
    }

    public void setEmailData(Map<String, Object> emailData) {
        this.emailData = emailData;
    }
// Generate Getters and Setters...
}
