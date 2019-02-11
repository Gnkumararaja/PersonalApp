package com.kumararaja.personalapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class HomeActivity extends Fragment {
    TextView details, ministatement,accname,accdetail,bnk1,bnk2,bnk3,baln1,baln2,baln3;
    TableLayout tb;
    DatabaseHelper databaseHelper;
    List<BankData> dataModel;
    RecyclerView recyclerView;
    RecycleBank recycler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_homelayout, container, false);
        details=(TextView)v.findViewById(R.id.detail);
        ministatement=(TextView)v.findViewById(R.id.mini);
        recyclerView=(RecyclerView)v.findViewById(R.id.brecycle);
        databaseHelper = new DatabaseHelper(getActivity());

        ministatement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (databaseHelper.getmini().isEmpty()) {
                    Toast.makeText(getActivity(), "Please Add money", Toast.LENGTH_SHORT).show();
                } else {
                    MiniStatement miniStatement = new MiniStatement();
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.content_frame, miniStatement);
                    ft.addToBackStack(null);
                    ft.commit();
                }
            }
        });

        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (databaseHelper.getonlyBank().size()>0) {

                    dataModel = databaseHelper.getBankall();
                    recycler = new RecycleBank(getActivity(), dataModel);
                    RecyclerView.LayoutManager reLayoutManager1 = new LinearLayoutManager(getContext());
                    recyclerView.setLayoutManager(reLayoutManager1);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(recycler);

                    Log.i("Data's", "" + dataModel);
                } else {
                    Toast.makeText(getActivity(), "Please Add money", Toast.LENGTH_SHORT).show();
                }

                    /*return v;*/



         /*   DatabaseHelper databaseHelper=new DatabaseHelper(getContext());
                SQLiteDatabase db = databaseHelper.getWritableDatabase();
                String qq=" SELECT * FROM  cdtable";
                Log.e("","Tab"+qq);
                Cursor c=db.rawQuery(qq,null);

                for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
                    // The Cursor is now set to the right position
                   Integer amnt= Integer.valueOf(c.getString(1));
                   String bank=c.getString(4);
                   String type=c.getString(5);

                    switch (bank){
                        case "bank1":
                            break;

                    }
                                   }
                c.close();
*/
            }
        });
    return v;
    }
}
