package com.example.login_page;

public class Instance {

    private int Id_data;

    private static final Instance id_data = new Instance();


    public void setId_data(int idData)
    {
        Id_data = idData;
    }


    public static Instance getInstance()
    {
        return id_data; // carrying the data in between the scenes
    }




    public int getId_data() {

        return Id_data;
    }
}
