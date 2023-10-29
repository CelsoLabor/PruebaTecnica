package com.example.pruebatecnica;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SyncRequest;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    Button loginbutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginbutton = (Button) findViewById(R.id.loginButton);

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Username = username.getText().toString();
                String Password = password.getText().toString();



                boolean check=Validatedata(Username,Password);

                if (check==true){
                    // Muestra mensaje login exitoso
                    Toast.makeText(getApplicationContext(),"Datos válidos",Toast.LENGTH_SHORT).show();
                    // Redirige segundo activity
                    openPrincipal();
                    // Envía usuario a segundo activity
                    Intent intent = new Intent(MainActivity.this, Principal.class);
                    intent.putExtra("Usuario",Username);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(),"Verifique usuario y contraseña",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private Boolean Validatedata(String Username, String Password) {
        if(Username.length()==0){
            username.requestFocus();
            username.setError("DIGITE UN USUARIO");
            return  false;
        }else if (Username.length()<8){
            username.requestFocus();
            username.setError("El usuario no puede ser menor a ocho caracteres");
            return  false;
        } else if (!Username.matches("[a-z]+")) {
            username.requestFocus();
            username.setError("El usuario debe ser solo en letras minusculas");
            return  false;
        } else if (Password.length()==0) {
            password.requestFocus();
            password.setError("La contraseña no puede estar vacia");
            return false;
        } else if (Password.length()<6) {
            password.requestFocus();
            password.setError("La contraseña debe ser mayoy a seis caracteres");
            return  false;
        } else if (!Password.matches("[a-zA-Z]+")) {
            password.requestFocus();
            password.setError("La contraseña debe contener uno letra mayuscula");
            return  false;
        } else if (Username=="administrador"){
            username.requestFocus();
            username.setError("USUARIO INCORRECTO");
            return  false;
        }else if (Password=="Ingreso"){
            password.requestFocus();
            password.setError("CONTRASEÑA INVALIDA");
            return  false;
        }else{
            return true;
        }

    }

    public void openPrincipal(){
        Intent intent = new Intent(this,Principal.class);
        startActivity(intent);
    }
}