package com.example.lucas.testefb7;


import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lucas.testefb7.domain.User;
import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpClienteFragment extends Fragment  {
    protected AutoCompleteTextView name;
    protected AutoCompleteTextView email;
    protected EditText password;
    protected EditText password2;
    private AutoCompleteTextView rg;
    private EditText dataNascimento;
    private DatePickerDialog fromDatePickerDialog;
    private SimpleDateFormat dateFormatter;
    private AutoCompleteTextView tefefone1;
    private AutoCompleteTextView tefefone2;
    private RadioGroup sexo;








    public SignUpClienteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up_cliente, container, false);

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
        sexo = (RadioGroup) view.findViewById(R.id.radio_group_sexo) ;
        tefefone1 = (AutoCompleteTextView) view.findViewById(R.id.telefone1);
        tefefone1.setText(obterNumTelefone());
        tefefone2= (AutoCompleteTextView) view.findViewById(R.id.telefone2);
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
       if (nameIsValid() && dataNascimentoIsValid() && rgIsValid() && sexoIsValid() && telefone1IsValid()
                && telefone2IsValid() && emailIsValid() && passwordIsvalid() && password2Isvalid()){
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

    private Boolean dataNascimentoIsValid(){
        if (dataNascimento.getText().toString().isEmpty() || dataNascimento.getText().toString().equals("dd/mm/aaaa")){
            dataNascimento.setError("Campo Obrigatório");
            dataNascimento.requestFocus();
            return false;
        }else{
            return true;
        }
    }

    private Boolean sexoIsValid(){
       switch (sexo.getCheckedRadioButtonId()){
           case R.id.radio_masculino:
               return true;
           case R.id.radio_feminino:
               return true;
           default:
               Toast.makeText(getActivity(), "Campo Sexo não selecionado!", Toast.LENGTH_LONG).show();
               sexo.requestFocus();
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

    //Getters and Setters
    public AutoCompleteTextView getName() {
        return name;
    }

    public AutoCompleteTextView getEmail() {
        return email;
    }

    public EditText getPassword() {
        return password;
    }

    public DatePickerDialog getFromDatePickerDialog() {
        return fromDatePickerDialog;
    }






}
