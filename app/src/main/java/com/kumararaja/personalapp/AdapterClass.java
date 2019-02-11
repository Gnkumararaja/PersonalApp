package com.kumararaja.personalapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.MyHolder> {
    List<DCdata> dcarrayList;
    Context cntx;

    public AdapterClass(Context context,List<DCdata>dcarrayList){
        this.dcarrayList=dcarrayList;
        this.cntx=context;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int ViewType){
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview,null);
        return new MyHolder(view);
    }

    /*@NonNull
    @Override
    public AdapterClass.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }*/

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int position) {
        DCdata dCdata=dcarrayList.get(position);
        myHolder.id.setText(dCdata.getId());
        myHolder.amount.setText(dCdata.getAmount());
        myHolder.date.setText(dCdata.getDatte());
        myHolder.descrp.setText(dCdata.getDescrip());
        myHolder.bank.setText(dCdata.getBankk());
        if (dCdata.getType().equals("credit")) {
            myHolder.type.setImageDrawable(cntx.getResources().getDrawable(R.drawable.creditm));
        } else {
            myHolder.type.setImageDrawable(cntx.getResources().getDrawable(R.drawable.debitm));
        }
      /*
        int k=0,k1=0;
dcarrayList=new DatabaseHelper(cntx).getmini();;
        for(int i=0;i<dcarrayList.size();i++){
    if(myHolder.id.getText().equals(dcarrayList.get(i).bankk)){
        if(dcarrayList.get(i).type.equals("credit")){
            k+=Integer.parseInt(dcarrayList.get(i).amount);
        } else if (dcarrayList.get(i).type.equals("debit")){
            k1+=Integer.parseInt(dcarrayList.get(i).amount);
        }
    }
}

myHolder.amount.setText(Integer.parseInt(dCdata.getAmount())+k-k1);
      */


        /*DCdata dCdataa=dcarrayList.get(position);
        myHolder.id.setText(dCdataa.getId());
        myHolder.amount.setText(dCdataa.getAmount());
        myHolder.date.setText(dCdataa.getDatte());
        myHolder.descrp.setText(dCdataa.getDescrip());
        myHolder.bankk.setText(dCdataa.getBankk());*/

    }

    @Override
    public int getItemCount() {
        return dcarrayList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        TextView id,amount,date,descrp,bank/*idd,amountt,datee,descrpp,bankk*/;
        ImageView type;

        public MyHolder(View itemView){
            super(itemView);

            id=(TextView)itemView.findViewById(R.id.autoid);
            amount=(TextView) itemView.findViewById(R.id.itamt);
            date=(TextView) itemView.findViewById(R.id.itdatee);
            descrp=(TextView)itemView.findViewById(R.id.itdescip);
            bank=(TextView) itemView.findViewById(R.id.itbank);
            type=(ImageView)itemView.findViewById(R.id.typee);


        }
    }
}
