package com.example.t4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.BaseJsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import cz.msebera.android.httpclient.Header;

public class ClienteActivity extends AppCompatActivity implements View.OnClickListener {

    EditText jtxtNombre, jtxtDireccion, jtxtDistrito;
    Button jbtnRegistrar, jbtnAtras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

        jtxtNombre = findViewById(R.id.txtNombre);
        jtxtDireccion = findViewById(R.id.txtDireccion);
        jtxtDistrito = findViewById(R.id.txtDistrito);

        jbtnRegistrar = findViewById(R.id.btnRegistrar);
        jbtnAtras = findViewById(R.id.btnAtras);

        jbtnRegistrar.setOnClickListener(this);
        jbtnAtras.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnRegistrar:
                registrarCliente();
                break;
            case R.id.btnAtras :
                Atras();
                break;
        }
    }

    private void Atras() {
        Intent iatras= new Intent(this, MainActivity.class);
        startActivity(iatras);

    }


    private void registrarCliente() {

        String nombre = jtxtNombre.getText().toString().trim();
        String direccion = jtxtDireccion.getText().toString().trim();
        String distrito = jtxtDistrito.getText().toString().trim();
        String jugada = (this.getIntent().getStringExtra("Numeros_jugada")).toString().trim();
        String dni = (this.getIntent().getStringExtra("Dni")).toString().trim();
        String estado = (this.getIntent().getStringExtra("estado_random")).toString().trim();


        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String fecha_actual = df.format(c.getTime());


        // Toast.makeText(getApplicationContext(), "Jugada " +jugada, Toast.LENGTH_LONG).show();

        if(nombre.isEmpty() || direccion.isEmpty() || distrito.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Rellene todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        registro(nombre,direccion,distrito,jugada,dni,estado, fecha_actual);

    }

    private void registro(String nombre, String direccion, String distrito, String jugada, String dni, String estado , String fecha_actual) {

        AsyncHttpClient cliente_insertar = new AsyncHttpClient();
        String surl="http://eyner.atwebpages.com/T4/registrar.php";

        RequestParams params = new RequestParams();
        params.add("nombre",nombre);
        params.add("direccion", direccion);
        params.add("distrito",distrito);
        params.add("jugada",jugada);
        params.add("dni",dni);
        params.add("estado",estado);
        params.put("fecha",fecha_actual);

        cliente_insertar.post(surl, params, new BaseJsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse, Object response) {
                if(statusCode == 200)
                {
                    Toast.makeText(getApplicationContext(), "Usuario registrado", Toast.LENGTH_SHORT).show();
                    jtxtNombre.setText("");
                    jtxtDireccion.setText("");
                    jtxtDistrito.setText("");
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, String rawJsonData, Object errorResponse) {

            }

            @Override
            protected Object parseResponse(String rawJsonData, boolean isFailure) throws Throwable {
                return null;
            }
        });

    }
}