package com.example.petest.model;

public class Product {
    private String id;
    private String name;
    private String datexs;
    private String quicach;
    private String idNhsx;

    public Product(String name, String datexs, String quicach, String idNhsx) {
        this.name = name;
        this.datexs = datexs;
        this.quicach = quicach;
        this.idNhsx = idNhsx;
    }

    public Product(String id, String name, String datexs, String quicach, String idNhsx) {
        this.id = id;
        this.name = name;
        this.datexs = datexs;
        this.quicach = quicach;
        this.idNhsx = idNhsx;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDatexs() {
        return datexs;
    }

    public void setDatexs(String datexs) {
        this.datexs = datexs;
    }

    public String getQuicach() {
        return quicach;
    }

    public void setQuicach(String quicach) {
        this.quicach = quicach;
    }

    public String getIdNhsx() {
        return idNhsx;
    }

    public void setIdNhsx(String idNhsx) {
        this.idNhsx = idNhsx;
    }
}
