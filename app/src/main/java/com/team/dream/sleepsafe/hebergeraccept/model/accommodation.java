package com.team.dream.sleepsafe.hebergeraccept.model;

public class accommodation {

    private String address;
    private String city;
    private int zipcode;
    private int nb_bed;
    private int id_user;
    private String bed_available;

    public accommodation(String address, String city, int zipcode, int nb_bed, int id_user, String bed_available) {
        this.address = address;
        this.city = city;
        this.zipcode = zipcode;
        this.nb_bed = nb_bed;
        this.id_user = id_user;
        this.bed_available = bed_available;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public int getNb_bed() {
        return nb_bed;
    }

    public void setNb_bed(int nb_bed) {
        this.nb_bed = nb_bed;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getBed_avalable() {
        return bed_available;
    }

    public void setBed_avalable(String bed_avalable) {
        this.bed_available = bed_avalable;
    }


}
