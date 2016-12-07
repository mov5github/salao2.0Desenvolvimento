package com.example.lucas.testefb7.domain;

import android.content.Context;

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
    private String tipoUsuario;
    private String rg;
    private String dataNascimento;


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

    public boolean isSocialNetworkLogged( Context context ){
        String token = getProviderSP( context );
        return( token.contains("facebook") || token.contains("google") || token.contains("twitter") || token.contains("github") );
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

    public void saveProviderSP(Context context, String token ){
        LibraryClass.saveSP( context, PROVIDER, token );
    }
    public String getProviderSP(Context context ){
        return( LibraryClass.getSP( context, PROVIDER) );
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

    public String getTipoUsuario() {
        return tipoUsuario;
    }
    public void setTipoUsuario(String teste) {
        this.tipoUsuario = teste;
    }

    public String getRg() {
        return rg;
    }
    public void setRg(String rg) {
        this.rg = rg;
    }


    public String getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }






}
