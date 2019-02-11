package com.kumararaja.personalapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class MiniStatement extends Fragment {
    DatabaseHelper databaseHelper;
    List<DCdata> dataModel;
    RecyclerView recyclerView;
    AdapterClass recycler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ministatement, container, false);
        recyclerView=(RecyclerView)v.findViewById(R.id.recycle);

        databaseHelper = new DatabaseHelper(getActivity());
       /* dataModel = databaseHelper.getAllData();
        recycler = new AdapterClass(dataModel);*/
        //RecyclerView.LayoutManager reLayoutManager = new LinearLayoutManager(getContext());
      //  recyclerView.setLayoutManager(reLayoutManager);
        //recyclerView.setItemAnimator(new DefaultItemAnimator());
       // recyclerView.setAdapter(recycler);

        dataModel = databaseHelper.getmini();
        recycler = new AdapterClass(getActivity(),dataModel);
        RecyclerView.LayoutManager reLayoutManager1 = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(reLayoutManager1);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recycler);

        Log.i("Data's", "" + dataModel);
        return v;
    }
    /*private void daata() {

        databaseHelper = new DatabaseHelper(getActivity());
        dataModel = databaseHelper.getAllData();

        recycler = new AdapterClass(dataModel);

        Log.i("Data's", "" + dataModel);
        RecyclerView.LayoutManager reLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(reLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recycler);
    }*/
}
