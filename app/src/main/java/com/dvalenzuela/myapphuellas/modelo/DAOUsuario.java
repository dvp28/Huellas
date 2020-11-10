package com.dvalenzuela.myapphuellas.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.dvalenzuela.myapphuellas.entidades.Usuario;
import com.dvalenzuela.myapphuellas.util.Constantes;
import com.dvalenzuela.myapphuellas.util.HuellasDB;

public class DAOUsuario {
    HuellasDB dbEstadio;
    SQLiteDatabase db;
    private Context context;

    public DAOUsuario(Context context){
        dbEstadio = new HuellasDB(context);
        this.context = context;
    }

    public void openDB(){
        db = dbEstadio.getWritableDatabase();
    }

    public void registrarLibro(Usuario usuario){
        try {
            ContentValues values = new ContentValues();
            values.put("nombres", usuario.getNombres());
            values.put("apellidos", usuario.getApellidos());
            values.put("correo", usuario.getCorreo());
            values.put("contrasenia", usuario.getContrasenia());

            long resultado = db.insert(Constantes.NOMBRE_TABLA_USUARIO,null,values);

            if (resultado == -1){
                Toast.makeText(context, "Error al insertar", Toast.LENGTH_SHORT).show();
            }else
            {
                Toast.makeText(context, "Se registro correctamente", Toast.LENGTH_SHORT).show();
            }

        }catch (Exception e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}
