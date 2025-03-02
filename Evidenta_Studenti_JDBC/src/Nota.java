import java.time.LocalDate;

public class Nota {
    private TipNota tip_nota;
    private String numar_matricol;
    private int cod_disciplina;
    private LocalDate data_examen;
    private int valoare_nota;
    private boolean promovat;

    public Nota(TipNota tip_nota, String numar_matricol, int cod_disciplina, LocalDate data_examen, int valoare_nota, boolean promovat) {
        this.tip_nota = tip_nota;
        this.numar_matricol = numar_matricol;
        this.cod_disciplina = cod_disciplina;
        this.data_examen = data_examen;
        this.valoare_nota = valoare_nota;
        this.promovat = promovat;
    }

    @Override
    public String toString() {
        return "Nota{" +
                "tip_nota=" + tip_nota +
                ", numar_matricol='" + numar_matricol + '\'' +
                ", cod_disciplina=" + cod_disciplina +
                ", data_examen=" + data_examen +
                ", valoare_nota=" + valoare_nota +
                ", promovat=" + promovat +
                '}';
    }
}
