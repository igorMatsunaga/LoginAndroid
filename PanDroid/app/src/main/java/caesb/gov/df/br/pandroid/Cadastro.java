package caesb.gov.df.br.pandroid;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Cadastro extends Activity {

    EditText eNome, enSenha, eEmail;
    Button btCancelar, btCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro);

        eNome = (EditText)findViewById(R.id.eNome);
        enSenha = (EditText)findViewById(R.id.enSenha);
        eEmail = (EditText)findViewById(R.id.eEmail);
        btCancelar = (Button)findViewById(R.id.btCancelar);
        btCadastrar = (Button)findViewById(R.id.btCadastrar);

        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
