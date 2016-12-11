package com.example.lucas.testefb7;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.lucas.testefb7.domain.User;
import com.example.lucas.testefb7.domain.util.LibraryClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class SignUpActivity extends CommonActivity implements DatabaseReference.CompletionListener {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private User user;
    private AutoCompleteTextView name;
    private String sexo;
    private String publico;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        progressBar = (ProgressBar) findViewById(R.id.sign_up_progress);

        mAuth = FirebaseAuth.getInstance();

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

                if( firebaseUser == null || user.getId() != null ){
                    return;
                }

                user.setId( firebaseUser.getUid() );
                user.saveDB( SignUpActivity.this );
            }
        };


    }



    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if( mAuthStateListener != null ){
            mAuth.removeAuthStateListener(mAuthStateListener);
        }
    }

    @Override
    protected void initViews() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container_signup);
        if (fragment instanceof SignUpClienteFragment){
            name = ((SignUpClienteFragment) fragment).getName();
            email = ((SignUpClienteFragment) fragment).getEmail();
            password = ((SignUpClienteFragment) fragment).getPassword();
            publico = new String();
        }else if (fragment instanceof SignUpSalaoFragment){
            name = ((SignUpSalaoFragment) fragment).getName();
            email = ((SignUpSalaoFragment) fragment).getEmail();
            password = ((SignUpSalaoFragment) fragment).getPassword();
            sexo = new String();
        }
        else{
            name = new AutoCompleteTextView(this);
            email = new AutoCompleteTextView(this);
            password = new EditText(this);
            sexo = new String();
            publico = new String();
        }

    }

    @Override
    protected void initUser() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container_signup);
        user = new User();
        user.setName( name.getText().toString() );
        user.setEmail( email.getText().toString() );
        user.setPassword( password.getText().toString() );
        if (fragment instanceof SignUpClienteFragment){
            user.setTipoUsuario("cliente");
            user.setRg(((SignUpClienteFragment) fragment).getStringRg());
            user.setDataNascimento(((SignUpClienteFragment) fragment).getStringDataNascimento());
            user.setSexo(sexo);
            user.setTelefone1(((SignUpClienteFragment) fragment).getStringTelefone1());
            user.setTelefone2(((SignUpClienteFragment) fragment).getStringTelefone2());
        }else if (fragment instanceof SignUpSalaoFragment){
            user.setTipoUsuario("salao");
            user.setRg(((SignUpSalaoFragment) fragment).getStringRg());
            user.setDataNascimento(((SignUpSalaoFragment) fragment).getStringDataNascimento());
            user.setPublico(publico);
            user.setNomeSalao(((SignUpSalaoFragment) fragment).getStringNomeSalao());
            user.setTelefone1(((SignUpSalaoFragment) fragment).getStringTelefone1());
            user.setTelefone2(((SignUpSalaoFragment) fragment).getStringTelefone2());
            user.setEstado(((SignUpSalaoFragment) fragment).getStringEstado());
            user.setCidade(((SignUpSalaoFragment) fragment).getStringCidade());
            user.setRua(((SignUpSalaoFragment) fragment).getStringRua());
            user.setNumEndereco(((SignUpSalaoFragment) fragment).getStringNumEndereco());
            user.setComplementoEndereco(((SignUpSalaoFragment) fragment).getStringComplementoEndereco());
        }

    }

    @Override
    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
        mAuth.signOut();

        showToast( "Conta criada com sucesso!" );
        closeProgressBar();
        finish();

    }

    public void cadastrar(View view) {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container_signup);
        if (fragment instanceof SignUpClienteFragment ){
            if (validaFormulario()){
                openProgressBar();
                initViews();
                initUser();
                saveUser();
            }
        }else if (fragment instanceof SignUpSalaoFragment){
            if (validaFormulario()){
                openProgressBar();
                initViews();
                initUser();
                saveUser();
            }
        }
    }

    public void linkAqui(View view) {
        finish();
    }

    public void onRadioButtonClickedTipoCadastro(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.radio_cliente:
                if (checked) {
                    tronaBotaoVisivel();
                    SignUpClienteFragment fragCliente = new SignUpClienteFragment();;
                    replaceFragment(fragCliente);
                }
                break;
            case R.id.radio_salao:
                if (checked) {
                    tronaBotaoVisivel();
                    SignUpSalaoFragment fragSalao = new SignUpSalaoFragment();
                    replaceFragment(fragSalao);
                }
                break;
            default:
                break;
        }
    }

    public void onRadioButtonSexoClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.radio_masculino:
                if (checked) {
                    sexo ="masculino";
                    showToast("masculino");
                }
                break;
            case R.id.radio_feminino:
                if (checked) {
                    sexo = "feminino";
                    showToast("feminino");
                }
                break;
            default:
                break;
        }
    }

    public void onRadioButtonGeneroClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.radio_masculino:
                if (checked) {
                    publico = "masculino";
                    showToast("masculino");
                }
                break;
            case R.id.radio_feminino:
                if (checked) {
                    publico = "feminino";
                    showToast("feminino");
                }
                break;
            case R.id.radio_unisex:
                if (checked) {
                    publico = "unisex";
                    showToast("unisex");
                }
                break;
            default:
                break;
        }
    }

    public void dataNascimento(View view) {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container_signup);

        //validacoe de campos fragmentCliente
        if (fragment instanceof SignUpClienteFragment){
            ((SignUpClienteFragment) fragment).getFromDatePickerDialog().show();
        }
        //validacao de campos fragmentSalao
        else if (fragment instanceof SignUpSalaoFragment){
            ((SignUpSalaoFragment) fragment).getFromDatePickerDialog().show();
        }
    }

    private void saveUser(){

        if (user.getName().isEmpty()){
            Log.w("BrokenLogic","Nao foi possivel saveUser getName vazio");
            closeProgressBar();
        }else if (user.getEmail().isEmpty()){
            Log.w("BrokenLogic","Nao foi possivel saveUser getEmail vazio");
            closeProgressBar();
        }else if (user.getPassword().isEmpty()){
            Log.w("BrokenLogic","Nao foi possivel saveUser getPassword vazio");
            closeProgressBar();
        }else {
            mAuth.createUserWithEmailAndPassword(
                    user.getEmail(),
                    user.getPassword()
            ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if( !task.isSuccessful() ){
                        closeProgressBar();
                    }
                }
            })
                    .addOnFailureListener(this, new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            FirebaseCrash.report( e );
                            showSnackbar( e.getMessage() );
                        }
                    });
        }


    }



    private Boolean validaFormulario(){
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container_signup);


        //validacoe de campos fragmentCliente
        if (fragment instanceof SignUpClienteFragment){
            if (!((SignUpClienteFragment) fragment).validaFormulario()){
                showToast("Preencha o formulário corretamente !");
                return false;
            }else {
                return true;
            }
        }
        //validacao de campos fragmentSalao
        else if (fragment instanceof SignUpSalaoFragment){
            if (!((SignUpSalaoFragment) fragment).validaFormulario()){
                showToast("Preencha o formulário corretamente !");
                return false;
            }else {
                return true;
            }
        }
        else return false;
    }



    public void tronaBotaoVisivel(){
        Button button = (Button)findViewById(R.id.button_cadastrar);
        button.setVisibility(View.VISIBLE);
    }

    private void addFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_container_signup, fragment).commit();
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container_signup, fragment).commit();
    }

    private void callLogin(){
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
        finish();
    }




    public String getTextName(){
        String string;
        if (!name.getText().toString().isEmpty() && name.getText().toString().length() !=0){
            string = name.getText().toString();
        }else {
            string = "";
        }
        return string;
    }

    public String getTextEmail(){
        String string;
        if (!email.getText().toString().isEmpty() && email.getText().toString().length() !=0){
            string = email.getText().toString();
        }else {
            string = "";
        }
        return string;
    }

    public String getTextPassword(){
        String string;
        if (!password.getText().toString().isEmpty() && password.getText().toString().length() !=0){
            string = password.getText().toString();
        }else {
            string = "";
        }
        return string;
    }


}
