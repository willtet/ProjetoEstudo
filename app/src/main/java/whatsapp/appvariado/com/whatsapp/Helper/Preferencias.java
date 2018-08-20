package whatsapp.appvariado.com.whatsapp.Helper;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class Preferencias {

    private Context contexto;
    private SharedPreferences preferences;
    private final String NOME_ARQUIVO = "whatsapp.preferencias";
    private SharedPreferences.Editor editor;

    private final String CHAVE_NOME = "nome";
    private final String CHAVE_TELEFONE = "telefone";
    private final String CHAVE_TOKEN = "token";

    public Preferencias(Context contextParametro) {
        contexto = contextParametro;
        preferences = contexto.getSharedPreferences(NOME_ARQUIVO, Context.MODE_PRIVATE);
        editor = preferences.edit();



    }

    public void salvarUsuarioPreferencias(String nome, String telefone, String token){
        editor.putString(CHAVE_NOME,nome);
        editor.putString(CHAVE_TELEFONE,telefone);
        editor.putString(CHAVE_TOKEN,token);
        editor.commit();
    }

    public HashMap<String,String> getDadosUsuario(){
        HashMap<String,String> dadosUsuarios = new HashMap<>();

        dadosUsuarios.put(CHAVE_NOME, preferences.getString(CHAVE_NOME,null));
        dadosUsuarios.put(CHAVE_TELEFONE, preferences.getString(CHAVE_TELEFONE,null));
        dadosUsuarios.put(CHAVE_TOKEN, preferences.getString(CHAVE_TOKEN,null));

        return dadosUsuarios;

    }


}
