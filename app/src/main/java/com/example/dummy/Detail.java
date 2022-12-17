package com.example.dummy;

import java.util.ArrayList;

public class Detail {
    private String name;
    private int id;
    private int amount;
    private String cno;
    private String Button;

    public static ArrayList<Detail> detailsArrayList = new ArrayList<>();

    public Detail(int id , String name, int amount, String cno, String button) {
        this.amount = amount;
        this.cno = cno;
        this.id = id;
        this.name = name;
        Button = button;
    }
    public String getButton() {
        return Button;
    }

    public void setButton(String button) {
        this.Button = button;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }
}
