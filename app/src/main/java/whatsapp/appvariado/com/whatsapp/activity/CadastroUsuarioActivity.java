package whatsapp.appvariado.com.whatsapp.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

import whatsapp.appvariado.com.whatsapp.R;
import whatsapp.appvariado.com.whatsapp.config.ConfiguracaoFirebase;
import whatsapp.appvariado.com.whatsapp.model.Usuario;

public class CadastroUsuarioActivity extends AppCompatActivity {

    private EditText nome;
    private EditText email;
    private EditText senha;
    private Button cadastrar;
    private Usuario usuario;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        nome = findViewById(R.id.cadastro_nome);
        email = findViewById(R.id.cadastro_email);
        senha = findViewById(R.id.cadastro_senha);
        cadastrar = findViewById(R.id.bCadastrar);

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario = new Usuario();
                usuario.setNome(nome.getText().toString());
                usuario.setEmail(email.getText().toString());
                usuario.setSenha(senha.getText().toString());
                cadastrarUsuario();

            }
        });


    }

    private void cadastrarUsuario(){
        firebaseAuth = ConfiguracaoFirebase.getFirebaseAuth();
        firebaseAuth.createUserWithEmailAndPassword(usuario.getEmail(),usuario.getSenha()
        ).addOnCompleteListener(CadastroUsuarioActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(CadastroUsuarioActivity.this,"Cadastrado com sucesso!",Toast.LENGTH_SHORT).show();

                    usuario.setId(task.getResult().getUser().getUid() );
                    usuario.salvar();

                    firebaseAuth.signOut();
                    finish();

                }else{

                    String erroExcecao = "";
                    try{
                        throw task.getException();

                    }catch(FirebaseAuthWeakPasswordException e){
                        erroExcecao = "Digite uma senha mais forte";
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        erroExcecao = "Email digitado error";
                    } catch (FirebaseAuthUserCollisionException e) {
                        erroExcecao = "Email já cadastrado";
                    } catch (Exception e) {
                        erroExcecao = "Erro no Cadastro!";
                        e.printStackTrace();
                    }
                    Toast.makeText(CadastroUsuarioActivity.this,"Error: "+erroExcecao,Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}
