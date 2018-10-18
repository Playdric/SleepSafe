package com.team.dream.sleepsafe.hebergeraccept.model;

public class Sinister {

    private String idPhone;
    private int nbPeople;
    private String comment;
    private String localisation;

    public Sinister(int nbPeople, String comment, String localisation, String idPhone) {
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
}
