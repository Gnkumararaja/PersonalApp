package com.kumararaja.personalapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecycleBank extends RecyclerView.Adapter<RecycleBank.MyHolder> {
    List<BankData> bankList;
    Context cntx;


    public RecycleBank(Context context, List<BankData> banklist) {
        this.bankList = banklist;
        this.cntx = context;

    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int ViewType){
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.bankview,null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int position) {
        BankData data=bankList.get(position);
        /*myHolder.amount.setText(data.getAmount());*/
        myHolder.bank.setText(data.getBank());
        int debit=0, credit =0;
        ArrayList<DCdata> dcarrayList = new DatabaseHelper(cntx).getmini();
        for(int i=0;i<dcarrayList.size();i++){
            if(myHolder.bank.getText().equals(dcarrayList.get(i).bankk)){
                if(dcarrayList.get(i).type.equals("credit")){
                    credit +=Integer.parseInt(dcarrayList.get(i).amount);
                } else if (dcarrayList.get(i).type.equals("debit")){
                    debit+=Integer.parseInt(dcarrayList.get(i).amount);
                }
            }
        }
        int j=Integer.parseInt(data.getAmount())+ credit -debit;
        myHolder.amount.setText(String.valueOf(j));

    }

    @Override
    public int getItemCount() {
        return bankList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView amount, bank;

        public MyHolder(View view) {
            super(view);

            amount = (TextView) view.findViewById(R.id.tamnt);
            bank = (TextView) view.findViewById(R.id.tbank);

        }
    }
}
