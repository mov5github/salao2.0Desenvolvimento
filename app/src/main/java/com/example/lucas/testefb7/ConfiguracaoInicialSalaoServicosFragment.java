package com.example.lucas.testefb7;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lucas.testefb7.Auxiliares.Servico;
import com.example.lucas.testefb7.outros.AdapterListviewServicos;
import com.example.lucas.testefb7.outros.ItemListviewServicos;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConfiguracaoInicialSalaoServicosFragment extends Fragment {
    private ArrayList<ItemListviewServicos> itens;
    private AdapterListviewServicos adapterListView;
    private HashMap<String,Servico> cartelaDeServicos;

    private AutoCompleteTextView nomeServico;
    private AutoCompleteTextView precoServico;
    private Spinner spinnerHoras;
    private Spinner spinnerMinutos;
    private AutoCompleteTextView descricaoServico;
    private ImageView iconeServico;
    private ImageButton btnAdicionarServico;
    private ListView listViewServicos;


    public ConfiguracaoInicialSalaoServicosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_configuracao_inicial_salao_servicos, container, false);
        initViews(view);

        createListView();


        return view;
    }

    private void createListView()
    {


        //Criamos nossa lista que preenchera o ListView
        itens = new ArrayList<ItemListviewServicos>();

        //Cria o adapter
        adapterListView = new AdapterListviewServicos(getActivity(), itens);

        //Define o Adapter
        listViewServicos.setAdapter(adapterListView);
        //Cor quando a lista é selecionada para ralagem.
        //listViewServicos.setCacheColorHint(Color.TRANSPARENT);
    }

    protected void addListview(){
        if (validaServico()){
            Servico servico = gerarServico();
            ItemListviewServicos item = new ItemListviewServicos(servico);
            itens.add(item);
            adapterListView.notifyDataSetChanged();
        }else {
            Toast.makeText(getActivity(),"preencha corretamente",Toast.LENGTH_SHORT).show();;
        }



    }

    protected Servico gerarServico(){
        Servico servico = new Servico(nomeServico.getText().toString(),R.drawable.icone1,Integer.valueOf(precoServico.getText().toString()),150,descricaoServico.getText().toString());
        return servico;
    }





    private void initViews(View view){
        listViewServicos = (ListView) view.findViewById(R.id.listview_servicos);
        nomeServico = (AutoCompleteTextView) view.findViewById(R.id.nome_servico);
        precoServico = (AutoCompleteTextView) view.findViewById(R.id.preco_servico);
        spinnerHoras = (Spinner) view.findViewById(R.id.spinner_duracao_servico_horas);
        spinnerMinutos = (Spinner) view.findViewById(R.id.spinner_duracao_servico_minutos);
        descricaoServico = (AutoCompleteTextView) view.findViewById(R.id.descricao_servico);
        iconeServico = (ImageView) view.findViewById(R.id.imageview_icones);
        btnAdicionarServico = (ImageButton) view.findViewById(R.id.button_adicionar_servico);
    }

    private Boolean validaServico(){
        //TODO adicionar verifiacaçao do icone e spinners
        if (nomeServico.getText().toString().isEmpty() || precoServico.getText().toString().isEmpty() || descricaoServico.getText().toString().isEmpty()){
            return false;
        }else{
            return true;
        }
    }

}
