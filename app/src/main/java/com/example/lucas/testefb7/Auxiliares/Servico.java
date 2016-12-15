package com.example.lucas.testefb7.Auxiliares;

/**
 * Created by Lucas on 14/12/2016.
 */

public class Servico {
    private String nomeServico;
    private int duracaoServico;
    private int precoServico;
    private String descricaoServico;
    private int iconeServico;

    public Servico(String nomeServico, int iconeServico, int precoServico, int duracaoServico, String descricaoServico){
        this.nomeServico = nomeServico;
        this.iconeServico = iconeServico;
        this.precoServico = precoServico;
        this.duracaoServico = duracaoServico;
        this.descricaoServico = descricaoServico;
    }

    public Servico(String nomeServico, int iconeServico){
        this.nomeServico = nomeServico;
        this.iconeServico = iconeServico;
    }


    public int getDuracaoServico() {
        return duracaoServico;
    }
    public void setDuracaoServico(int duracaoServico) {
        this.duracaoServico = duracaoServico;
    }

    public String getNomeServico() {
        return nomeServico;
    }
    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public int getPrecoServico() {
        return precoServico;
    }
    public void setPrecoServico(int precoServico) {
        this.precoServico = precoServico;
    }

    public String getDescricaoServico() {
        return descricaoServico;
    }
    public void setDescricaoServico(String descricaoServico) {
        this.descricaoServico = descricaoServico;
    }

    public int getIconeServico() {
        return iconeServico;
    }
    public void setIconeServico(int iconeServico) {
        this.iconeServico = iconeServico;
    }


}
