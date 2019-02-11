package com.kumararaja.personalapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class HandCash extends Activity {

    Spinner source;
    EditText amnt;
    Button btn;
    DatabaseHelper myDb = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.handcash);

        source=(Spinner)findViewById(R.id.csource);
        amnt = (EditText) findViewById(R.id.hamnt);
        btn = (Button) findViewById(R.id.hbnkadd);

        String a[]={"Choose","Savings","Others"};

        ArrayAdapter<String>spa=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,a);
        spa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        source.setAdapter(spa);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String b = source.getSelectedItem().toString();
                String ba = amnt.getText().toString();

                if (b.equals("Choose")) {
                    Toast.makeText(HandCash.this, "Please Select a source", Toast.LENGTH_SHORT).show();;
                    return;
                } else {
                    if (TextUtils.isEmpty(ba)) {
                        amnt.setError("Enter the amount");
                    } else {
                        boolean isInserted = myDb.insertBank(ba, b, "credit");
                        /* if (b.isEmpty() && ba.isEmpty()) {
                             Toast.makeText(AddBank.this, "Empty Data in Database", Toast.LENGTH_SHORT).show();
                         }*/
                        if (isInserted == true) {
                            Toast.makeText(HandCash.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                        } else {
                            Log.v("NOT INSERTE", "" + isInserted);
                            Log.v("bank", "" + b);
                            Log.v("amount", "" + ba);
                            Toast.makeText(HandCash.this, "Data Not Insterted", Toast.LENGTH_SHORT).show();
                        }
                        Intent ik = new Intent(HandCash.this, MainActivity.class);
                        startActivity(ik);
                    }
                }
            }
        });
    }
}
