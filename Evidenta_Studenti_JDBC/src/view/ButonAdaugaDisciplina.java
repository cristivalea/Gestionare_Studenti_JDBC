package view;

import model.Disciplina;

import javax.swing.*;

public class ButonAdaugaDisciplina extends JButton implements Comand{
    private JTextField txtCodDisciplina;
    private JTextField txtNumeDisciplina;
    private JTextField txtCoefExamen;
    private JTextField txtCoefLab;
    private JTextField txtCoefProiect;
    private JTextField txtCoefSeminar;
    private JTextField txtCoefPrezentaCurs;
    private JTextField txtCoefPrezentaLab;
    private JTextField txtCoefPrezentaSeminar;
    private JTextField txtCoefPrezentaProiect;
    private JComboBox<String> txtNrCredite;

    public ButonAdaugaDisciplina(JTextField txtCodDisciplina, JTextField txtNumeDisciplina, JTextField txtCoefExamen, JTextField txtCoefLab, JTextField txtCoefSeminar ,JTextField txtCoefProiect, JTextField txtCoefPrezentaCurs, JTextField txtCoefPrezentaLab, JTextField txtCoefPrezentaSeminar, JTextField txtCoefPrezentaProiect, JComboBox<String> nrCredite) {
        super("Adaugă Disciplina");
        this.txtCodDisciplina = txtCodDisciplina;
        this.txtNumeDisciplina = txtNumeDisciplina;
        this.txtCoefExamen = txtCoefExamen;
        this.txtCoefLab = txtCoefLab;
        this.txtCoefProiect = txtCoefProiect;
        this.txtCoefSeminar = txtCoefSeminar;
        this.txtCoefPrezentaCurs = txtCoefPrezentaCurs;
        this.txtCoefPrezentaLab = txtCoefPrezentaLab;
        this.txtCoefPrezentaSeminar = txtCoefPrezentaSeminar;
        this.txtCoefPrezentaProiect = txtCoefPrezentaProiect;
        this.txtNrCredite = nrCredite;
    }

    public void execute(){
        String numeDisciplina = txtNumeDisciplina.getText().trim();
        int codDisciplina = Integer.parseInt(txtCodDisciplina.getText().trim());
        double coefExamen = Double.parseDouble(txtCoefExamen.getText().trim());
        double coefLab = Double.parseDouble(txtCoefLab.getText().trim());
        double coefProiect = Double.parseDouble(txtCoefProiect.getText().trim());
        double coefSeminar = Double.parseDouble(txtCoefSeminar.getText().trim());
        double coefPrezentaCurs = Double.parseDouble(txtCoefPrezentaCurs.getText().trim());
        double coefPrezentaLab = Double.parseDouble(txtCoefPrezentaLab.getText().trim());
        double coefPrezentaSeminar = Double.parseDouble(txtCoefPrezentaSeminar.getText().trim());
        double coefPrezentaProiect = Double.parseDouble(txtCoefPrezentaProiect.getText().trim());
        short nrCredite = Short.parseShort(this.txtNrCredite.getSelectedItem().toString());
        Disciplina disciplina = new Disciplina(codDisciplina, numeDisciplina, coefExamen, coefLab, coefProiect, coefSeminar, coefPrezentaCurs, coefPrezentaLab, coefPrezentaProiect, coefPrezentaSeminar, nrCredite);
        System.out.println(disciplina);
        this.txtCodDisciplina.setText(String.valueOf(disciplina.getCodDisciplina()));
    }
}
