package com.example.shippingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.EditText;
import android.widget.TextView;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity {

    private Spinner s_cont , s_pais;
    private EditText  peso , nombre;
    private TextView result;


    ArrayList <String> arrayList_cont;
    ArrayAdapter<String> arrayAdapter_parent;

    ArrayList<String> arrayList_AN,arrayList_AC,arrayList_AS,arrayList_E,arrayList_A;
    ArrayAdapter<String> arrayAdapter_child;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        s_cont=findViewById(R.id.spinnerCont);
        s_pais=findViewById(R.id.spinnerPais);
        result=findViewById(R.id.txtCostoEnvio);
        peso=findViewById(R.id.txtInputPeso);
        nombre=findViewById(R.id.txtInputNombre);


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

  public void RealizarCal (View view) {


        int precioporgramo = 0;
        String value1 = peso.getText().toString();
        int pesopaquete = Integer.parseInt(value1);
        int costoenvio =0;

        String selection = s_cont.getSelectedItem().toString();

        switch(selection) {
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
            String resultado = String.valueOf(costoenvio);
            result.setText(resultado);

        } else
            {

               AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
                dialogBuilder.setMessage("El peso del paquete es superior a 5000g, paquetes con peso superior a 5000g no pueden ser enviados");
                dialogBuilder.setCancelable(true).setTitle("SU PAQUETE NO PUEDE SER ENVIADO");
                dialogBuilder.create().show();

                String resultado = "Paquete no puede ser enviado";
                result.setText(resultado);


        }



    }

    public void insert(View v) {

        thunderShippingDb admin = new thunderShippingDb (this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String pesobd = peso.getText().toString();
        String nombrebd = nombre.getText().toString();
        String costobd = result.getText().toString();
        String continentebd = s_cont.getSelectedItem().toString();
        String paisdb = s_pais.getSelectedItem().toString();

        ContentValues registro = new ContentValues();
        registro.put("nombre",nombrebd);
        registro.put("peso",pesobd);
        registro.put("continente",continentebd);
        registro.put("pais",paisdb);
        registro.put("costo",costobd);

        bd.insert("envios",null,registro);
        String selectQuery = "select * from envios where id = (select max(id) from envios);";
        Cursor cursor = bd.rawQuery(selectQuery,null);

        String idactual="";
        String nameactual=" ";
        String pesoactual="";
        String continenteactual="";
        String paisactual="";
        int costoactual=0;

        if(cursor.moveToLast()){
           idactual = cursor.getString(0);
           nameactual = cursor.getString(1);
           pesoactual = cursor.getString(2);
           continenteactual = cursor.getString(3);
           paisactual = cursor.getString(4);
           costoactual = cursor.getInt(5);

            //--get other cols values
        }




        bd.close();

        peso.setText("");
        nombre.setText("");
        result.setText("");
        s_cont.setSelection(0);
        s_pais.setSelection(0);


        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setMessage( "Nombre: "+nameactual + "\n" + "Peso(g): "+pesoactual +"\n"+"Continente: "+continenteactual+"\n"+"Pais: "+paisactual+"\n"+"Costo Envio: "+costoactual+ "\n"+ "El ID asignado para su ITEM es: "+ idactual);
        dialogBuilder.setCancelable(true).setTitle("Nuevo registro para el ITEM");
        dialogBuilder.create().show();


    }





}
