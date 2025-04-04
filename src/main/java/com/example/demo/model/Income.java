package com.example.demo.model;

public class Income {

    private String periode;
    private Float total;
    private Float salaire;
    private Float aides;
    private Float autoEntreprise;
    private Float revenusPassifs;
    private Float autres;

    public Income() {}

    public Income(String periode, Float total, Float salaire, Float aides, Float autoEntreprise,
                   Float revenusPassifs, Float autres) {
        this.periode = periode;
        this.total = total;
        this.salaire = salaire;
        this.aides = aides;
        this.autoEntreprise = autoEntreprise;
        this.revenusPassifs = revenusPassifs;
        this.autres = autres;
    }

    public String getPeriode() {
        return periode;
    }

    public Float getTotal() {
        return total;
    }

    public Float getSalaire() {
        return salaire;
    }

    public Float getAides() {
        return aides;
    }

    public Float getAutoEntreprise() {
        return autoEntreprise;
    }

    public Float getRevenusPassifs() {
        return revenusPassifs;
    }

    public Float getAutres() {
        return autres;
    }
}
