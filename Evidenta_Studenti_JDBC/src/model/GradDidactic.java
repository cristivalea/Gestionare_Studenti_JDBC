package model;

public enum GradDidactic {
    ASISTENT("Asistent"),
    LECTOR("Lector"),
    CONFERENTIAR("Conferentiar"),
    PROFESOR("Profesor"),
    PROFESOR_ABILITAT("Profesor abilitat");

    private String denumire;

    GradDidactic(String denumire){
        this.denumire = denumire;
    }

    public String getDenumire() {
        return denumire;
    }
}
