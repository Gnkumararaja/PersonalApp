package com.kumararaja.personalapp;

public class DCdata {

    String id;
    String amount;
    String datte;
    String descrip;
    String bankk;
    String type;

    public DCdata(String id, String amount, String datte, String descrip, String bankk, String type) {
        this.id = id;
        this.amount = amount;
        this.datte = datte;
        this.descrip = descrip;
        this.bankk = bankk;
        this.type=type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDatte() {
        return datte;
    }

    public void setDatte(String datte) {
        this.datte = datte;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public String getBankk() {
        return bankk;
    }

    public void setBankk(String bankk) {
        this.bankk = bankk;
    }

    public String getType() {
        return type;
    }
    public void setType(String ty) {
        this.type = ty;
    }
}
