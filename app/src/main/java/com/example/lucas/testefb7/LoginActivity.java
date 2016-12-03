package com.example.lucas.testefb7;



import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
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
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

public class LoginActivity extends CommonActivity implements DatabaseReference.CompletionListener {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private User user;
    private AutoCompleteTextView name;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mAuth = FirebaseAuth.getInstance();

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

                if (firebaseUser == null || user.getId() != null) {
                    return;
                }

                user.setId(firebaseUser.getUid());
                user.saveDB(LoginActivity.this);
            }
        };

        initViews();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "teste", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });

        if (savedInstanceState == null){
            LoginFragment loginFragment = new LoginFragment();
            addFragment(loginFragment);
        }
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    protected void onStart() {
        super.onStart();// ATTENTION: This was auto-generated to implement the App Indexing API.
// See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        mAuth.addAuthStateListener(mAuthStateListener);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    protected void onStop() {
        super.onStop();// ATTENTION: This was auto-generated to implement the App Indexing API.
// See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());

        if (mAuthStateListener != null) {
            mAuth.removeAuthStateListener(mAuthStateListener);
        }
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.disconnect();
    }

    @Override
    protected void initViews() {
        name = (AutoCompleteTextView) findViewById(R.id.name);
        email = (AutoCompleteTextView) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        progressBar = (ProgressBar) findViewById(R.id.sign_up_progress);
    }

    @Override
    protected void initUser() {
        recebeDadosCadastro();
    }

    @Override
    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
        mAuth.signOut();

        showToast("Conta criada com sucesso!");
        closeProgressBar();
        finish();
    }


    private void saveUser() {

        mAuth.createUserWithEmailAndPassword(
                user.getEmail(),
                user.getPassword()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (!task.isSuccessful()) {
                    closeProgressBar();
                }
            }
        })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        FirebaseCrash.report(e);
                        showSnackbar(e.getMessage());
                    }
                });
    }

    public void onRadioButtonClickedTipoCadastro(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radiogroup_tipo_cadastro);
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.fragment_container);
        Button button = (Button) findViewById(R.id.cadastrar_logar_button);

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.radio_cliente:
                if (checked) {
                    frameLayout.setVisibility(View.VISIBLE);
                    button.setVisibility(View.VISIBLE);
                    SignUpClienteFragment fragCliente = new SignUpClienteFragment();;
                    replaceFragment(fragCliente);
                }
                break;
            case R.id.radio_salao:
                if (checked) {
                    frameLayout.setVisibility(View.VISIBLE);
                    button.setVisibility(View.VISIBLE);
                    SignUpSalaoFragment fragSalao = new SignUpSalaoFragment();
                    replaceFragment(fragSalao);
                }
                break;
            default:
                break;
        }
    }


    public void sendSignUpData(View view) {
        openProgressBar();
        initUser();
        saveUser();
    }


    public void recebeDadosCadastro() {
        SignUpClienteFragment clienteFrag = (SignUpClienteFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        user = new User();
        user.setName(clienteFrag.getNomeCadastroFragment());
        user.setEmail(clienteFrag.getEmailCadastroFragment());
        user.setPassword(clienteFrag.getPasswordCadastroFragment());
    }


    public void cadastrarLogar(View view) {
        Fragment fragment = (Fragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);

        if (fragment instanceof SignUpClienteFragment){

        }else if (fragment instanceof SignUpSalaoFragment){

        }else if (fragment instanceof LoginFragment){

        }

    }

    public void alteraLoginCadastro(View view) {
        Fragment fragment = (Fragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radiogroup_tipo_cadastro);
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.fragment_container);
        Button button = (Button) findViewById(R.id.cadastrar_logar_button);
        TextView textView =(TextView) findViewById(R.id.link_login_cadastro);
        TextView labelTipoCadastro =(TextView) findViewById(R.id.label_tipo_cadastro);

        if (fragment instanceof LoginFragment){
            if (frameLayout.getVisibility() != View.VISIBLE){
                radioGroup.setVisibility(View.INVISIBLE);
                labelTipoCadastro.setVisibility(View.INVISIBLE);
                frameLayout.setVisibility(View.VISIBLE);
                button.setVisibility(View.VISIBLE);
                button.setText("Logar");
                textView.setText("Não tem uma conta ? ");
            }else{
                radioGroup.setVisibility(View.VISIBLE);
                labelTipoCadastro.setVisibility(View.VISIBLE);
                frameLayout.setVisibility(View.INVISIBLE);
                button.setVisibility(View.INVISIBLE);
                button.setText("Cadastrar");
                textView.setText("Ja possui uma conta ? ");
            }
        }else if (fragment instanceof SignUpClienteFragment || fragment instanceof SignUpSalaoFragment){
            radioGroup.setVisibility(View.INVISIBLE);
            labelTipoCadastro.setVisibility(View.INVISIBLE);
            frameLayout.setVisibility(View.VISIBLE);
            button.setVisibility(View.VISIBLE);
            button.setText("Logar");
            textView.setText("Não tem uma conta ? ");
            LoginFragment loginFragment = new LoginFragment();
            replaceFragment(loginFragment);
        }
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("SignUp Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    private void addFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_container, fragment).commit();
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment).commit();
    }
}
