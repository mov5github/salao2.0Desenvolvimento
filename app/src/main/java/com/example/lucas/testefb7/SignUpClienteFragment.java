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
    private AutoCompleteTextView rg;
    private EditText dataNascimento;
    private DatePickerDialog fromDatePickerDialog;
    private SimpleDateFormat dateFormatter;





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
        rg = (AutoCompleteTextView) view.findViewById(R.id.rg);
        dataNascimento = (EditText) view.findViewById(R.id.data_nascimento);
        dataNascimento.setInputType(InputType.TYPE_NULL);
        dataNascimento.setText("dd/mm/aaaa");
        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);


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
       if (nameIsValid() && emailIsValid() && passwordIsvalid() && rgIsValid() && dataNascimentoIsValid()){
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
        if (dataNascimento.getText().toString().isEmpty()){
            dataNascimento.setError("Campo Obrigatório");
            dataNascimento.requestFocus();
            return false;
        }else{
            return true;
        }
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

    public AutoCompleteTextView getRg() {
        return rg;
    }

    public EditText getDataNascimento() {
        return dataNascimento;
    }

    public DatePickerDialog getFromDatePickerDialog() {
        return fromDatePickerDialog;
    }






}
