package view;

import main.Comand;
import model.NotaNumerica;
import model.TipNota;
import org.jdatepicker.impl.JDatePickerImpl;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ButonAdaugareNotaNumerica extends JButton implements Comand {

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

    @Override
    public void execute() {
        // Validare câmpuri numerice
        int notaE = 0, notaL = 0, notaS = 0, notaP = 0;
        double coefPC = 0, coefPL = 0, coefPS = 0, coefPP = 0;
        try {
            notaE = Integer.parseInt(txtNotaExamen.getText().trim());
            notaL = Integer.parseInt(txtNotaLaborator.getText().trim());
            notaS = Integer.parseInt(txtNotaSeminar.getText().trim());
            notaP = Integer.parseInt(txtNotaProiect.getText().trim());
            coefPC = Double.parseDouble(txtCoefPrezentaCurs.getText().trim());
            coefPL = Double.parseDouble(txtCoefPrezentaLab.getText().trim());
            coefPS = Double.parseDouble(txtCoefPrezentaSeminar.getText().trim());
            coefPP = Double.parseDouble(txtCoefPrezentaProiect.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Te rog introduceti doar numere pentru note!");
            return; // Ieși din metodă dacă datele sunt invalide
        }

        // Calcul nota finală
        int notaFinala = (int)(notaE * coefPC + notaL * coefPL + notaS * coefPS + notaP * coefPP);
        int promovat = (notaFinala >= 5) ? 1 : 0;

        // Obținerea datei examenului
        LocalDate dataE = null;
        if (dataExamen.getModel().getValue() != null) {
            java.util.Date date = (java.util.Date) dataExamen.getModel().getValue();
            dataE = date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
        }

        // Crearea obiectului NotaNumerica
        try {
            NotaNumerica notaNumerica = new NotaNumerica(TipNota.N, butonStudent.getText(), Integer.parseInt(butonDisciplina.getText()), dataE, notaFinala, promovat);
            // Adăugare logică pentru stocarea sau salvarea notei
            System.out.println("Nota adăugată cu succes!");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Eroare la adăugarea notei!");
        }
    }
}
