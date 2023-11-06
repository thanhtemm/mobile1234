package com.example.petest.model;

public class Nsx {
    private String idNhsx;
    private String nameNhsx;

    public Nsx(String nameNhsx) {
        this.nameNhsx = nameNhsx;
    }

    public Nsx(String idNhsx, String nameNhsx) {
        this.idNhsx = idNhsx;
        this.nameNhsx = nameNhsx;
    }

    public String getIdNhsx() {
        return idNhsx;
    }

    public void setIdNhsx(String idNhsx) {
        this.idNhsx = idNhsx;
    }

    public String getNameNhsx() {
        return nameNhsx;
    }

    public void setNameNhsx(String nameNhsx) {
        this.nameNhsx = nameNhsx;
    }
}
