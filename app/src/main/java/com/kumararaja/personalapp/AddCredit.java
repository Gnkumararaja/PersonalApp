package com.kumararaja.personalapp;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AddCredit extends Fragment {
EditText amt,date,descrip;
    String amount,ddate,des,bank;
Spinner cbank;
Button add,reset;
    DatabaseHelper databaseHelper;
    List<BankData> dataModel;
    int aa,ddd;
    private DatePicker datePicker;
    private Calendar calendar;
    private int year, month, day;
    final Calendar myCalender = Calendar.getInstance();
    BankData data;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_addcredit, container, false);
        final DatabaseHelper myDb = new DatabaseHelper(v.getContext());
        amt=(EditText)v.findViewById(R.id.ed_amt);
        date=(EditText)v.findViewById(R.id.ed_date);
        descrip=(EditText)v.findViewById(R.id.ed_desc);
        cbank=(Spinner)v.findViewById(R.id.ed_combo);
        add=(Button)v.findViewById(R.id.btnAdd);
        reset=(Button)v.findViewById(R.id.btnReset);
        databaseHelper = new DatabaseHelper(getActivity());

        final DatePickerDialog.OnDateSetListener datee=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                myCalender.set(Calendar.YEAR, i);
                myCalender.set(Calendar.MONTH,i1);
                myCalender.set(Calendar.DAY_OF_MONTH,i2);
                updateCalender();
            }
        };
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getActivity(), datee, myCalender.get(Calendar.YEAR),myCalender.get(Calendar.MONTH),
                        myCalender.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        List<String> lb=myDb.getonlyBank();

        final ArrayAdapter<String>adapter=new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_dropdown_item,lb);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cbank.setAdapter(adapter);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amt.setText("");
                date.setText("");
                descrip.setText("");
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Clicked", Toast.LENGTH_SHORT).show();
                amount = amt.getText().toString().trim();
                ddate = date.getText().toString().trim();
                des = descrip.getText().toString().trim();
                bank = cbank.getSelectedItem().toString().trim();

                dataModel = databaseHelper.getBankall();
                Toast.makeText(getActivity(), ""+dataModel, Toast.LENGTH_SHORT).show();
                Log.v("Display",""+dataModel);

                if (amount.length()>0 && ddate.length()>0 && des.length()>0 && bank.length()>0) {

                    boolean isInserted = myDb.insertData(amt.getText().toString(), ddate, des, bank,"credit");
                    if (amount.isEmpty() && ddate.isEmpty() && des.isEmpty() && bank.isEmpty()) {
                        Toast.makeText(getActivity(), "Empty Data in Database", Toast.LENGTH_SHORT).show();
                    }
                    if (isInserted == true) {
                        Toast.makeText(getActivity(), "Data Inserted", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.v("Checking", "FreshData_Not_Inserted");
                        Toast.makeText(getActivity(), "Data Not Insterted", Toast.LENGTH_SHORT).show();
                    }
                    //aa=new Integer(amount).intValue();
                    // AddData();
                    Intent a = new Intent(getActivity(), MainActivity.class);
                    startActivity(a);
                return;
                } else {
                    Toast.makeText(getActivity(), "Please enter all the feilds", Toast.LENGTH_SHORT).show();

                }
            }
        });

        return v;
    }

    private void updateCalender() {
        String format="dd/MM/yyyy";
        SimpleDateFormat sdf=new SimpleDateFormat(format, Locale.US);

        date.setText(sdf.format(myCalender.getTime()));
        date.setInputType(InputType.TYPE_NULL);
    }
   /* public void AddData() {

        Log.v("Values are",":"+aa+ddate+des+bank);
        boolean isInserted = myDb.insertData(amt.getText().toString(), ddate.toString(), des.toString(), bank.toString());
        if (amount.isEmpty() && ddate.isEmpty() && des.isEmpty() && bank.isEmpty()) {
            Toast.makeText(getActivity(), "Empty Data in Database", Toast.LENGTH_SHORT).show();
        }
        if (isInserted == true) {
            Toast.makeText(getActivity(), "Data Inserted", Toast.LENGTH_SHORT).show(); } else {
            Log.v("Checking", "FreshData_Not_Inserted");
            Toast.makeText(getActivity(), "Data Not Insterted", Toast.LENGTH_SHORT).show();
        }
    }*/
}
