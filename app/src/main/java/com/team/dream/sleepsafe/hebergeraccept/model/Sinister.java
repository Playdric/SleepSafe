package com.team.dream.sleepsafe.hebergeraccept.model;

public class Sinister {

    private String name;
    private String surname;
    private int phoneNumber;
    private String idPhone;
    private int nbPeople;
    private String comment;
    private String localisation;

    public Sinister(String name, String surname, int phoneNumber, int nbPeople, String comment, String localisation, String idPhone) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.nbPeople = nbPeople;
        this.comment = comment;
        this.localisation = localisation;
        this.idPhone = idPhone;
    }


    public int getNbPeople() {
        return nbPeople;
    }

    public void setNbPeople(int nbPeople) {
        this.nbPeople = nbPeople;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getIdPhone() {
        return idPhone;
    }

    public void setIdPhone(String idPhone) {
        this.idPhone = idPhone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
