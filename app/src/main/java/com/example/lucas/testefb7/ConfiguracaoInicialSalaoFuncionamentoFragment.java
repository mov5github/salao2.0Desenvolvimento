package com.example.lucas.testefb7;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lucas.testefb7.Auxiliares.Funcionamento;
import com.example.lucas.testefb7.domain.User;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConfiguracaoInicialSalaoFuncionamentoFragment extends Fragment{
    private TextView abreSegunda;
    private TextView abreTerca;
    private TextView abreQuarta;
    private TextView abreQuinta;
    private TextView abreSexta;
    private TextView abreSabado;
    private TextView abreDomingo;
    private TextView fechaSegunda;
    private TextView fechaTerca;
    private TextView fechaQuarta;
    private TextView fechaQuinta;
    private TextView fechaSexta;
    private TextView fechaSabado;
    private TextView fechaDomingo;
    private TextView almocoSegunda;
    private TextView almocoTerca;
    private TextView almocoQuarta;
    private TextView almocoQuinta;
    private TextView almocoSexta;
    private TextView almocoSabado;
    private TextView almocoDomingo;
    private Spinner duracaoAlmoco;
    private CheckBox segunda;
    private CheckBox terca;
    private CheckBox quarta;
    private CheckBox quinta;
    private CheckBox sexta;
    private CheckBox sabado;
    private CheckBox domingo;






    public ConfiguracaoInicialSalaoFuncionamentoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_configuracao_inicial_salao_funcionamento, container, false);
        initViews(view);
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        persistirHorarioFuncionamento(outState);

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null){
            //recuperar dados
            retomarHorarioFuncionamento(savedInstanceState);
        }
    }


    private  void initViews(View view){
        abreSegunda = (TextView) view.findViewById(R.id.abre_segunda);
        abreTerca =  (TextView) view.findViewById(R.id.abre_terca);
        abreQuarta = (TextView) view.findViewById(R.id.abre_quarta);
        abreQuinta = (TextView) view.findViewById(R.id.abre_quinta);
        abreSexta = (TextView) view.findViewById(R.id.abre_sexta);
        abreSabado = (TextView) view.findViewById(R.id.abre_sabado);
        abreDomingo = (TextView) view.findViewById(R.id.abre_domingo);
        fechaSegunda = (TextView) view.findViewById(R.id.fecha_segunda);
        fechaTerca =  (TextView) view.findViewById(R.id.fecha_terca);
        fechaQuarta = (TextView) view.findViewById(R.id.fecha_quarta);
        fechaQuinta = (TextView) view.findViewById(R.id.fecha_quinta);
        fechaSexta = (TextView) view.findViewById(R.id.fecha_sexta);
        fechaSabado = (TextView) view.findViewById(R.id.fecha_sabado);
        fechaDomingo = (TextView) view.findViewById(R.id.fecha_domingo);
        almocoSegunda = (TextView) view.findViewById(R.id.almoco_segunda);
        almocoTerca =  (TextView) view.findViewById(R.id.almoco_terca);
        almocoQuarta = (TextView) view.findViewById(R.id.almoco_quarta);
        almocoQuinta = (TextView) view.findViewById(R.id.almoco_quinta);
        almocoSexta = (TextView) view.findViewById(R.id.almoco_sexta);
        almocoSabado = (TextView) view.findViewById(R.id.almoco_sabado);
        almocoDomingo = (TextView) view.findViewById(R.id.almoco_domingo);
        duracaoAlmoco = (Spinner) view.findViewById(R.id.spinner_duracao_almoco);
        duracaoAlmoco.setSelection(0);
        segunda =(CheckBox) view.findViewById(R.id.segunda);
        terca =(CheckBox) view.findViewById(R.id.terca);
        quarta =(CheckBox) view.findViewById(R.id.quarta);
        quinta =(CheckBox) view.findViewById(R.id.quinta);
        sexta =(CheckBox) view.findViewById(R.id.sexta);
        sabado =(CheckBox) view.findViewById(R.id.sabado);
        domingo =(CheckBox) view.findViewById(R.id.domingo);


    }

    protected void aplicaVisibilidadeHorarios(View view){
        CheckBox checkBox = (CheckBox) view;
        switch (view.getId()){
            case R.id.segunda:
                if (checkBox.isChecked()){
                    abreSegunda.setVisibility(View.VISIBLE);
                    almocoSegunda.setVisibility(View.VISIBLE);
                    fechaSegunda.setVisibility(View.VISIBLE);
                }else{
                    abreSegunda.setVisibility(View.INVISIBLE);
                    almocoSegunda.setVisibility(View.INVISIBLE);
                    fechaSegunda.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.terca:
                if (checkBox.isChecked()){
                    abreTerca.setVisibility(View.VISIBLE);
                    almocoTerca.setVisibility(View.VISIBLE);
                    fechaTerca.setVisibility(View.VISIBLE);
                }else {
                    abreTerca.setVisibility(View.INVISIBLE);
                    almocoTerca.setVisibility(View.INVISIBLE);
                    fechaTerca.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.quarta:
                if (checkBox.isChecked()){
                    abreQuarta.setVisibility(View.VISIBLE);
                    almocoQuarta.setVisibility(View.VISIBLE);
                    fechaQuarta.setVisibility(View.VISIBLE);
                }else{
                    abreQuarta.setVisibility(View.INVISIBLE);
                    almocoQuarta.setVisibility(View.INVISIBLE);
                    fechaQuarta.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.quinta:
                if (checkBox.isChecked()){
                    abreQuinta.setVisibility(View.VISIBLE);
                    almocoQuinta.setVisibility(View.VISIBLE);
                    fechaQuinta.setVisibility(View.VISIBLE);
                }else {
                    abreQuinta.setVisibility(View.INVISIBLE);
                    almocoQuinta.setVisibility(View.INVISIBLE);
                    fechaQuinta.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.sexta:
                if (checkBox.isChecked()){
                    abreSexta.setVisibility(View.VISIBLE);
                    almocoSexta.setVisibility(View.VISIBLE);
                    fechaSexta.setVisibility(View.VISIBLE);
                }else {
                    abreSexta.setVisibility(View.INVISIBLE);
                    almocoSexta.setVisibility(View.INVISIBLE);
                    fechaSexta.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.sabado:
                if (checkBox.isChecked()){
                    abreSabado.setVisibility(View.VISIBLE);
                    almocoSabado.setVisibility(View.VISIBLE);
                    fechaSabado.setVisibility(View.VISIBLE);
                }else {
                    abreSabado.setVisibility(View.INVISIBLE);
                    almocoSabado.setVisibility(View.INVISIBLE);
                    fechaSabado.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.domingo:
                if (checkBox.isChecked()){
                    abreDomingo.setVisibility(View.VISIBLE);
                    almocoDomingo.setVisibility(View.VISIBLE);
                    fechaDomingo.setVisibility(View.VISIBLE);
                }else {
                    abreDomingo.setVisibility(View.INVISIBLE);
                    almocoDomingo.setVisibility(View.INVISIBLE);
                    fechaDomingo.setVisibility(View.INVISIBLE);
                }
                break;
            default:
                break;
        }
    }

    protected Boolean validaFormulario(){

        Log.i("validacao","entrou validacao");
        if (segunda.isChecked()) {
            if (abreSegunda.getText().toString().equals("--:--")){
                showToast("Preencher horario de abertura das segundas!");
                return false;
            }else if (almocoSegunda.getText().toString().equals("--:--")){
                showToast("Preencher horario de almoço das segundas!");
                return false;
            }else if (fechaSegunda.getText().toString().equals("--:--")){
                showToast("Preencher horario de encerramento das segundas!");
                return false;
            }
        }
        if (terca.isChecked()) {
            if (abreTerca.getText().toString().equals("--:--")){
                showToast("Preencher horario de abertura das terças!");
                return false;
            }else if (almocoTerca.getText().toString().equals("--:--")){
                showToast("Preencher horario de almoço das terças!");
                return false;
            }else if (fechaTerca.getText().toString().equals("--:--")){
                showToast("Preencher horario de encerramento das terças!");
                return false;
            }
        }
        if (quarta.isChecked()) {
            if (abreQuarta.getText().toString().equals("--:--")){
                showToast("Preencher horario de abertura das quartas!");
                return false;
            }else if (almocoQuarta.getText().toString().equals("--:--")){
                showToast("Preencher horario de almoço das quartas!");
                return false;
            }else if (fechaQuarta.getText().toString().equals("--:--")){
                showToast("Preencher horario de encerramento das quartas!");
                return false;
            }
        }
        if (quinta.isChecked()) {
            Log.i("validacao","quinta check");
            if (abreQuinta.getText().toString().equals("--:--")){
                showToast("Preencher horario de abertura das quintas!");
                return false;
            }else if (almocoQuinta.getText().toString().equals("--:--")){
                showToast("Preencher horario de almoço das quintas!");
                return false;
            }else if (fechaQuinta.getText().toString().equals("--:--")){
                showToast("Preencher horario de encerramento das quintas!");
                return false;
            }
        }
        if (sexta.isChecked()) {
            if (abreSexta.getText().toString().equals("--:--")){
                showToast("Preencher horario de abertura das sextas!");
                return false;
            }else if (almocoSexta.getText().toString().equals("--:--")){
                showToast("Preencher horario de almoço das sextas!");
                return false;
            }else if (fechaSexta.getText().toString().equals("--:--")){
                showToast("Preencher horario de encerramento das sextas!");
                return false;
            }
        }
        if (sabado.isChecked()) {
            if (abreSabado.getText().toString().equals("--:--")){
                showToast("Preencher horario de abertura dos sabados!");
                return false;
            }else if (almocoSabado.getText().toString().equals("--:--")){
                showToast("Preencher horario de almoço dos sabados!");
                return false;
            } else if (fechaSabado.getText().toString().equals("--:--")){
                showToast("Preencher horario de encerramento dos sabados!");
                return false;
            }
        }
        if (domingo.isChecked()) {
            if (abreDomingo.getText().toString().equals("--:--")){
                showToast("Preencher horario de abertura dos domingos!");
                return false;
            }else if (almocoDomingo.getText().toString().equals("--:--")){
                showToast("Preencher horario de almoço dos domingos!");
                return false;
            }else if (fechaDomingo.getText().toString().equals("--:--")){
                showToast("Preencher horario de encerramento dos domingos!");
                return false;
            }
        }
        if (duracaoAlmoco.getSelectedItemPosition() == 0){
            showToast("Preencher a duração padrão dos almoços!");
            return false;
        }
        return true;

    }

    protected Funcionamento geraFuncionamento(){
        Funcionamento funcionamento = new Funcionamento();

        if (segunda.isChecked()) {
            funcionamento.setAbreSegunda(abreSegunda.getText().toString());
            funcionamento.setAlmocoSegunda(almocoSegunda.getText().toString());
            funcionamento.setFechaSegunda(fechaSegunda.getText().toString());
        }
        if (terca.isChecked()) {
            funcionamento.setAbreTerca(abreTerca.getText().toString());
            funcionamento.setAlmocoTerca(almocoTerca.getText().toString());
            funcionamento.setFechaTerca(fechaTerca.getText().toString());
        }
        if (quarta.isChecked()) {
            funcionamento.setAbreQuarta(abreQuarta.getText().toString());
            funcionamento.setAlmocoQuarta(almocoQuarta.getText().toString());
            funcionamento.setFechaQuarta(fechaQuarta.getText().toString());}
        if (quinta.isChecked()) {
            funcionamento.setAbreQuinta(abreQuinta.getText().toString());
            funcionamento.setAlmocoQuinta(almocoQuinta.getText().toString());
            funcionamento.setFechaQuinta(fechaQuinta.getText().toString());}
        if (sexta.isChecked()) {
            funcionamento.setAbreSexta(abreSexta.getText().toString());
            funcionamento.setAlmocoSexta(almocoSexta.getText().toString());
           funcionamento.setFechaSexta(fechaSexta.getText().toString());}
        if (sabado.isChecked()) {
           funcionamento.setAbreSabado(abreSabado.getText().toString());
            funcionamento.setAlmocoSabado(almocoSabado.getText().toString());
            funcionamento.setFechaSabado(fechaSabado.getText().toString());  }
        if (domingo.isChecked()) {
            funcionamento.setAbreDomingo(abreDomingo.getText().toString());
            funcionamento.setAlmocoDomingo(almocoDomingo.getText().toString());
            funcionamento.setFechaDomingo(fechaDomingo.getText().toString());}
        if (duracaoAlmoco.getSelectedItemPosition() != 0) {
            funcionamento.setDuracaoAlmoco(duracaoAlmoco.getSelectedItem().toString());
        }
        return funcionamento;
    }



    private void retomarHorarioFuncionamento(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey("abreSegunda")|| savedInstanceState.containsKey("almocoSegunda") || savedInstanceState.containsKey("fechaSegunda")){
            segunda.setChecked(true);
            aplicaVisibilidadeHorarios(segunda);
            if (savedInstanceState.containsKey("abreSegunda")) abreSegunda.setText(savedInstanceState.get("abreSegunda").toString());
            if (savedInstanceState.containsKey("almocoSegunda")) almocoSegunda.setText(savedInstanceState.get("almocoSegunda").toString());
            if (savedInstanceState.containsKey("fechaSegunda")) fechaSegunda.setText(savedInstanceState.get("fechaSegunda").toString());
        }
        if (savedInstanceState.containsKey("abreTerca")|| savedInstanceState.containsKey("almocoTerca") || savedInstanceState.containsKey("fechaTerca")){
            terca.setChecked(true);
            aplicaVisibilidadeHorarios(terca);
            if (savedInstanceState.containsKey("abreTerca")) abreTerca.setText(savedInstanceState.get("abreTerca").toString());
            if (savedInstanceState.containsKey("almocoTerca")) almocoTerca.setText(savedInstanceState.get("almocoTerca").toString());
            if (savedInstanceState.containsKey("fechaTerca")) fechaTerca.setText(savedInstanceState.get("fechaTerca").toString());
        }
        if (savedInstanceState.containsKey("abreQuarta")|| savedInstanceState.containsKey("almocoQuarta") || savedInstanceState.containsKey("fechaQuarta")){
            quarta.setChecked(true);
            aplicaVisibilidadeHorarios(quarta);
            if (savedInstanceState.containsKey("abreQuarta")) abreQuarta.setText(savedInstanceState.get("abreQuarta").toString());
            if (savedInstanceState.containsKey("almocoQuarta")) almocoQuarta.setText(savedInstanceState.get("almocoQuarta").toString());
            if (savedInstanceState.containsKey("fechaQuarta")) fechaQuarta.setText(savedInstanceState.get("fechaQuarta").toString());
        }
        if (savedInstanceState.containsKey("abreQuinta")|| savedInstanceState.containsKey("almocoQuinta") || savedInstanceState.containsKey("fechaQuinta")){
            quinta.setChecked(true);
            aplicaVisibilidadeHorarios(quinta);
            if (savedInstanceState.containsKey("abreQuinta")) abreQuinta.setText(savedInstanceState.get("abreQuinta").toString());
            if (savedInstanceState.containsKey("almocoQuinta")) almocoQuinta.setText(savedInstanceState.get("almocoQuinta").toString());
            if (savedInstanceState.containsKey("fechaQuinta")) fechaQuinta.setText(savedInstanceState.get("fechaQuinta").toString());
        }
        if (savedInstanceState.containsKey("abreSexta")|| savedInstanceState.containsKey("almocoSexta") || savedInstanceState.containsKey("fechaSexta")){
            sexta.setChecked(true);
            aplicaVisibilidadeHorarios(sexta);
            if (savedInstanceState.containsKey("abreSexta")) abreSexta.setText(savedInstanceState.get("abreSexta").toString());
            if (savedInstanceState.containsKey("almocoSexta")) almocoSexta.setText(savedInstanceState.get("almocoSexta").toString());
            if (savedInstanceState.containsKey("fechaSexta")) fechaSexta.setText(savedInstanceState.get("fechaSexta").toString());
        }
        if (savedInstanceState.containsKey("abreSabado")|| savedInstanceState.containsKey("almocoSabado") || savedInstanceState.containsKey("fechaSabado")){
            sabado.setChecked(true);
            aplicaVisibilidadeHorarios(sabado);
            if (savedInstanceState.containsKey("abreSabado")) abreSabado.setText(savedInstanceState.get("abreSabado").toString());
            if (savedInstanceState.containsKey("almocoSabado")) almocoSabado.setText(savedInstanceState.get("almocoSabado").toString());
            if (savedInstanceState.containsKey("fechaSabado")) fechaSabado.setText(savedInstanceState.get("fechaSabado").toString());
        }
        if (savedInstanceState.containsKey("abreDomingo")|| savedInstanceState.containsKey("almocoDomingo") || savedInstanceState.containsKey("fechaSabado")){
            domingo.setChecked(true);
            aplicaVisibilidadeHorarios(domingo);
            if (savedInstanceState.containsKey("abreDomingo")) abreDomingo.setText(savedInstanceState.get("abreDomingo").toString());
            if (savedInstanceState.containsKey("almocoDomingo")) almocoDomingo.setText(savedInstanceState.get("almocoDomingo").toString());
            if (savedInstanceState.containsKey("fechaDomingo")) fechaDomingo.setText(savedInstanceState.get("fechaDomingo").toString());
        }
        if (savedInstanceState.containsKey("duracaoAlmocoPosition")) duracaoAlmoco.setSelection((int)savedInstanceState.get("duracaoAlmocoPosition"));
    }

    private void persistirHorarioFuncionamento(Bundle outState) {
        if (segunda.isChecked()) {
            outState.putString("abreSegunda", abreSegunda.getText().toString());
            outState.putString("almocoSegunda", almocoSegunda.getText().toString());
            outState.putString("fechaSegunda", fechaSegunda.getText().toString());
        }
        if (terca.isChecked()) {
            outState.putString("abreTerca", abreTerca.getText().toString());
            outState.putString("almocoTerca", almocoTerca.getText().toString());
            outState.putString("fechaTerca", fechaTerca.getText().toString());
        }
        if (quarta.isChecked()) {
            outState.putString("abreQuarta", abreQuarta.getText().toString());
            outState.putString("almocoQuarta", almocoQuarta.getText().toString());
            outState.putString("fechaQuarta", fechaQuarta.getText().toString());
        }
        if (quinta.isChecked()) {
            outState.putString("abreQuinta", abreQuinta.getText().toString());
            outState.putString("almocoQuinta", almocoQuinta.getText().toString());
            outState.putString("fechaQuinta", fechaQuinta.getText().toString());
        }
        if (sexta.isChecked()) {
            outState.putString("abreSexta", abreSexta.getText().toString());
            outState.putString("almocoSexta", almocoSexta.getText().toString());
            outState.putString("fechaSexta", fechaSexta.getText().toString());
        }
        if (sabado.isChecked()) {
            outState.putString("abreSabado", abreSabado.getText().toString());
            outState.putString("almocoSabado", almocoSabado.getText().toString());
            outState.putString("fechaSabado", fechaSabado.getText().toString());
        }
        if (domingo.isChecked()) {
            outState.putString("abreDomingo", abreDomingo.getText().toString());
            outState.putString("almocoDomingo", almocoDomingo.getText().toString());
            outState.putString("fechaDomingo", fechaDomingo.getText().toString());
        }
        if (duracaoAlmoco.getSelectedItemPosition() != 0) outState.putInt("duracaoAlmocoPosition",duracaoAlmoco.getSelectedItemPosition());

    }

    private void showToast( String message ){
        Toast.makeText(getActivity(),
                message,
                Toast.LENGTH_LONG)
                .show();
    }

    //Getters and Setters
    public TextView getAbreSegunda() {
        return abreSegunda;
    }

    public void setAbreSegunda(TextView abreSegunda) {
        this.abreSegunda = abreSegunda;
    }

    public TextView getAbreTerca() {
        return abreTerca;
    }

    public void setAbreTerca(TextView abreTerca) {
        this.abreTerca = abreTerca;
    }

    public TextView getAbreQuinta() {
        return abreQuinta;
    }

    public void setAbreQuinta(TextView abreQuinta) {
        this.abreQuinta = abreQuinta;
    }

    public TextView getAbreQuarta() {
        return abreQuarta;
    }

    public void setAbreQuarta(TextView abreQuarta) {
        this.abreQuarta = abreQuarta;
    }

    public TextView getAbreSexta() {
        return abreSexta;
    }

    public void setAbreSexta(TextView abreSexta) {
        this.abreSexta = abreSexta;
    }

    public TextView getAbreSabado() {
        return abreSabado;
    }

    public void setAbreSabado(TextView abreSabado) {
        this.abreSabado = abreSabado;
    }

    public TextView getAbreDomingo() {
        return abreDomingo;
    }

    public void setAbreDomingo(TextView abreDomingo) {
        this.abreDomingo = abreDomingo;
    }

    public TextView getFechaQuarta() {
        return fechaQuarta;
    }

    public void setFechaQuarta(TextView fechaQuarta) {
        this.fechaQuarta = fechaQuarta;
    }

    public TextView getFechaSegunda() {
        return fechaSegunda;
    }

    public void setFechaSegunda(TextView fechaSegunda) {
        this.fechaSegunda = fechaSegunda;
    }

    public TextView getFechaTerca() {
        return fechaTerca;
    }

    public void setFechaTerca(TextView fechaTerca) {
        this.fechaTerca = fechaTerca;
    }

    public TextView getFechaQuinta() {
        return fechaQuinta;
    }

    public void setFechaQuinta(TextView fechaQuinta) {
        this.fechaQuinta = fechaQuinta;
    }

    public TextView getFechaSexta() {
        return fechaSexta;
    }

    public void setFechaSexta(TextView fechaSexta) {
        this.fechaSexta = fechaSexta;
    }

    public TextView getFechaSabado() {
        return fechaSabado;
    }

    public void setFechaSabado(TextView fechaSabado) {
        this.fechaSabado = fechaSabado;
    }

    public TextView getAlmocoSegunda() {
        return almocoSegunda;
    }

    public void setAlmocoSegunda(TextView almocoSegunda) {
        this.almocoSegunda = almocoSegunda;
    }

    public TextView getFechaDomingo() {
        return fechaDomingo;
    }

    public void setFechaDomingo(TextView fechaDomingo) {
        this.fechaDomingo = fechaDomingo;
    }

    public TextView getAlmocoTerca() {
        return almocoTerca;
    }

    public void setAlmocoTerca(TextView almocoTerca) {
        this.almocoTerca = almocoTerca;
    }

    public TextView getAlmocoQuinta() {
        return almocoQuinta;
    }

    public void setAlmocoQuinta(TextView almocoQuinta) {
        this.almocoQuinta = almocoQuinta;
    }

    public TextView getAlmocoQuarta() {
        return almocoQuarta;
    }

    public void setAlmocoQuarta(TextView almocoQuarta) {
        this.almocoQuarta = almocoQuarta;
    }

    public TextView getAlmocoSexta() {
        return almocoSexta;
    }

    public void setAlmocoSexta(TextView almocoSexta) {
        this.almocoSexta = almocoSexta;
    }

    public TextView getAlmocoSabado() {
        return almocoSabado;
    }

    public void setAlmocoSabado(TextView almocoSabado) {
        this.almocoSabado = almocoSabado;
    }

    public TextView getAlmocoDomingo() {
        return almocoDomingo;
    }

    public void setAlmocoDomingo(TextView almocoDomingo) {
        this.almocoDomingo = almocoDomingo;
    }

    public Spinner getDuracaoAlmoco() {
        return duracaoAlmoco;
    }

    public void setDuracaoAlmoco(Spinner duracaoAlmoco) {
        this.duracaoAlmoco = duracaoAlmoco;
    }

    public CheckBox getSegunda() {
        return segunda;
    }

    public void setSegunda(CheckBox segunda) {
        this.segunda = segunda;
    }

    public CheckBox getTerca() {
        return terca;
    }

    public void setTerca(CheckBox terca) {
        this.terca = terca;
    }

    public CheckBox getQuinta() {
        return quinta;
    }

    public void setQuinta(CheckBox quinta) {
        this.quinta = quinta;
    }

    public CheckBox getQuarta() {
        return quarta;
    }

    public void setQuarta(CheckBox quarta) {
        this.quarta = quarta;
    }

    public CheckBox getSexta() {
        return sexta;
    }

    public void setSexta(CheckBox sexta) {
        this.sexta = sexta;
    }

    public CheckBox getSabado() {
        return sabado;
    }

    public void setSabado(CheckBox sabado) {
        this.sabado = sabado;
    }

    public CheckBox getDomingo() {
        return domingo;
    }

    public void setDomingo(CheckBox domingo) {
        this.domingo = domingo;
    }



}
