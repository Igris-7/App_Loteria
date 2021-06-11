package com.example.t4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.BaseJsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.Random;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText jtxtNum1, jtxtNum2, jtxtNum3, jtxtNum4,jtxtNum5, jtxtNum6, jtxtDNI;
    TextView jNumAzar;
    Button jbtnNext, jbtnSalir;
    String estado_azar="No";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jtxtNum1 = findViewById(R.id.txtNum1);
        jtxtNum2 = findViewById(R.id.txtNum2);
        jtxtNum3 = findViewById(R.id.txtNum3);
        jtxtNum4 = findViewById(R.id.txtNum4);
        jtxtNum5 = findViewById(R.id.txtNum5);
        jtxtNum6 = findViewById(R.id.txtNum6);
        jtxtDNI = findViewById(R.id.txtDNI);
        jNumAzar = findViewById(R.id.NumAzar);

        jbtnNext = findViewById(R.id.btnNext);
        jbtnSalir = findViewById(R.id.btnSalir);

        jbtnNext.setOnClickListener(this);
        jbtnSalir.setOnClickListener(this);
        jNumAzar.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnNext:
                siguienteActividad();
                break;

            case R.id.btnSalir:
                salir();
                break;

            case R.id.NumAzar :
                 generarNumerosAzar();
                break;
        }

    }

    private void siguienteActividad() {

        String snum1 = jtxtNum1.getText().toString().trim();
        String snum2 = jtxtNum2.getText().toString().trim();
        String snum3 = jtxtNum3.getText().toString().trim();
        String snum4 = jtxtNum4.getText().toString().trim();
        String snum5 = jtxtNum5.getText().toString().trim();
        String snum6 = jtxtNum6.getText().toString().trim();
        String sDni = jtxtDNI.getText().toString().trim();

        String separador = "-";
        String jugada =snum1 + separador + snum2 +separador + snum3 + separador + snum4 + separador +
                snum5 + separador + snum6;

        if(snum1.isEmpty() || snum2.isEmpty() || snum3.isEmpty() || snum4.isEmpty() || snum5.isEmpty() || snum6.isEmpty() || sDni.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Rellene todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        BuscaryEnviar(sDni,jugada);

    }

    private void BuscaryEnviar(final String sDni, final String jugada) {

        AsyncHttpClient cliente = new AsyncHttpClient();

        Intent inext = new Intent(this, ClienteActivity.class);

        String sURL = "http://eyner.atwebpages.com/T4/busqueda.php";
        RequestParams params = new RequestParams();
        params.add("Dni",sDni);

        cliente.post(sURL, params, new BaseJsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse, Object response) {
                if (statusCode == 200)
                    {
                        try {
                            JSONArray jsonArray = new JSONArray(rawJsonResponse);
                            if (jsonArray.length() > 0){
                                String sValor = jsonArray.getJSONObject(0).getString("dni");
                                if (sValor.equals("-1")){
                                    Toast.makeText(getApplicationContext(), "DNI no registrado", Toast.LENGTH_SHORT).show();
                                    inext .putExtra("Numeros_jugada", jugada);
                                    inext .putExtra("Dni", sDni);
                                    inext .putExtra("estado_random", estado_azar);
                                    startActivity(inext);


                                }else{
                                    Toast.makeText(getApplicationContext(), "DNI existente",Toast.LENGTH_SHORT).show();
                                }

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
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


    private void salir() {
        System.exit(1);
    }

    private void generarNumerosAzar() {

        //Generar número random
        Random random = new Random(System.currentTimeMillis());
        random.setSeed(System.currentTimeMillis());

        String num1, num2, num3,  num4, num5, num6;
        int Randnum1, Randnum2, Randnum3, Randnum4, Randnum5, Randnum6;

        //Primer número
        Randnum1 =random.nextInt(40-1+1)+1;
        num1 =String.valueOf(Randnum1);
        jtxtNum1.setText(num1);

        //Segundo número
        do {
            Randnum2= random.nextInt(40-1+1)+1;
            num2 = String.valueOf(Randnum2);
            jtxtNum2.setText(num2);

        }while (num1.equals(num2));

        //Tercer número
        do {
            Randnum3 = random.nextInt(40-1+1)+1;
            num3 = String.valueOf(Randnum3);
            jtxtNum3.setText(num3);
        }while(num1.equals(num3) || num2.equals(num3));

        //Cuarto número
        do {
            Randnum4 = random.nextInt(40-1+1)+1;
            num4 = String.valueOf(Randnum4);
            jtxtNum4.setText(num4);
        }while(num1.equals(num4) || num2.equals(num4) || num3.equals(num4));

        //Quinto número
        do {
            Randnum5 = random.nextInt(40-1+1)+1;
            num5 = String.valueOf(Randnum5);
            jtxtNum5.setText(num5);
        }while(num1.equals(num5) || num2.equals(num5) || num3.equals(num5) || num4.equals(num5));

        //Sexto número
        do {
            Randnum6 = random.nextInt(40-1+1);
            num6 = String.valueOf(Randnum6);
            jtxtNum6.setText(num6);
        }while(num1.equals(num6) || num2.equals(num6) || num3.equals(num6) || num4.equals(num6) || num5.equals(num6));

        estado_azar="Si";
    }
}