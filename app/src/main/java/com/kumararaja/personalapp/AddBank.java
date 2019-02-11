package com.kumararaja.personalapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddBank extends Activity {

    EditText bnk,amnt;
    Button btn;
    DatabaseHelper myDb = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abank);


        bnk=(EditText)findViewById(R.id.bnkname);
        amnt=(EditText) findViewById(R.id.amnt);
        btn=(Button)findViewById(R.id.bnkadd);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 String b=bnk.getText().toString();
                 String ba=amnt.getText().toString();

                 if (TextUtils.isEmpty(b)) {
                     bnk.setError("Enter the bank name");
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
                             Toast.makeText(AddBank.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                         } else {
                             Log.v("NOT INSERTE",""+isInserted);
                             Log.v("bank",""+b);
                             Log.v("amount",""+ba);
                             Toast.makeText(AddBank.this, "Data Not Insterted", Toast.LENGTH_SHORT).show();
                         }
                         Intent ik = new Intent(AddBank.this, MainActivity.class);
                         startActivity(ik);
                     }
                 }
            }
        });
    }
}
