package whatsapp.appvariado.com.whatsapp.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import whatsapp.appvariado.com.whatsapp.R;
import whatsapp.appvariado.com.whatsapp.config.ConfiguracaoFirebase;
import whatsapp.appvariado.com.whatsapp.model.Usuario;

public class LoginActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;

    private EditText email;
    private EditText senha;
    private Button botaoEntrar;
    private TextView novaConta;
    private Usuario usuario;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        databaseReference = ConfiguracaoFirebase.getFirebase();

        verificarUsuarioLogado();

        email = findViewById(R.id.emailId);
        senha = findViewById(R.id.senhaId);
        novaConta = findViewById(R.id.novaContaId);
        botaoEntrar = findViewById(R.id.bLogin);

        botaoEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario = new Usuario();
                usuario.setEmail(email.getText().toString());
                usuario.setSenha(senha.getText().toString());

                validarLogin();
            }
        });

    }

    private void validarLogin(){
        auth = ConfiguracaoFirebase.getFirebaseAuth();
        auth.signInWithEmailAndPassword(
            usuario.getEmail(),
            usuario.getSenha()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    abrirTelaPrincipal();
                    Toast.makeText(LoginActivity.this, "Sucesso",Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(LoginActivity.this, "Error",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void abrirTelaPrincipal(){
        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void abrirCadastroUsuario(View view){
        Intent intent = new Intent(LoginActivity.this,CadastroUsuarioActivity.class);
        startActivity(intent);

    }

    private void verificarUsuarioLogado(){
        auth = ConfiguracaoFirebase.getFirebaseAuth();
        if (auth.getCurrentUser() != null){
            abrirTelaPrincipal();
        }

    }
}
