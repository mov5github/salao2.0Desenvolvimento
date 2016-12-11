package com.example.lucas.testefb7;


import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpSalaoFragment extends Fragment {
    protected AutoCompleteTextView name;
    protected AutoCompleteTextView email;
    protected EditText password;
    protected EditText password2;
    private EditText dataNascimento;
    private DatePickerDialog fromDatePickerDialog;
    private SimpleDateFormat dateFormatter;
    private AutoCompleteTextView rg;
    private AutoCompleteTextView nomeSalao;
    private AutoCompleteTextView tefefone1;
    private AutoCompleteTextView tefefone2;
    private RadioGroup genero;
    private AutoCompleteTextView estado;
    private AutoCompleteTextView cidade;
    private AutoCompleteTextView rua;
    private AutoCompleteTextView numEndereco;
    private AutoCompleteTextView complementoEndereco;



    public SignUpSalaoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up_salao, container, false);
        initViews(view);
        setDateTimeField();
        return view;
    }

    public void initViews (View view){
        name = (AutoCompleteTextView) view.findViewById(R.id.name);
        email = (AutoCompleteTextView) view.findViewById(R.id.email);
        password = (EditText) view.findViewById(R.id.password);
        password2 = (EditText) view.findViewById(R.id.password2);
        rg = (AutoCompleteTextView) view.findViewById(R.id.rg);
        dataNascimento = (EditText) view.findViewById(R.id.data_nascimento);
        dataNascimento.setInputType(InputType.TYPE_NULL);
        dataNascimento.setText("dd/mm/aaaa");
        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        tefefone1 = (AutoCompleteTextView) view.findViewById(R.id.telefone1);
        tefefone1.setText(obterNumTelefone());
        tefefone2= (AutoCompleteTextView) view.findViewById(R.id.telefone2);
        nomeSalao = (AutoCompleteTextView) view.findViewById(R.id.name_salao);
        genero = (RadioGroup) view.findViewById(R.id.radiogroup_genero);
        estado = (AutoCompleteTextView) view.findViewById(R.id.estado);
        cidade = (AutoCompleteTextView) view.findViewById(R.id.cidade);
        numEndereco = (AutoCompleteTextView) view.findViewById(R.id.numero);
        rua = (AutoCompleteTextView) view.findViewById(R.id.rua);
        complementoEndereco = (AutoCompleteTextView) view.findViewById(R.id.complemento);


    }

    private void setDateTimeField() {


        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                dataNascimento.setText(dateFormatter.format(newDate.getTime()));
                dataNascimento.setTextColor(Color.BLACK);
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

    }


    public boolean validaFormulario(){
        if (nameIsValid() && dataNascimentoIsValid() && rgIsValid() && nomeSalaoIsValid() && publicoIsValid() && telefone1IsValid()
                && telefone2IsValid() && estadoIsValid() && cidadeIsValid() && ruaIsValid() && numEnderecoIsValid() && complementoEnderecoIsValid()
                && emailIsValid() && passwordIsvalid() && password2Isvalid()){
            return true;
        }else return false;
    }

    private Boolean nameIsValid(){
        if (name.getText().toString().isEmpty()){
            name.setError("Campo Obrigatório");
            name.requestFocus();
            return false;
        }else{
            return true;
        }
    }

    private Boolean emailIsValid(){
        if (email.getText().toString().isEmpty()){
            email.setError("Campo Obrigatório");
            email.requestFocus();
            return false;
        }else{
            return true;
        }
    }

    private Boolean passwordIsvalid(){
        if (password.getText().toString().isEmpty()){
            password.setError("Campo Obrigatório");
            password.requestFocus();
            return false;
        }else{
            return true;
        }
    }

    private Boolean password2Isvalid(){
        if (password2.getText().toString().isEmpty()){
            password2.setError("Campo Obrigatório");
            password2.requestFocus();
            return false;
        }else if (!password2.getText().toString().equals(password.getText().toString())){
            password2.setError("As senhas devem ser iguais!");
            password2.requestFocus();
            return false;
        }else{
            return true;
        }
    }

    private Boolean rgIsValid(){
        if (rg.getText().toString().isEmpty()){
            rg.setError("Campo Obrigatório");
            rg.requestFocus();
            return false;
        }else{
            return true;
        }
    }

    private Boolean nomeSalaoIsValid(){
        if (nomeSalao.getText().toString().isEmpty()){
            nomeSalao.setError("Campo Obrigatório");
            nomeSalao.requestFocus();
            return false;
        }else{
            return true;
        }
    }

    private Boolean dataNascimentoIsValid(){
        if (dataNascimento.getText().toString().isEmpty() || dataNascimento.getText().toString().equals("dd/mm/aaaa")){
            dataNascimento.setError("Campo Obrigatório");
            dataNascimento.requestFocus();
            return false;
        }else{
            return true;
        }
    }

    private Boolean publicoIsValid(){
        switch (genero.getCheckedRadioButtonId()){
            case R.id.radio_masculino:
                return true;
            case R.id.radio_feminino:
                return true;
            case R.id.radio_unisex:
                return true;
            default:
                Toast.makeText(getActivity(), "Campo Publico não selecionado!", Toast.LENGTH_LONG).show();
                genero.requestFocus();
                return false;
        }
    }

    private Boolean telefone1IsValid(){
        if (tefefone1.getText().toString().isEmpty()){
            tefefone1.setError("Campo Obrigatório");
            tefefone1.requestFocus();
            return false;
        }else{
            return true;
        }
    }

    private Boolean telefone2IsValid(){
        return true;
    }

    private Boolean estadoIsValid(){
        if (estado.getText().toString().isEmpty()){
            estado.setError("Campo Obrigatório");
            estado.requestFocus();
            return false;
        }else{
            return true;
        }
    }

    private Boolean cidadeIsValid(){
        if (cidade.getText().toString().isEmpty()){
            cidade.setError("Campo Obrigatório");
            cidade.requestFocus();
            return false;
        }else{
            return true;
        }
    }

    private Boolean ruaIsValid(){
        if (rua.getText().toString().isEmpty()){
            rua.setError("Campo Obrigatório");
            rua.requestFocus();
            return false;
        }else{
            return true;
        }
    }

    private Boolean numEnderecoIsValid(){
        return true;
    }

    private Boolean complementoEnderecoIsValid(){
        if (complementoEndereco.getText().toString().isEmpty()){
            complementoEndereco.setError("Campo Obrigatório");
            complementoEndereco.requestFocus();
            return false;
        }else{
            return true;
        }
    }


    private String obterNumTelefone(){
        //TODO impelmentar cod que pega automatico numero do celular
        return "";
    }


    public String getStringRg(){
        return rg.getText().toString();
    }

    public String getStringDataNascimento(){
        return dataNascimento.getText().toString();
    }

    public String getStringTelefone1(){
        return tefefone1.getText().toString();
    }

    public String getStringTelefone2(){
        return tefefone2.getText().toString();
    }

    public String getStringEstado(){
        return estado.getText().toString();
    }

    public String getStringCidade(){
        return cidade.getText().toString();
    }

    public String getStringRua(){
        return rua.getText().toString();
    }

    public String getStringNumEndereco(){
        return numEndereco.getText().toString();
    }

    public String getStringComplementoEndereco(){
        return complementoEndereco.getText().toString();
    }

    public String getStringNomeSalao(){
        return nomeSalao.getText().toString();
    }




    //Getters and Setters
    public AutoCompleteTextView getEmail() {
        return email;
    }

    public EditText getPassword() {
        return password;
    }

    public AutoCompleteTextView getName() {
        return name;
    }

    public DatePickerDialog getFromDatePickerDialog() {
        return fromDatePickerDialog;
    }

}
