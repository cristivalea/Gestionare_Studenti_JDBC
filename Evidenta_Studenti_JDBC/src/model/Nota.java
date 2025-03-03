package model;

import model.TipNota;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Nota {
    protected TipNota tip_nota;
    protected String numar_matricol;
    protected int cod_disciplina;
    protected LocalDate data_examen;
    protected int promovat;

    public Nota(TipNota tip_nota, String numar_matricol, int cod_disciplina, LocalDate data_examen,  int promovat) {
        this.tip_nota = tip_nota;
        this.numar_matricol = numar_matricol;
        this.cod_disciplina = cod_disciplina;
        this.data_examen = data_examen;
        this.promovat = promovat;
    }

    public abstract boolean isPromovat();

    public abstract int getNotaFinala();

    @Override
    public String toString() {
        return "model.Nota{" +
                "tip_nota=" + tip_nota +
                ", numar_matricol='" + numar_matricol + '\'' +
                ", cod_disciplina=" + cod_disciplina +
                ", data_examen=" + data_examen +
                ", promovat=" + promovat +
                '}';
    }
}
