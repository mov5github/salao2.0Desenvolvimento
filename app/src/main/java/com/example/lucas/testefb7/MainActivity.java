package com.example.lucas.testefb7;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

import com.example.lucas.testefb7.domain.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends CommonActivity {

    private User user;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                callSignUpActivity("none");
            }
        });


        progressBar = (ProgressBar) findViewById(R.id.login_progress);
        openProgressBar();
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = getFirebaseAuthResultHandler();
        initViews();
        initUser();
        verifyLogged();
        closeProgressBar();


    }

    @Override
    protected void onStart() {
        super.onStart();
        verifyLogged();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initUser() {
        user = new User();
    }


    private FirebaseAuth.AuthStateListener getFirebaseAuthResultHandler(){
        FirebaseAuth.AuthStateListener callback = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser userFirebase = firebaseAuth.getCurrentUser();

                if( userFirebase == null ){
                    return;
                }

                if( user.getId() == null
                        && isNameOk( user, userFirebase ) ){

                    user.setId( userFirebase.getUid() );
                    user.setNameIfNull( userFirebase.getDisplayName() );
                    user.setEmailIfNull( userFirebase.getEmail() );
                    user.saveDB();
                }

                //callMainActivity();
                showToast("callSignUpActivity(); log getFirebaseAuthResultHandler()");
            }
        };
        return( callback );
    }

    private boolean isNameOk( User user, FirebaseUser firebaseUser ){
        return(
                user.getName() != null
                        || firebaseUser.getDisplayName() != null
        );
    }

    private void verifyLogged(){
        if( mAuth.getCurrentUser() != null ){
            showToast("callMainActivity(); Log verifyLogged()");
        }
        else{
            mAuth.addAuthStateListener( mAuthListener );
            showToast("callSignUpActivity(); Log verifyLogged()");
        }
    }

    //Meus Metodos

    //CallActivitys
    public void callSignUpActivity (String tipoUsuario){
        Intent intent = new Intent(this,LoginActivity.class);
        intent.putExtra("usuario",tipoUsuario);
        startActivity(intent);
        finish();
    }

    public void callSignUpActivityCliente(View view) {
        callSignUpActivity("cliente");
    }

    public void callSignUpActivitySalao(View view) {
        callSignUpActivity("salao");
    }
    //FIM CallActivitys
}
