package whatsapp.appvariado.com.whatsapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import whatsapp.appvariado.com.whatsapp.R;
import whatsapp.appvariado.com.whatsapp.config.ConfiguracaoFirebase;


public class MainActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private Button bSaida;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = ConfiguracaoFirebase.getFirebaseAuth();

        toolbar = findViewById(R.id.toolbar_principal);
        toolbar.setTitle("WhatsApp");
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.item_sair:
                deslogarUsuario();
                return true;

            case R.id.item_configuracoes:
                deslogarUsuario();
                return true;

            case R.id.item_pesquisa:
                deslogarUsuario();
                return true;

            case R.id.item_adicionar:
                deslogarUsuario();

                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public void deslogarUsuario(){
        auth.signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();

    }
}
