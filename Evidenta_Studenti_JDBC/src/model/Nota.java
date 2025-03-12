package model;

import model.TipNota;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Nota {
    protected Student student;
    protected TipNota tip_nota;
    protected String numar_matricol;
    protected int cod_disciplina;
    protected LocalDate data_examen;
    protected int promovat;
    protected Disciplina d;
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

    public void setTip_nota(TipNota tip_nota) {
        this.tip_nota = tip_nota;
    }

    public void setNumar_matricol(String numar_matricol) {
        this.numar_matricol = numar_matricol;
    }

    public void setCod_disciplina(int cod_disciplina) {
        this.cod_disciplina = cod_disciplina;
    }

    public void setData_examen(LocalDate data_examen) {
        this.data_examen = data_examen;
    }

    public void setPromovat(int promovat) {
        this.promovat = promovat;
    }

    public void setD(Disciplina d) {
        this.d = d;
    }

    public abstract void setNotaFinala();

    public void setStudent(Student student) {
        this.student = student;
    }
}
