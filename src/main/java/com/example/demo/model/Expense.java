package com.example.demo.model;

public class Expense {

    private String periode;
    private Float total;
    private Float logement;
    private Float nourriture;
    private Float sorties;
    private Float transport;
    private Float voyage;
    private Float impot;
    private Float autres;

    public Expense() {}

    public Expense(String periode, Float total, Float logement, Float nourriture, Float sorties,
                   Float transport, Float voyage, Float impot, Float autres) {
        this.periode = periode;
        this.total = total;
        this.logement = logement;
        this.nourriture = nourriture;
        this.sorties = sorties;
        this.transport = transport;
        this.voyage = voyage;
        this.impot = impot;
        this.autres = autres;
    }

    public String getPeriode() {
        return periode;
    }

    public Float getLogement() {
        return logement;
    }

    public Float getNourriture() {
        return nourriture;
    }

    public Float getSorties() {
        return sorties;
    }

    public Float getTransport() {
        return transport;
    }

    public Float getVoyage() {
        return voyage;
    }

    public Float getImpot() {
        return impot;
    }

    public Float getAutres() {
        return autres;
    }

    public Float getTotal() {
        return total;
    }
}
