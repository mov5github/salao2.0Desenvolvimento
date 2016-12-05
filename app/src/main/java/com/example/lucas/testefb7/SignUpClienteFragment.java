package com.example.lucas.testefb7;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lucas.testefb7.domain.User;
import com.google.firebase.auth.FirebaseAuth;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpClienteFragment extends Fragment {
    protected AutoCompleteTextView name;
    protected AutoCompleteTextView email;
    protected EditText password;


    public SignUpClienteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up_cliente, container, false);
        initViews(view);
        return view;
    }


    public void initViews (View view){
        name = (AutoCompleteTextView) view.findViewById(R.id.name);
        email = (AutoCompleteTextView) view.findViewById(R.id.email);
        password = (EditText) view.findViewById(R.id.password);
    }

    public boolean validaFormulario(){
       if (nameIsValid() && emailIsValid() && passwordIsvalid()){
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






}
