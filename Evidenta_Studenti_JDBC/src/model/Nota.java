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
    public abstract void adaugaNota();

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

    public String getNumar_matricol() {
        return numar_matricol;
    }

    public TipNota getTip_nota() {
        return tip_nota;
    }

    public int getCod_disciplina() {
        return cod_disciplina;
    }

    public LocalDate getData_examen() {
        return data_examen;
    }

    public int getPromovat() {
        return promovat;
    }
}
