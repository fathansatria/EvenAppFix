package com.example.eventapp.Model;

public class PesertaModel {

    private String namaPeserta;
    private String email;
    private String phone;
    private int id_event;

    public void setNamaPeserta(String namaPeserta) {
        this.namaPeserta = namaPeserta;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public String getNamaPeserta() {
        return namaPeserta;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public int getId_event() {
        return id_event;
    }
}
