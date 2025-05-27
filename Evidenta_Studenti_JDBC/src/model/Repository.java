package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import XMLProcess.ProcesareFisaDisciplinei;
import view.Observer;
public class Repository {
    private static ArrayList<Disciplina> discipline  = new ArrayList<Disciplina>();
    private static ArrayList<Student> studenti = new ArrayList<Student>();
    private static ArrayList<Nota> note = new ArrayList<Nota>();
    private static ArrayList<Observer> observers = new ArrayList<Observer>();
    private static Repository instance=null;

    private Repository() throws Exception {
        discipline = loadDiscipline();
        note = loadNote();
        for (Disciplina d : discipline) {
            for (Nota n : note) {
                if (n.getCod_disciplina() == d.getCodDisciplina()) {
                    n.setD(d);
                    n.setNotaFinala();
                    break;
                }
            }
        }
        studenti = loadStudenti();
        for (Student s : studenti) {
            for (Nota n : note) {
                if (s.getNrMatricol().equals(n.numar_matricol)) {
                    s.getNote().add(n);
                }
            }
        }
//        Map<String, Student> mapStudenti = new HashMap<>();
//        for (Student s : studenti) {
//            mapStudenti.put(s.getNrMatricol(), s);
//        }
//        for (Nota n : note) {
//            Student s = mapStudenti.get(n.getNumar_matricol());
//            if (s != null) {
//                n.setStudent(s);
//            }
//        }
        //incarca fisa disciplinei pentru fiecare disciplina in parte
//        Optional<ArrayList<FisaDisciplinei>> box = ProcesareFisaDisciplinei.getFiseDiscipline();
//        ArrayList<FisaDisciplinei> listaFiseDiscipline = new ArrayList<FisaDisciplinei>();
//        if(box.isPresent() == false){
//            System.err.println("Nu s-au incarcat disciplinele");
//        }
//        else{
//            listaFiseDiscipline = box.get();
//        }
//
//        for(Disciplina d : discipline){
//            for(FisaDisciplinei f : listaFiseDiscipline){
//                if(f.getDenumireDisciplina().equals(d.getNumedisciplina())){
//                    d.setFisaDisciplinei(f);
//                }
//            }
//        }//end for
    }

    public static Repository getInstance() throws Exception {
        if(instance == null)
            instance=new Repository();
        return instance;
    }

//    private ArrayList<Disciplina> loadDiscipline(){
//        return Disciplina.getDiscipline();
//    }

    private ArrayList<Disciplina> loadDiscipline(){
        ArrayList<Disciplina> disciplineLoaded = Disciplina.getDiscipline();
        System.out.println("Discipline incarcate: " + disciplineLoaded.size());
        return disciplineLoaded;
    }


    private ArrayList<Nota> loadNote(){
        ArrayList<Nota> note_aux = new ArrayList<Nota>();
        for(int i = 0; i < NotaNumerica.getNote().size(); i++){
            note_aux.add(NotaNumerica.getNote().get(i));
        }
        for(int i = 0; i < NotaCalificativAR.getNoteCalificativ().size(); i++){
            note_aux.add(NotaCalificativAR.getNoteCalificativ().get(i));
        }
        for(int i = 0; i < NotaCalificativ.getNoteCalificativ().size(); i++){
            note_aux.add(NotaCalificativ.getNoteCalificativ().get(i));
        }
        return note_aux;
    }

    public ArrayList<Student> loadStudenti() throws Exception{
        return Student.getStudenti();
    }

    public static ArrayList<Disciplina> getDiscipline() {
        return discipline;
    }

    public static ArrayList<Student> getStiudenti() {
        return studenti;
    }

    public static ArrayList<Nota> getNote() {
        return note;
    }

    public static void adaugareDisciplina(Disciplina d){
        d.adaugaDisciplina();
        discipline.add(d);
        notifyObservers();
    }

    public static void adugareStudent(Student s){
        s.adaugareStudent();
        studenti.add(s);
        notifyObservers();
    }

    public static void adaugaObservatori(Observer obs){
        observers.add(obs);
    }

    public static void notifyObservers(){
        for(Observer o : observers){
            o.update();
        }
    }

    public static Student cautareStudentNume(String nume){
        Student student = null;
        for(Student s : studenti){
            if(s.getNumeFamilie().equals(nume)){
                student = s;
                break;
            }
        }
        return student;
    }

    public void stergeStudent(Student student) {
        return;
    }
    public static void updateDataNastere(LocalDate dataNoua, String numarMatricol){
        for(Student s : getStiudenti()){
            if(s.getNrMatricol().equals(numarMatricol)){
                s.setDataNAstere(dataNoua);
            }
        }
    }// end update data nastere

    public static void updateNumefamilie(String numeNou, String numarMatricol){
        for(Student s : getStiudenti()){
            if(s.getNrMatricol().equals(numarMatricol)){
                s.setNumeFamilie(numeNou);
            }
        }
    }// end update nume de familie

    public static void updatePrenume(String[] prenumeNoi, String numarMatricol){
        for(Student s : getStiudenti()){
            if(s.getNrMatricol().equals(numarMatricol)){
                s.setPrenume(prenumeNoi);
            }
        }
    }

    public static void adaugareNotaNumerica(NotaNumerica n, String nrMatricol, int codDsicipplina){
        for(Student s : getStiudenti()){
            for (Disciplina d : getDiscipline()){
                System.out.println(s);
                System.out.println(d);
                if(s.getNrMatricol().equals(nrMatricol) && (d.getCodDisciplina() == codDsicipplina)){
                    System.out.println(n + " " + nrMatricol + " " + codDsicipplina);
                    n.setNotaFinala();
                    note.add(n);
                    n.adaugaNota();
                    notifyObservers();
                    break;
                }
            }
        }
    }// end adaugare nota numerica

    public static void adaugareNotaCalificativAr(NotaCalificativAR nar, String nrMAtricol, int codDisciplina){
        for(Student s : getStiudenti()){
            for(Disciplina d : getDiscipline()){
//                System.out.println(s);
//                System.out.println(d);
                if(s.getNrMatricol().equals(nrMAtricol) && d.getCodDisciplina() == codDisciplina){
                    System.out.println(nar + " " + nrMAtricol + " " + codDisciplina);
                    nar.setNotaFinala();
                    note.add(nar);
                    nar.adaugaNota();
                    notifyObservers();
                    break;
                }
            }
        }
    } // adaugare nota calificativ admis/respins

    public static void adaugareNotaCalificativ(NotaCalificativ nc, String nrMatricol, int codDisciplina){
        for(Student s : getStiudenti()){
            for(Disciplina d : getDiscipline()){
                if(s.getNrMatricol().equals(nrMatricol) && d.getCodDisciplina() == codDisciplina){
                    nc.setNotaFinala();
                    note.add(nc);
                    nc.adaugaNota();
                    notifyObservers();
                }
            }
        }
    } // end adaugare nota calificativ


} // end Repository
