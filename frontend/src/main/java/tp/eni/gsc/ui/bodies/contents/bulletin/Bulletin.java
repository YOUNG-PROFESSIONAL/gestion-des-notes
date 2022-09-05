package main.java.tp.eni.gsc.ui.bodies.contents.bulletin;

import main.java.tp.eni.gsc.ui.bodies.contents.etudiant.Etudiant;
import main.java.tp.eni.gsc.ui.bodies.contents.notes.Notes;

import java.util.List;

public class Bulletin{
    private Etudiant bStudent;
    private List<Notes> bNotes;
    private Double bNotePonderee;
    private Double bAverage;
    private Integer bCoefficient;
    private String bObservation;

    public  Bulletin(){}

    public Bulletin(Etudiant bStudent, List<Notes> bNotes, Double bNotePonderee, Double bAverage, Integer bCoefficient, String bObservation) {
        this.bStudent = bStudent;
        this.bNotes = bNotes;
        this.bNotePonderee = bNotePonderee;
        this.bAverage = bAverage;
        this.bCoefficient = bCoefficient;
        this.bObservation = bObservation;
    }

    public Etudiant getbStudent() {
        return bStudent;
    }

    public void setbStudent(Etudiant bStudent) {
        this.bStudent = bStudent;
    }

    public List<Notes> getbNotes() {
        return bNotes;
    }

    public void setbNotes(List<Notes> bNotes) {
        this.bNotes = bNotes;
    }

    public Double getbNotePonderee() {
        return bNotePonderee;
    }

    public void setbNotePonderee(Double bNotePonderee) {
        this.bNotePonderee = bNotePonderee;
    }

    public Double getbAverage() {
        return bAverage;
    }

    public void setbAverage(Double bAverage) {
        this.bAverage = bAverage;
    }

    public Integer getbCoefficient() {
        return bCoefficient;
    }

    public void setbCoefficient(Integer bCoefficient) {
        this.bCoefficient = bCoefficient;
    }

    public String getbObservation() {
        if(moyenne() >= 10.0) this.bObservation = "ADMIS";
        else  if(moyenne()< 10.0 && moyenne() >= 7.5) this.bObservation = "REDOUBLANT";
        else this.bObservation ="EXCLU";
        return bObservation;
    }

    public void setbObservation(String bObservation) {
        if(moyenne() >= 10.0) this.bObservation = "ADMIS";
        else  if(moyenne()< 10.0 && moyenne() >= 7.5) this.bObservation = "REDOUBLANT";
        else this.bObservation ="EXCLU";
    }
    public Double moyenne(){
        Double moyens = 0.0;
        Integer coef = 0;
        for(Notes note : this.bNotes) {
            moyens = moyens + note.getNote()*note.getNoteMatiere().getMatiereCoeff();
            coef = coef + note.getNoteMatiere().getMatiereCoeff();
        }
        return moyens/coef;
    }
}
