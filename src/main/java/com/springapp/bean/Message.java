package com.springapp.bean;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
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
    @Size(min=5,max=100)
    private String subject;
    @Size(min=5,max=1000)
    private String content;

    //Name of user sending the message
    @Size(max = 60, min = 3, message = "Name must be between 3 and 30 characters long.")
    private String name;


    //Sender's email address
    @NotBlank
    @Email
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

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
