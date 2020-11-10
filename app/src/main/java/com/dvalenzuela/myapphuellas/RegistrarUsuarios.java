package com.dvalenzuela.myapphuellas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.dvalenzuela.myapphuellas.entidades.Usuario;
import com.dvalenzuela.myapphuellas.modelo.DAOUsuario;
import com.dvalenzuela.myapphuellas.util.FuncionesComunes;

public class RegistrarUsuarios extends AppCompatActivity {

    EditText txtNombres, txtApellidos, txtCorreo, txtContrasenia, txtRepetirContrasenia;
    CheckBox chkTerminosCond;
    TextView lnkIniciarSesionR;
    Button btnGuardar;

    DAOUsuario daoUsuario = new DAOUsuario(this);
    Usuario usuario;
    FuncionesComunes funcionesComunes = new FuncionesComunes();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuarios);
        daoUsuario.openDB();
        asignarReferencias();
    }

    private void asignarReferencias(){
        txtNombres = findViewById(R.id.txtNombres);
        txtApellidos = findViewById(R.id.txtApellidos);
        txtCorreo = findViewById(R.id.txtCorreo);
        txtContrasenia = findViewById(R.id.txtContrasenia);
        txtRepetirContrasenia = findViewById(R.id.txtRepetirContrasenia);
        chkTerminosCond = findViewById(R.id.chkTerminosCond);

        btnGuardar = findViewById(R.id.btnRegistrar);

        lnkIniciarSesionR = findViewById(R.id.lnkIniciarSesiónR);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombres, apellidos, correo, contrasenia;

                nombres = txtNombres.getText().toString();
                apellidos = txtApellidos.getText().toString();
                correo = txtCorreo.getText().toString();
                contrasenia = txtContrasenia.getText().toString();

                boolean resultado = validarCampos(txtNombres, txtApellidos, txtCorreo, txtContrasenia, txtRepetirContrasenia);

                if (!resultado){
                    mostrarAlerta("Por favor ingrese los campos obligatorios.");
                }else if(!funcionesComunes.validarEmail(correo)){
                    txtCorreo.setError("Ingrese un correo valido.");
                    mostrarAlerta("Ingrese un correo valido.");
                }else if(!funcionesComunes.validarPwd(contrasenia)){
                    txtContrasenia.setError("Ingrese una contraseña valida.");
                    mostrarAlerta("Ingrese una contraseña valida. Entre 8 a 16 caracteres, número, minuscula, mayuscula.");
                }else if(!chkTerminosCond.isChecked())
                {
                    mostrarAlerta("Tiene que aceptar los términos y condiciones.");
                }
                else
                {
                    usuario = new Usuario(nombres, apellidos, correo, contrasenia);
                    daoUsuario.registrarLibro(usuario);
                }

            }
        });

        lnkIniciarSesionR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(RegistrarUsuarios.this, MainActivity.class);
                //startActivity(intent);
            }
        });
    }

    private boolean validarCampos(EditText ptxtNombres,EditText ptxtApellidos,EditText ptxtCorreo,EditText ptxtContrasenia,EditText ptxtRepetirContrasenia){

        boolean resultado = true;

        String nombres = txtNombres.getText().toString();
        String apellidos = txtApellidos.getText().toString();
        String correo = txtCorreo.getText().toString();
        String contrasenia = txtContrasenia.getText().toString();
        String repetirContrasenia = txtRepetirContrasenia.getText().toString();

        if (nombres.isEmpty()){
            ptxtNombres.setError("Ingresar Nombres");
            resultado = false;
        }

        if (apellidos.isEmpty()){
            ptxtApellidos.setError("Ingresar Apellidos");
            resultado = false;
        }

        if (correo.isEmpty()){
            ptxtCorreo.setError("Ingresar correo");
            resultado = false;
        }

        if (contrasenia.isEmpty()){
            ptxtContrasenia.setError("Ingresar contraseña");
            resultado = false;
        }

        if (repetirContrasenia.isEmpty()){
            ptxtRepetirContrasenia.setError("Ingresar contraseña");
            resultado = false;
        }

        if (!contrasenia.equals(repetirContrasenia)){
            ptxtRepetirContrasenia.setError("Contraseña incorrecta");
            resultado = false;
        }

        return resultado;
    }

    private void mostrarAlerta(String msj){
        new AlertDialog.Builder(this)
                .setTitle("Observación")
                .setMessage(msj)
                .setPositiveButton("OK", null).show();
    }

}