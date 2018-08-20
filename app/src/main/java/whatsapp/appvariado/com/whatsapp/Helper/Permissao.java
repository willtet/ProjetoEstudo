package whatsapp.appvariado.com.whatsapp.Helper;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class Permissao {
    public static boolean validarPermissaoes(int resquestCode, Activity activity, String[] permissoes){

        List<String> listaPermissoes = new ArrayList<String>();

        if(Build.VERSION.SDK_INT >= 23){
            //Percorre as perissoes passadas, verifica uma a uma se tem permissoes liberadas, caso nao, coloca na lista
            for (String permissao : permissoes) {
                Boolean validaPermissao = ContextCompat.checkSelfPermission(activity,permissao) == PackageManager.PERMISSION_GRANTED;
                if (!validaPermissao) listaPermissoes.add(permissao);

            }

            //caso a lista estiver vazia, retorna q todas as permissoes estao liberadas
            if(listaPermissoes.isEmpty()){
                return true;
            }

            String[] novasPermissoes = new String[listaPermissoes.size()];
            listaPermissoes.toArray(novasPermissoes);

            //Solicitar permissoes necessarias
            ActivityCompat.requestPermissions(activity,novasPermissoes,resquestCode);


        }



        return true;
    }
}
