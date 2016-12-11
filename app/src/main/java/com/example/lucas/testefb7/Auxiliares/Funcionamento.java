package com.example.lucas.testefb7.Auxiliares;

import com.example.lucas.testefb7.domain.util.LibraryClass;
import com.google.firebase.database.DatabaseReference;

/**
 * Created by Lucas on 10/12/2016.
 */

public class Funcionamento {
    private String duracaoAlmoco;
    private String abreSegunda;
    private String almocoSegunda;
    private String fechaSegunda;
    private String abreTerca;
    private String almocoTerca;
    private String fechaTerca;
    private String abreQuarta;
    private String almocoQuarta;
    private String fechaQuarta;
    private String abreQuinta;
    private String almocoQuinta;
    private String fechaQuinta;
    private String abreSexta;
    private String almocoSexta;
    private String fechaSexta;
    private String abreSabado;
    private String almocoSabado;
    private String fechaSabado;
    private String abreDomingo;
    private String almocoDomingo;
    private String fechaDomingo;

    public void saveDBFuncionamento(String numCadastro, DatabaseReference.CompletionListener... completionListener ){
        DatabaseReference firebase = LibraryClass.getFirebase().child("salões").child(numCadastro ).child("funcionamento");

        if( completionListener.length == 0 ){
            firebase.setValue(this);
        }
        else{
            firebase.setValue(this, completionListener[0]);
        }
    }

    //Getters and Setters
    public String getAbreTerca() {
        return abreTerca;
    }

    public void setAbreTerca(String abreTerca) {
        this.abreTerca = abreTerca;
    }

    public String getDuracaoAlmoco() {
        return duracaoAlmoco;
    }

    public void setDuracaoAlmoco(String duracaoAlmoco) {
        this.duracaoAlmoco = duracaoAlmoco;
    }

    public String getAbreSegunda() {
        return abreSegunda;
    }

    public void setAbreSegunda(String abreSegunda) {
        this.abreSegunda = abreSegunda;
    }

    public String getAlmocoSegunda() {
        return almocoSegunda;
    }

    public void setAlmocoSegunda(String almocoSegunda) {
        this.almocoSegunda = almocoSegunda;
    }

    public String getFechaSegunda() {
        return fechaSegunda;
    }

    public void setFechaSegunda(String fechaSegunda) {
        this.fechaSegunda = fechaSegunda;
    }

    public String getAlmocoTerca() {
        return almocoTerca;
    }

    public void setAlmocoTerca(String almocoTerca) {
        this.almocoTerca = almocoTerca;
    }

    public String getFechaTerca() {
        return fechaTerca;
    }

    public void setFechaTerca(String fechaTerca) {
        this.fechaTerca = fechaTerca;
    }

    public String getAbreQuarta() {
        return abreQuarta;
    }

    public void setAbreQuarta(String abreQuarta) {
        this.abreQuarta = abreQuarta;
    }

    public String getAlmocoQuarta() {
        return almocoQuarta;
    }

    public void setAlmocoQuarta(String almocoQuarta) {
        this.almocoQuarta = almocoQuarta;
    }

    public String getAlmocoQuinta() {
        return almocoQuinta;
    }

    public void setAlmocoQuinta(String almocoQuinta) {
        this.almocoQuinta = almocoQuinta;
    }

    public String getFechaQuinta() {
        return fechaQuinta;
    }

    public void setFechaQuinta(String fechaQuinta) {
        this.fechaQuinta = fechaQuinta;
    }

    public String getFechaSexta() {
        return fechaSexta;
    }

    public void setFechaSexta(String fechaSexta) {
        this.fechaSexta = fechaSexta;
    }

    public String getAbreSabado() {
        return abreSabado;
    }

    public void setAbreSabado(String abreSabado) {
        this.abreSabado = abreSabado;
    }

    public String getAlmocoSabado() {
        return almocoSabado;
    }

    public void setAlmocoSabado(String almocoSabado) {
        this.almocoSabado = almocoSabado;
    }

    public String getFechaSabado() {
        return fechaSabado;
    }

    public void setFechaSabado(String fechaSabado) {
        this.fechaSabado = fechaSabado;
    }

    public String getAbreDomingo() {
        return abreDomingo;
    }

    public void setAbreDomingo(String abreDomingo) {
        this.abreDomingo = abreDomingo;
    }

    public String getAlmocoDomingo() {
        return almocoDomingo;
    }

    public void setAlmocoDomingo(String almocoDomingo) {
        this.almocoDomingo = almocoDomingo;
    }

    public String getFechaDomingo() {
        return fechaDomingo;
    }

    public void setFechaDomingo(String fechaDomingo) {
        this.fechaDomingo = fechaDomingo;
    }

    public String getAlmocoSexta() {
        return almocoSexta;
    }

    public void setAlmocoSexta(String almocoSexta) {
        this.almocoSexta = almocoSexta;
    }

    public String getAbreSexta() {
        return abreSexta;
    }

    public void setAbreSexta(String abreSexta) {
        this.abreSexta = abreSexta;
    }

    public String getAbreQuinta() {
        return abreQuinta;
    }

    public void setAbreQuinta(String abreQuinta) {
        this.abreQuinta = abreQuinta;
    }

    public String getFechaQuarta() {
        return fechaQuarta;
    }

    public void setFechaQuarta(String fechaQuarta) {
        this.fechaQuarta = fechaQuarta;
    }

}
