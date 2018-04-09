package caesb.gov.df.br.pandroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class LoginDroid extends Activity{

    EditText eUsuario, eSenha;
    Button btLogin;
    TextView eCadastro;

    String url = "";
    String parametros = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_droid);

        eUsuario = (EditText)findViewById(R.id.eUsuario);
        eSenha = (EditText)findViewById(R.id.eSenha);
        btLogin = (Button)findViewById(R.id.btLogin);
        eCadastro = (TextView)findViewById(R.id.eCadastro);

        eCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abreCadastro = new Intent(LoginDroid.this, Cadastro.class);
                startActivity(abreCadastro);
            }
        });


        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

                if (networkInfo != null && networkInfo.isConnected()){


                    String email = eUsuario.getText().toString();
                    String senha = eSenha.getText().toString();

                    if(email.isEmpty() || senha.isEmpty()){
                        Toast.makeText(getApplicationContext(), "Insira seu login e senha ", Toast.LENGTH_LONG).show();

                    }else{
                        url = "http://192.168.1.10:8181/login.php";
                        parametros = "email=" + email + "&senha=" + senha;
                        new SolicitaDados().execute(url);
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "Confira sua conexão", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    class SolicitaDados extends AsyncTask<String, Void, String>{
        @Override
        protected  String doInBackground(String... urls){

           return Conexao.postDados(urls[0], parametros);
        }
        @Override
        protected void onPostExecute(String resultado){
            if(resultado.equals("login_ok")){
                Intent abriInicio = new Intent(LoginDroid.this, Cadastro.class);
                startActivity(abriInicio);
            }else {
                Toast.makeText(getApplicationContext(), "Usuario ou senha invalidos", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected  void onPause(){
        super.onPause();
        finish();
    }
}
