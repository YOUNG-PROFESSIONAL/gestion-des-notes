package main.java.tp.eni.gsc.ui.bodies.contents.etudiant;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import main.java.tp.eni.gsc.config.HttpConnection;
import main.java.tp.eni.gsc.config.Message;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EtudiantService {

    private final HttpConnection httpConnection = new HttpConnection();

    public List<Etudiant> getAllStudent(String key, Integer niv) throws IOException {
        List<Etudiant> list = new ArrayList<Etudiant>(){};
        JsonArray etudiants = httpConnection.httpGetAll("students",niv,key);

        for (JsonElement object: etudiants){
            Etudiant convertedObject = new Gson().fromJson(String.valueOf(object),Etudiant.class);
            if(convertedObject.getStudentLevel() == null) convertedObject.setStudentLevel("");
            if(convertedObject.getStudentLevel().contentEquals("0")) convertedObject.setStudentLevel("Select");
            if(convertedObject.getStudentLevel().contentEquals("1")) convertedObject.setStudentLevel("L1");
            if(convertedObject.getStudentLevel().contentEquals("2")) convertedObject.setStudentLevel("L2");
            if(convertedObject.getStudentLevel().contentEquals("3")) convertedObject.setStudentLevel("L3");
            if(convertedObject.getStudentLevel().contentEquals("4")) convertedObject.setStudentLevel("M1");
            if(convertedObject.getStudentLevel().contentEquals("5")) convertedObject.setStudentLevel("M2");
            list.add(convertedObject);
        }
        return list;
    }
    public Etudiant getStudent(Etudiant etudiant){
        return null;
    }
    public Etudiant addStudent(Etudiant etudiant) throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(etudiant);
        Message elements = httpConnection.httpPost("student",json);
        System.out.println("Message : " + elements.message);
        return null;
    }
    public Etudiant updateStudent(Etudiant etudiant) throws IOException     {
        Gson gson = new Gson();
        String json = gson.toJson(etudiant);
        Message elements = httpConnection.httpPut("student",json);
        System.out.println("Message : " + elements.message);
        return null;
    }
    public boolean removeStudent(Etudiant etudiant) throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(etudiant.getPersonId());
        Message elements = httpConnection.httpDelete("student" + "/" +json);
        System.out.println("Message : " + json);
        System.out.println("Message : " + elements.message);
        return true;
    }
}
