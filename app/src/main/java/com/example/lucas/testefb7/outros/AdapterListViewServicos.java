package com.example.lucas.testefb7.outros;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lucas.testefb7.R;

import java.util.ArrayList;

/**
 * Created by Lucas on 14/12/2016.
 */

public class AdapterListviewServicos extends BaseAdapter {
    private LayoutInflater mInflater;
    private ArrayList<ItemListviewServicos> itens;

    public AdapterListviewServicos(Context context, ArrayList<ItemListviewServicos> itens){
        this.itens = itens;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return itens.size();
    }

    //Retorna o item de acordo com a posicao dele na tela
    @Override
    public ItemListviewServicos getItem(int position) {
        return itens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        //Pega o item de acordo com a posção.
        ItemListviewServicos item = itens.get(position);
        //infla o layout para podermos preencher os dados
        view = mInflater.inflate(R.layout.row_listview_servicos,null);

        ((TextView) view.findViewById(R.id.textview_nome_servico)).setText(item.getNomeServico());
        ((ImageView) view.findViewById(R.id.imageview_icone_servico)).setImageResource(item.getIconeServico());

        return view;
    }
}
