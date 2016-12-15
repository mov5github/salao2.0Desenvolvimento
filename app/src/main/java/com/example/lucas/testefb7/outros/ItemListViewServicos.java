package com.example.lucas.testefb7.outros;

import com.example.lucas.testefb7.Auxiliares.Servico;

/**
 * Created by Lucas on 14/12/2016.
 */

public class ItemListviewServicos {
    private String nomeServico;
    private int iconeServico;

    public ItemListviewServicos(){

    }

    public ItemListviewServicos(Servico servico){

        this.nomeServico = servico.getNomeServico();
        this.iconeServico = servico.getIconeServico();
    }

    //Getters and Setters
    public int getIconeServico() {
        return iconeServico;
    }
    public void setIconeServico(int iconeServico) {
        this.iconeServico = iconeServico;
    }

    public String getNomeServico() {
        return nomeServico;
    }
    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

}
