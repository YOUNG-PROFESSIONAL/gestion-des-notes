package main.java.tp.eni.gsc.ui.bodies.contents.dashboard;

import main.java.tp.eni.gsc.ui.bodies.contents.etudiant.Etudiant;
import main.java.tp.eni.gsc.ui.bodies.contents.notes.Notes;

import java.util.List;

public class DashBoard {
    private Etudiant bStudent;
    private List<Notes> bNotes;
    private Double bAverage;
    private Integer bCoefficient;
    private String bObservation;

    public DashBoard() {
    }

    public DashBoard(Etudiant bStudent, List<Notes> bNotes, Double bAverage, Integer bCoefficient) {
        this.bStudent = bStudent;
        this.bNotes = bNotes;
        this.bAverage = bAverage;
        this.bCoefficient = bCoefficient;
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

    public Double getbAverage() {
        return this.bAverage;
    }

    public void setbAverage(double bAverage) {

        this.bAverage = bAverage ;
    }

    public Integer getbCoefficient() {
        return bCoefficient;
    }

    public void setbCoefficient(Integer bCoefficient) {
        this.bCoefficient = bCoefficient;
    }
    public String getbObservation() {
        if(getbAverage() >= 10.0) this.bObservation = "ADMIS";
        else  if(getbAverage()< 10.0 && getbAverage() >= 7.5) this.bObservation = "REDOUBLANT";
        else this.bObservation ="EXCLU";
        return bObservation;
    }

    public void setbObservation(String bObservation) {
        if(getbAverage() >= 10.0) this.bObservation = "ADMIS";
        else  if(getbAverage()< 10.0 && getbAverage() >= 7.5) this.bObservation = "REDOUBLANT";
        else this.bObservation ="EXCLU";
    }
}
