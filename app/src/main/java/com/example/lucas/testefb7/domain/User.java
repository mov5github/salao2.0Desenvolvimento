package com.example.lucas.testefb7.domain;

import com.example.lucas.testefb7.Auxiliares.DadosUsuario;
import com.example.lucas.testefb7.domain.util.LibraryClass;
import com.google.firebase.database.DatabaseReference;

/**
 * Created by Lucas on 30/11/2016.
 */

public class User {
    public static String PROVIDER = "com.example.lucas.testefb7.domain.User.PROVIDER";

    private String id;
    private String name;
    private String email;
    private String password;
    private String newPassword;
    //private DadosUsuario dadosUsuario;

    public User(){}


    public void saveDB( DatabaseReference.CompletionListener... completionListener ){
        DatabaseReference firebase = LibraryClass.getFirebase().child("users").child( getId() );

        if( completionListener.length == 0 ){
            firebase.setValue(this);
        }
        else{
            firebase.setValue(this, completionListener[0]);
        }
    }

    public void setNameIfNull(String name) {
        if( this.name == null ){
            this.name = name;
        }
    }

    public void setEmailIfNull(String email) {
        if( this.email == null ){
            this.email = email;
        }

    }


    //Getters and Setters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
