package view;//package view;

import main.Comand;


import model.NotaNumerica;

import model.TipNota;
import org.jdatepicker.impl.JDatePickerImpl;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


    public class ButonAdaugareNotaNumerica extends JButton implements Comand{

        private JComboBox<TipNota> tipNota;
        private ButonCautare butonStudent;
        private ButonCautareDiscipline butonDisciplina;
        private JTextField txtNotaExamen;
        private JTextField txtNotaLaborator;
        private JTextField txtNotaSeminar;
        private JTextField txtNotaProiect;
        private JTextField txtCoefPrezentaCurs;
        private JTextField txtCoefPrezentaLab;
        private JTextField txtCoefPrezentaSeminar;
        private JTextField txtCoefPrezentaProiect;
        private JDatePickerImpl dataExamen;


        public ButonAdaugareNotaNumerica(JComboBox<TipNota> tipNota, ButonCautare butonStudent, ButonCautareDiscipline butonDisciplina, JTextField txtNotaExamen, JTextField txtNotaLaborator, JTextField txtNotaSeminar, JTextField txtNotaProiect, JTextField txtCoefPrezentaCurs, JTextField txtCoefPrezentaLab, JTextField txtCoefPrezentaSeminar, JTextField txtCoefPrezentaProiect, JDatePickerImpl dataExamen) {
            super("Adauga Nota Numerica");
            this.tipNota = tipNota;
            this.butonStudent = butonStudent;
            this.butonDisciplina = butonDisciplina;
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
            String tipDisciplina = tipNota.getSelectedItem().toString();
            String codStudent = butonStudent.getText();
            int codDisciplina = Integer.parseInt(butonDisciplina.getText());
            int notaE = Integer.parseInt(txtNotaExamen.getText().trim());
            int notaL = Integer.parseInt(txtNotaLaborator.getText().trim());
            int notaS = Integer.parseInt(txtNotaSeminar.getText().trim());
            int notaP = Integer.parseInt(txtNotaProiect.getText().trim());
            double coefPC = Double.parseDouble(txtCoefPrezentaCurs.getText().trim());
            double coefPL = Double.parseDouble(txtCoefPrezentaLab.getText().trim());
            double coefPS = Double.parseDouble(txtCoefPrezentaSeminar.getText().trim());
            double coefPP = Double.parseDouble(txtCoefPrezentaProiect.getText().trim());
            int notaFinala = (int)(notaE * coefPC + notaL * coefPL + notaS * coefPS + notaP * coefPP);
            int promovat = 0;
            if(notaFinala >= 5){
                promovat = 1;
            }
            String dataEx = dataExamen.toString();
            LocalDate dataE = null;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            try {
                dataE = LocalDate.parse(dataEx, formatter);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                NotaNumerica notaNumerica = new NotaNumerica(TipNota.N, codStudent, codDisciplina, dataE, notaFinala, promovat);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }