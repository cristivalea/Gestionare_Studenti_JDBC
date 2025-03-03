package model;

import com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBaseIterators;

public enum TipCalificativ {
//    INSUFICIENT("Insuficient"),
//    SUFICIENT("Suficient"),
//    BINE("Bine"),
//    FOARTE_BINE("Foarte bine"),
//    EXCELENT("Excelent");

    ADMIS("ADMIS"),
    RESPINS("RESPINS");
    private String denumire;
    TipCalificativ(String denumire){
        this.denumire = denumire;
    }

    public String getDenumire() {
        return denumire;
    }
}
