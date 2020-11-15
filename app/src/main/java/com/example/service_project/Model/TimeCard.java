package com.example.service_project.Model;

public class TimeCard {
    String employecode;
    String singinstatus;
    String department;
    String lunchbreak;

    public TimeCard(String employecode, String singinstatus, String department, String lunchbreak) {
        this.employecode = employecode;
        this.singinstatus = singinstatus;
        this.department = department;
        this.lunchbreak = lunchbreak;
    }

    public TimeCard() {
    }

    public String getEmployecode() {
        return employecode;
    }

    public void setEmployecode(String employecode) {
        this.employecode = employecode;
    }

    public String getSinginstatus() {
        return singinstatus;
    }

    public void setSinginstatus(String singinstatus) {
        this.singinstatus = singinstatus;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getLunchbreak() {
        return lunchbreak;
    }

    public void setLunchbreak(String lunchbreak) {
        this.lunchbreak = lunchbreak;
    }
}
