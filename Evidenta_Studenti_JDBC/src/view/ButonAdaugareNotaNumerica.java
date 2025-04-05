package view;

import view.Comand;
import model.NotaNumerica;


import model.TipNota;
import org.jdatepicker.impl.JDatePickerImpl;

import javax.swing.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class ButonAdaugareNotaNumerica extends JButton implements Comand {

    private JComboBox<TipNota> tipNota;
    private JLabel labelStudent;
    private JLabel labelDisciplina;
    private JTextField txtNotaExamen;
    private JTextField txtNotaLaborator;
    private JTextField txtNotaSeminar;
    private JTextField txtNotaProiect;
    private JTextField txtCoefPrezentaCurs;
    private JTextField txtCoefPrezentaLab;
    private JTextField txtCoefPrezentaSeminar;
    private JTextField txtCoefPrezentaProiect;
    private JDatePickerImpl dataExamen;


    public ButonAdaugareNotaNumerica(JComboBox<TipNota> tipNota, JLabel labelStudent, JLabel labelDisciplina, JTextField txtNotaExamen, JTextField txtNotaLaborator, JTextField txtNotaSeminar, JTextField txtNotaProiect, JTextField txtCoefPrezentaCurs, JTextField txtCoefPrezentaLab, JTextField txtCoefPrezentaSeminar, JTextField txtCoefPrezentaProiect, JDatePickerImpl dataExamen) {
        super("Adauga Nota Numerica");
        this.tipNota = tipNota;
        this.labelStudent = labelStudent;
        this.labelDisciplina = labelDisciplina;
        this.txtNotaExamen = txtNotaExamen;
        this.txtNotaLaborator = txtNotaLaborator;
        this.txtNotaSeminar = txtNotaSeminar;
        this.txtNotaProiect = txtNotaProiect;
        this.txtCoefPrezentaCurs = txtCoefPrezentaCurs;
        this.txtCoefPrezentaLab = txtCoefPrezentaLab;
        this.txtCoefPrezentaSeminar = txtCoefPrezentaSeminar;
        this.txtCoefPrezentaProiect = txtCoefPrezentaProiect;
        this.dataExamen = dataExamen;
    }

    public void execute() {
        String auxTipDisciplina = tipNota.getSelectedItem().toString();
        if(auxTipDisciplina != null || auxTipDisciplina.length() > 1) {
            String tipDisciplina = auxTipDisciplina;
        }

        String auxCodStudent = labelStudent.getText().trim();
        int pozitieS = auxCodStudent.lastIndexOf(" ");
        String codS = auxCodStudent.substring(pozitieS + 1).trim();
        String codStudent = null;
        if(codS != null && codS.length() > 1){
            codStudent = codS;
        }


        String auxlabelDisciplina = labelDisciplina.getText().trim();
//        System.err.println(auxlabelDisciplina);
        int pozitie = auxlabelDisciplina.lastIndexOf(" ");
        int cod = Integer.parseInt(auxlabelDisciplina.substring(pozitie + 1).trim());
        System.err.println(cod);
        int codDisciplina = cod;

        String auxNotaE = txtNotaExamen.getText().trim();
        int notaE= 0;
        if(auxNotaE != null && auxNotaE.length() > 1){
            notaE = Integer.parseInt(auxNotaE);
        }

        String auxNotaL = txtNotaLaborator.getText().trim();
        int notaL = 0;
        if(auxNotaL != null && auxNotaL.length() > 1) {
            notaL = Integer.parseInt(auxNotaL);
        }

        String auxNotaS = txtNotaSeminar.getText().trim();
        int notaS = 0;
        if(auxNotaS != null && auxNotaS.length() > 1) {
            notaS = Integer.parseInt(auxNotaS);
        }

        String auxNotaP = txtNotaProiect.getText().trim();
        int notaP = 0;
        if(auxNotaP != null && auxNotaP.length() > 1) {
            notaP = Integer.parseInt(auxNotaP);
        }

        String auxCoefP = txtCoefPrezentaCurs.getText().trim();
        double coefPC = 0;
        if(auxCoefP != null && auxCoefP.length() > 1) {
            coefPC = Double.parseDouble(auxCoefP);
        }


        double coefPL = 0;
        String auxCoefPL = txtCoefPrezentaLab.getText().trim();
        if(auxCoefPL != null && auxCoefPL.length() > 1) {
            coefPL = Double.parseDouble(auxCoefPL);
        }

        double coefPS = 0;
        String auxCoefPS = txtCoefPrezentaSeminar.getText().trim();
        if(auxCoefPS != null && auxCoefPS.length() > 1) {
            coefPS = Double.parseDouble(auxCoefPS);
        }


        double coefPP = 0;
        String auxCoefPP = txtCoefPrezentaProiect.getText().trim();
        if(auxCoefPP != null && auxCoefPP.length() > 1) {
            coefPP = Double.parseDouble(auxCoefPP);
        }


        int notaFinala = (int)(notaE * coefPC + notaL * coefPL + notaS * coefPS + notaP * coefPP);
        int promovat = notaFinala > 4 ? 1 : 0;

        Date selectedDate = (Date) dataExamen.getModel().getValue();
        if (selectedDate == null) {
            System.err.println("Data examenului nu este selectată.");
            return;
        }
        LocalDate dataE = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        try {
            NotaNumerica notaNumerica = new NotaNumerica(TipNota.N, codStudent, codDisciplina, dataE, notaFinala, promovat);
            System.out.println(notaNumerica.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
