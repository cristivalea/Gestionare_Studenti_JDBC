package model;

public enum TipCalificativ {
    INSUFICIENT("Insuficient"),
    SUFICIENT("Suficient"),
    BINE("Bine"),
    FOARTE_BINE("Foarte bine"),
    EXCELENT("Excelent");

    private String denumire;
    TipCalificativ(String denumire){
        this.denumire = denumire;
    }

    public String getDenumire(){
        return this.denumire;
    }
}
