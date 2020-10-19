package com.example.service_project.Model;

public class Employe {
    public String name;
    public String lastname;
    public String id;
    public String password;

    public Employe(String name, String lastname, String id, String password) {
        this.name = name;
        this.lastname = lastname;
        this.id = id;
        this.password = password;
    }
    public Employe(){
       // Default constructor required for calls to DataSnapshot.getValue(Employe.class)
    }

}
