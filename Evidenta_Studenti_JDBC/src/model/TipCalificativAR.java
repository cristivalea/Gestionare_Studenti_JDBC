package model;

public enum TipCalificativAR {
//    INSUFICIENT("Insuficient"),
//    SUFICIENT("Suficient"),
//    BINE("Bine"),
//    FOARTE_BINE("Foarte bine"),
//    EXCELENT("Excelent");

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
