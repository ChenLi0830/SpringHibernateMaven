package com.springapp.bean;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Chen on 15-02-12.
 */
@Entity
@Table(name = "messages")
public class Message implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String subject;
    private String content;

    //Name of user sending the message
    private String name;

    //Sender's email address
    private String email;

    //Message sending TO this user
    private String username;

    public String getSubject() {
        return subject;
    }

    public Message() {
//        this.subject = "Subject goes here";
//        this.content = "Content goes here";
//        this.name = "Name goes here";
//        this.email = "Email goes here";
//        this.username = "Username goes here";
    }

    public Message(String subject, String content, String name, String email, String username) {
        this.subject = subject;
        this.content = content;
        this.name = name;
        this.email = email;
        this.username = username;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
