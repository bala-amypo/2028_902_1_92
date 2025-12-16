package com.example.project.entity;

import java.time.LocalDate;

public class Student{
    private int id;
    private String name;
    private String email;
    private float cgpa;
    

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }
    public int getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public int getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public int getCgpa(){
       return cgpa;
    }
    public void setCgpa(float cgpa){
        this.cgpa=cgpa;
    }
   public Student(int id,String name,String email,float cgpa){
    this.id=id;
    this.name=name;
    this.email=email;
    this.cgpa=cgpa;

   }


}