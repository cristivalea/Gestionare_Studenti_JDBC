package model;

import java.util.ArrayList;

public class CadruDidactic {
    private String numeFamilie;
    private String prenume;
    private String initialaTata;
    private String cnp;
    private String contBancar;
    private ArrayList<String> disciplinePredate = new ArrayList<String>();
    private GradDidactic gradDidactic;

    public CadruDidactic(String numeFamilie, String prenume, String initialaTata, String cnp, String contBancar, ArrayList<String> disciplinePredate, GradDidactic gradDidactic) {
        this.numeFamilie = numeFamilie;
        this.prenume = prenume;
        this.initialaTata = initialaTata;
        this.cnp = cnp;
        this.contBancar = contBancar;
        this.disciplinePredate = disciplinePredate;
        this.gradDidactic = gradDidactic;
    }

    public String getNumeFamilie() {
        return numeFamilie;
    }

    public String getPrenume() {
        return prenume;
    }

    public String getInitialaTata() {
        return initialaTata;
    }

    public String getCnp() {
        return cnp;
    }

    public String getContBancar() {
        return contBancar;
    }

    public ArrayList<String> getDisciplinePredate() {
        return disciplinePredate;
    }

    public GradDidactic getGradDidactic() {
        return gradDidactic;
    }

    public void setNumeFamilie(String numeFamilie) {
        this.numeFamilie = numeFamilie;
    }

    public void setContBancar(String contBancar) {
        this.contBancar = contBancar;
    }

    public void setGradDidactic(GradDidactic gradDidactic) {
        this.gradDidactic = gradDidactic;
    }

    public void setDisciplinePredate(ArrayList<String> disciplinePredate) {
        this.disciplinePredate = disciplinePredate;
    }
}
