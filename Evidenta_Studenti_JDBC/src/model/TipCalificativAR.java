package model;

public enum TipCalificativAR {
    ADMIS("ADMIS"),
    RESPINS("RESPINS");
    private String denumire;
    TipCalificativAR(String denumire){
        this.denumire = denumire;
    }

    public String getDenumire() {
        return denumire;
    }
}
