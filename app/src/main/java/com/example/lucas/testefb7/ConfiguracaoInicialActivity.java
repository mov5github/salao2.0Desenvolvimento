package com.example.lucas.testefb7;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.lucas.testefb7.Auxiliares.Funcionamento;
import com.example.lucas.testefb7.Auxiliares.Servico;
import com.example.lucas.testefb7.domain.User;
import com.example.lucas.testefb7.domain.util.LibraryClass;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.HashMap;

public class ConfiguracaoInicialActivity extends AppCompatActivity  implements DatabaseReference.CompletionListener  {

    private String REF_CODIGO_SALAO = "com.example.lucas.testefb7.REF_CODIGO_SALAO";
    private ProgressBar progressBar;
    protected int numCadastro = 0 ;
    protected int controlNumCadastro;
    private Funcionamento funcionamento;
    protected Boolean whileControlsaveDBControladorCodigoSalao = true;
    private String id;
    protected Boolean funcionamentoOK = false;
    protected Boolean servicosOK = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracao_inicial);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        progressBar = (ProgressBar) findViewById(R.id.configuracao_inicial_progress);

        resgataDBControladorCodigoSalao();
        recebeDadosActivityPai();
        verificaTipoUsuario();
        REF_CODIGO_SALAO = REF_CODIGO_SALAO+id;

        openProgressBar();



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, id , Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });
    }

    @Override
    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
        Log.i("firebase","onComplete Activity");
        if (databaseError != null){
            Log.i("firebase","numCadastro não foi alterado");
        }else {
            Log.i("firebase","numCadastro alterado");
            saveSPCodigoSalao(ConfiguracaoInicialActivity.this,REF_CODIGO_SALAO,controlNumCadastro);
        }
        Log.i("firebase","onComplete Activity fim");
        whileControlsaveDBControladorCodigoSalao = true;
    }


    public void salvar(View view) {
        //TODO
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container_configuracao_inicial);

        if (fragment instanceof ConfiguracaoInicialSalaoFuncionamentoFragment){
            openProgressBar();
            if (((ConfiguracaoInicialSalaoFuncionamentoFragment)fragment).validaFormulario()){
                Log.i("validaçao","form valido");
                funcionamento =((ConfiguracaoInicialSalaoFuncionamentoFragment)fragment).geraFuncionamento();
                if (getSPCodigoSalao(ConfiguracaoInicialActivity.this,REF_CODIGO_SALAO) == 0){
                    while (getSPCodigoSalao(ConfiguracaoInicialActivity.this,REF_CODIGO_SALAO) == 0 && whileControlsaveDBControladorCodigoSalao){
                        gerarNumCadastroUnico();
                    }
                    funcionamento.saveDBFuncionamento(String.valueOf(getSPCodigoSalao(ConfiguracaoInicialActivity.this,REF_CODIGO_SALAO)), new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                            Log.i("firebase","onComplete saveDBFuncionamento");
                            if (databaseError != null){
                                Log.i("firebase","funcionamento nao foi salvo");
                                closeProgressBar();
                            }else {
                                Log.i("firebase","funcionamento salvo");
                                funcionamentoOK = true;
                                closeProgressBar();
                            }
                        }
                    });
                }else{
                    Log.i("salvar","salao ja possui numCadastroUnico ");
                    funcionamento.saveDBFuncionamento(String.valueOf(getSPCodigoSalao(ConfiguracaoInicialActivity.this,REF_CODIGO_SALAO)), new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                            Log.i("firebase","onComplete saveDBFuncionamento");
                            if (databaseError != null){
                                Log.i("firebase","funcionamento nao foi salvo");
                                closeProgressBar();
                            }else {
                                Log.i("firebase","funcionamento salvo");
                                showToast("Funcionamento Salvo!");
                                funcionamentoOK = true;
                                closeProgressBar();
                            }
                        }
                    });
                    closeProgressBar();
                }

            }else{
                Log.i("salvar","formulario invalido Não salvou funcionamento no DB");
                closeProgressBar();
            }

        }


    }

    public void chamaTimePicker(View view) {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container_configuracao_inicial);
        final TextView textView;
        switch (view.getId()){
            case R.id.abre_segunda:
                textView = ((ConfiguracaoInicialSalaoFuncionamentoFragment)fragment).getAbreSegunda();
                break;
            case R.id.abre_terca:
                textView = ((ConfiguracaoInicialSalaoFuncionamentoFragment)fragment).getAbreTerca();
                break;
            case R.id.abre_quarta:
                textView = ((ConfiguracaoInicialSalaoFuncionamentoFragment)fragment).getAbreQuarta();
                break;
            case R.id.abre_quinta:
                textView = ((ConfiguracaoInicialSalaoFuncionamentoFragment)fragment).getAbreQuinta();
                break;
            case R.id.abre_sexta:
                textView = ((ConfiguracaoInicialSalaoFuncionamentoFragment)fragment).getAbreSexta();
                break;
            case R.id.abre_sabado:
                textView = ((ConfiguracaoInicialSalaoFuncionamentoFragment)fragment).getAbreSabado();
                break;
            case R.id.abre_domingo:
                textView = ((ConfiguracaoInicialSalaoFuncionamentoFragment)fragment).getAbreDomingo();
                break;
            case R.id.fecha_segunda:
                textView = ((ConfiguracaoInicialSalaoFuncionamentoFragment)fragment).getFechaSegunda();
                break;
            case R.id.fecha_terca:
                textView = ((ConfiguracaoInicialSalaoFuncionamentoFragment)fragment).getFechaTerca();
                break;
            case R.id.fecha_quarta:
                textView = ((ConfiguracaoInicialSalaoFuncionamentoFragment)fragment).getFechaQuarta();
                break;
            case R.id.fecha_quinta:
                textView = ((ConfiguracaoInicialSalaoFuncionamentoFragment)fragment).getFechaQuinta();
                break;
            case R.id.fecha_sexta:
                textView = ((ConfiguracaoInicialSalaoFuncionamentoFragment)fragment).getFechaSexta();
                break;
            case R.id.fecha_sabado:
                textView = ((ConfiguracaoInicialSalaoFuncionamentoFragment)fragment).getFechaSabado();
                break;
            case R.id.fecha_domingo:
                textView = ((ConfiguracaoInicialSalaoFuncionamentoFragment)fragment).getFechaDomingo();
                break;
            case R.id.almoco_segunda:
                textView = ((ConfiguracaoInicialSalaoFuncionamentoFragment)fragment).getAlmocoSegunda();
                break;
            case R.id.almoco_terca:
                textView = ((ConfiguracaoInicialSalaoFuncionamentoFragment)fragment).getAlmocoTerca();
                break;
            case R.id.almoco_quarta:
                textView = ((ConfiguracaoInicialSalaoFuncionamentoFragment)fragment).getAlmocoQuarta();
                break;
            case R.id.almoco_quinta:
                textView = ((ConfiguracaoInicialSalaoFuncionamentoFragment)fragment).getAlmocoQuinta();
                break;
            case R.id.almoco_sexta:
                textView = ((ConfiguracaoInicialSalaoFuncionamentoFragment)fragment).getAlmocoSexta();
                break;
            case R.id.almoco_sabado:
                textView = ((ConfiguracaoInicialSalaoFuncionamentoFragment)fragment).getAlmocoSabado();
                break;
            case R.id.almoco_domingo:
                textView = ((ConfiguracaoInicialSalaoFuncionamentoFragment)fragment).getAlmocoDomingo();
                break;
            default:
                textView = new TextView(this);
                break;
        }
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                textView.setText( selectedHour + ":" + selectedMinute);
                textView.setTextColor(Color.BLACK);
            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }

    public void onRadioButtonClickedEtapaConfiguracao(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.radio_funcionamento:
                if (checked) {
                    ConfiguracaoInicialSalaoFuncionamentoFragment frag = new ConfiguracaoInicialSalaoFuncionamentoFragment();
                    replaceFragment(frag);
                }
                break;
            case R.id.radio_servicos:
                if (checked) {
                    ConfiguracaoInicialSalaoServicosFragment frag = new ConfiguracaoInicialSalaoServicosFragment();
                    replaceFragment(frag);
                }
                break;
            default:
                break;
        }
    }

    public void selecionaDia(View view) {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container_configuracao_inicial);
        ((ConfiguracaoInicialSalaoFuncionamentoFragment)fragment).aplicaVisibilidadeHorarios(view);
    }

    public void adicionarServiçoListView(View view) {
        Servico servico = new Servico("servico",R.drawable.icone1);
        ConfiguracaoInicialSalaoServicosFragment fragment = (ConfiguracaoInicialSalaoServicosFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container_configuracao_inicial);
        fragment.addListview();
    }



    private void gerarNumCadastroUnico(){
            saveDBControladorCodigoSalao(ConfiguracaoInicialActivity.this);
    }



    public void saveDBControladorCodigoSalao( DatabaseReference.CompletionListener... completionListener ){
        whileControlsaveDBControladorCodigoSalao = false;
        controlNumCadastro = numCadastro;

        DatabaseReference firebase = LibraryClass.getFirebase().child("RegrasDeNegocio").child("ControladorCodigoSalão");
        Log.i("firebase","saveDBControladorCodigoSalao()");
        if( completionListener.length == 0 ){
            firebase.setValue(numCadastro+1);
        }
        else{
            firebase.setValue(numCadastro+1, completionListener[0]);
        }
        Log.i("firebase","saveDBControladorCodigoSalao() fim");

    }

    public void resgataDBControladorCodigoSalao(){
        DatabaseReference firebase = LibraryClass.getFirebase().child("RegrasDeNegocio").child("ControladorCodigoSalão");
        Log.i("firebase","resgataDBControladorCodigoSalao()");
        firebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue(int.class) == null){
                    Log.i("firebase","resgataDBControladorCodigoSalao() datasnapshot == null");
                }else {
                    Log.i("firebase","resgataDBControladorCodigoSalao() datasnapshot != null "+dataSnapshot.getValue().toString());
                    numCadastro = Integer.valueOf(String.valueOf(dataSnapshot.getValue()));

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void verificaTipoUsuario(){
        DatabaseReference firebase = LibraryClass.getFirebase().child("users").child( id ).child("tipoUsuario");
        firebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot != null  && dataSnapshot.getValue() != null){
                    if (dataSnapshot.getValue().toString().equals("cliente")){
                        ConfiguracaoInicialClienteFragment frag = new ConfiguracaoInicialClienteFragment();
                        replaceFragment(frag);
                        closeProgressBar();
                    }else if (dataSnapshot.getValue().toString().equals("salao")){
                        RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radiogroup_etapa_configuracao);
                        radioGroup.setVisibility(View.VISIBLE);
                        TextView textView = (TextView) findViewById(R.id.label_tipo_cadastro);
                        textView.setVisibility(View.VISIBLE);
                        Button button = (Button) findViewById(R.id.button_salvar);
                        button.setVisibility(View.VISIBLE);
                        verificaEtapa();
                    }else{
                        Log.i("firebase","tipoUsuaruo Invalido");
                        showToast("erro ao acessar banco de dados");
                        closeProgressBar();
                    }
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void verificaEtapa(){
        if (getSPCodigoSalao(ConfiguracaoInicialActivity.this,REF_CODIGO_SALAO) != 0){
            if (!funcionamentoOK){
                DatabaseReference firebase = LibraryClass.getFirebase().child("salões").child( String.valueOf(getSPCodigoSalao(ConfiguracaoInicialActivity.this,REF_CODIGO_SALAO)) ).child("funcionamento");
                firebase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot == null || dataSnapshot.getValue() == null){
                            RadioButton radioButton = (RadioButton) findViewById(R.id.radio_funcionamento);
                            radioButton.setChecked(true);
                            ConfiguracaoInicialSalaoFuncionamentoFragment frag = new ConfiguracaoInicialSalaoFuncionamentoFragment();
                            replaceFragment(frag);
                            closeProgressBar();
                        }else {
                            RadioButton radioButton = (RadioButton) findViewById(R.id.radio_servicos);
                            radioButton.setChecked(true);
                            ConfiguracaoInicialSalaoServicosFragment frag = new ConfiguracaoInicialSalaoServicosFragment();
                            replaceFragment(frag);
                            funcionamentoOK = true;
                            closeProgressBar();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        closeProgressBar();
                        Log.i("firebase","verificaEtapa() onCacelled");

                    }
                });
            }

        }

    }


    private void recebeDadosActivityPai(){
        Intent intent = getIntent();
        if (intent.getStringExtra("id") != null){
            id = intent.getStringExtra("id");
        }else{
            id = "";
        }

    }


    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container_configuracao_inicial, fragment).commit();
    }



    private void saveSPCodigoSalao(Context context, String key, int value ){
        SharedPreferences sp = context.getSharedPreferences(REF_CODIGO_SALAO, Context.MODE_PRIVATE);
        sp.edit().putInt(key, value).apply();
    }

    private int getSPCodigoSalao(Context context, String key ){
        SharedPreferences sp = context.getSharedPreferences(REF_CODIGO_SALAO, Context.MODE_PRIVATE);
        int codigoSalao = sp.getInt(key, 0);
        return( codigoSalao );
    }

    protected void showToast( String message ){
        Toast.makeText(ConfiguracaoInicialActivity.this,
                message,
                Toast.LENGTH_LONG)
                .show();
    }

    protected void openProgressBar(){
        progressBar.setVisibility( View.VISIBLE );
    }

    protected void closeProgressBar(){
        progressBar.setVisibility( View.GONE );
    }


}
