package com.example.shippingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import  android.widget.Spinner;
import android.widget.Toast;
import android.content.ContentValues;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    EditText txtid , txtname, txtpeso , txtcosto;
    Spinner s_cont , s_pais;
    int posicionpais = 0;

    private Handler mhandler = new Handler();

    ArrayList <String> arrayList_cont;
    ArrayAdapter<String> arrayAdapter_parent;

    ArrayList<String> arrayList_AN,arrayList_AC,arrayList_AS,arrayList_E,arrayList_A;
    ArrayAdapter<String> arrayAdapter_child;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        txtid = findViewById(R.id.txtID);
        txtname = findViewById(R.id.txtNames);
        txtpeso = findViewById(R.id.txtPesos);
        txtcosto = findViewById(R.id.txtCostos);
        s_cont = findViewById(R.id.spinnerContinente);
        s_pais = findViewById(R.id.spinnerPaises);

        //======= ArrayList Continentes =========

        arrayList_cont= new ArrayList<>();
        arrayList_cont.add("");
        arrayList_cont.add("América del Norte");
        arrayList_cont.add("América Central");
        arrayList_cont.add("América del Sur");
        arrayList_cont.add("Europa");
        arrayList_cont.add("Asia");

        arrayAdapter_parent=new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_item_fiftydiez, arrayList_cont);

        s_cont.setAdapter(arrayAdapter_parent);

        //============= spinner dinamico ==========

        //====== ArrayList Numero1 =========
        arrayList_AN= new ArrayList<>();
        arrayList_AN.add("");
        arrayList_AN.add("Estados Unidos");
        arrayList_AN.add("Mexico");
        arrayList_AN.add("Canada");

        //========= ArrayList Numero2 =======

        arrayList_AC= new ArrayList<>();
        arrayList_AC.add("");
        arrayList_AC.add("Guatemala");
        arrayList_AC.add("Honduras");
        arrayList_AC.add("El Salvador");
        arrayList_AC.add("Nicaragua");
        arrayList_AC.add("Costa Rica");
        arrayList_AC.add("Panama");

        //======== ArrayList Numero3 =========

        arrayList_AS= new ArrayList<>();
        arrayList_AS.add("");
        arrayList_AS.add("Argentina");
        arrayList_AS.add("Brasil");
        arrayList_AS.add("Bolivia");
        arrayList_AS.add("Colombia");
        arrayList_AS.add("Chile");
        arrayList_AS.add("Ecuador");


        //========= ArrayList Numero4 =======

        arrayList_E= new ArrayList<>();
        arrayList_E.add("");
        arrayList_E.add("Alemania");
        arrayList_E.add("Francia");
        arrayList_E.add("España");
        arrayList_E.add("Inglaterra");
        arrayList_E.add("Suecia");
        arrayList_E.add("Italia");


        //============ ArrayList Numero5 =======

        arrayList_A = new ArrayList<>();
        arrayList_A.add("");
        arrayList_A.add("China");
        arrayList_A.add("Japón");
        arrayList_A.add("Irán");
        arrayList_A.add("Mongolia");
        arrayList_A.add("India");
        arrayList_A.add("Corea del Sur");

        s_cont.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position==0){ }

                if(position==1){

                    arrayAdapter_child = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_item_fiftydiez, arrayList_AN );

                }

                if(position==2){

                    arrayAdapter_child = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_item_fiftydiez, arrayList_AC );

                }

                if(position==3){

                    arrayAdapter_child = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_item_fiftydiez, arrayList_AS );

                }

                if(position==4){

                    arrayAdapter_child = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_item_fiftydiez, arrayList_E );

                }

                if(position==5){

                    arrayAdapter_child = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_item_fiftydiez, arrayList_A );

                }

                s_pais.setAdapter(arrayAdapter_child);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void consulta (View v) {

        String paisbd;
        String contbd;
        int posicioncont = 0;
        int costoabd = 0;


        thunderShippingDb admin = new thunderShippingDb(this , "administracion",null,1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String id = txtid.getText().toString();
        Cursor fila = bd.rawQuery("select nombre,peso,continente,pais,costo from envios where id=" + id,null);
        if (fila.moveToFirst()) {

            txtname.setText(fila.getString(0));
            txtpeso.setText(fila.getString(1));


            costoabd = fila.getInt(4);
            String resultado = String.valueOf(costoabd);
            txtcosto.setText(resultado);

            contbd = fila.getString(2);
            paisbd = fila.getString(3);

            switch(contbd) {
                case "América del Norte":
                    posicioncont = 1;
                    break;
                case "América Central":
                    posicioncont = 2;
                    break;

                case "América del Sur":
                    posicioncont = 3;
                    break;
                case "Europa":
                    posicioncont = 4;
                    break;
                case "Asia":
                    posicioncont = 5;
                    break;

            }

            s_cont.setSelection(posicioncont);



            if (posicioncont == 1) {

                switch(paisbd) {
                    case "Estados Unidos":
                        posicionpais  = 1;
                        break;
                    case "Mexico":
                        posicionpais  = 2;
                        break;

                    case "Canada":
                        posicionpais  = 3;
                        break;

                }


            } else if (posicioncont == 2){



                switch(paisbd) {
                    case "Guatemala":
                        posicionpais = 1;
                        break;
                    case "Honduras":
                        posicionpais = 2;
                        break;

                    case "El Salvador":
                        posicionpais = 3;
                        break;

                    case "Nicaragua":
                        posicionpais = 4;
                        break;
                    case "Costa Rica":
                        posicionpais = 5;
                        break;

                    case "Panama":
                        posicionpais = 6;
                        break;

                }

            } else if (posicioncont == 3){



                switch(paisbd) {
                    case "Argentina":
                        posicionpais = 1;
                        break;
                    case "Brasil":
                        posicionpais = 2;
                        break;

                    case "Bolivia":
                        posicionpais = 3;
                        break;

                    case "Colombia":
                        posicionpais = 4;
                        break;
                    case "Chile":
                        posicionpais = 5;
                        break;

                    case "Ecuador":
                        posicionpais = 6;
                        break;

                }

            } else if (posicioncont == 4){



                switch(paisbd) {
                    case "Alemania":
                        posicionpais = 1;
                        break;
                    case "Francia":
                        posicionpais = 2;
                        break;

                    case "España":
                        posicionpais = 3;
                        break;

                    case "Inglaterra":
                        posicionpais = 4;
                        break;
                    case "Suecia":
                        posicionpais = 5;
                        break;

                    case "Italia":
                        posicionpais = 6;
                        break;

                }

            } else if (posicioncont == 5){




                switch(paisbd) {
                    case "China":
                        posicionpais = 1;

                        break;
                    case "Japón":
                        posicionpais = 2;
                        break;

                    case "Irán":
                        posicionpais = 3;
                        break;

                    case "Mongolia":
                        posicionpais = 4;
                        break;
                    case "India":
                        posicionpais = 5;
                        break;

                    case "Corea del Sur":
                        posicionpais = 6;

                        break;

                }

            }

            mhandler.postDelayed(endiable,30);



        } else {

            Toast.makeText(this,"No existe un ITEM con el ID: "+ id,Toast.LENGTH_SHORT).show();
            bd.close();

        }




    }

        private Runnable endiable = new Runnable() {
            @Override
            public void run() {

                s_pais.setSelection(posicionpais);

            }
        };


        public void eliminar (View v) {

            thunderShippingDb admin = new thunderShippingDb(this,"administracion",null,1);
            SQLiteDatabase bd = admin.getWritableDatabase();
            String id = txtid.getText().toString();
            int cant = bd.delete("envios","id=" + id,null);
            bd.close();

            txtid.setText("");
            txtname.setText("");
            txtcosto.setText("");
            txtpeso.setText("");
            s_cont.setSelection(0);
            s_pais.setSelection(0);

            if (cant ==1){

                Toast.makeText(this,"Se borró el ITEM con el ID: " +id,Toast.LENGTH_SHORT).show();

            } else {

                Toast.makeText(this,"No existe un ITEM con el ID: " +id,Toast.LENGTH_SHORT).show();

            }


        }

    public String RealizarCal (String peso , String continente) {


        int precioporgramo = 0;
        int pesopaquete = Integer.parseInt(peso);
        int costoenvio =0;
        String resultado = "";

        switch(continente) {
            case "América del Norte":
                precioporgramo= 3800;
                break;
            case "América Central":
                precioporgramo= 3100;
                break;

            case "América del Sur":
                precioporgramo= 2900;
                break;
            case "Europa":
                precioporgramo= 4200;
                break;
            case "Asia":
                precioporgramo= 5300;
                break;

        }

        if (pesopaquete <= 5000)
        {

            costoenvio = (pesopaquete*precioporgramo);
            resultado = String.valueOf(costoenvio);

        } else
        {

            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
            dialogBuilder.setMessage("El peso del paquete no debe superar los 5000g");
            dialogBuilder.setCancelable(true).setTitle("PESO INVALIDO!!");
            dialogBuilder.create().show();
            resultado = "0";
        }

        return resultado;

    }


        public void modificar (View v) {

            thunderShippingDb admin = new thunderShippingDb(this, "administracion", null, 1);
            SQLiteDatabase bd = admin.getWritableDatabase();
            String iddb = txtid.getText().toString();
            String pesobd = txtpeso.getText().toString();
            String nombrebd = txtname.getText().toString();
            String costobd = txtcosto.getText().toString();
            String continentebd = s_cont.getSelectedItem().toString();
            String paisdb = s_pais.getSelectedItem().toString();


            String veririficacion = RealizarCal(pesobd,continentebd);

            if (veririficacion=="0"){

                Toast.makeText(this,"No es posible modificar el ITEM revise la informacion e intente denuevo", Toast.LENGTH_SHORT).show();

            } else {

                ContentValues registro = new ContentValues();

                registro.put("nombre",nombrebd);
                registro.put("peso",pesobd);
                registro.put("continente",continentebd);
                registro.put("pais",paisdb);
                registro.put("costo",veririficacion);

                int cant = bd.update("envios", registro, "id="+iddb,null);
                bd.close();
                if (cant==1){

                    Toast.makeText(this,"Se han modificado los datos del ITEM con el ID: "+ iddb, Toast.LENGTH_SHORT).show();

                } else {

                    Toast.makeText(this,"No existe el ITEM con el ID: "+ iddb, Toast.LENGTH_SHORT).show();

                }


            }




        }





}