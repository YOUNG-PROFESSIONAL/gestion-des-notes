package main.java.tp.eni.gsc.ui.bodies.contents.matieres;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import main.java.tp.eni.gsc.config.HttpConnection;
import main.java.tp.eni.gsc.config.Message;
import main.java.tp.eni.gsc.ui.bodies.contents.etudiant.Etudiant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MatieresService {
    private final HttpConnection httpConnection = new HttpConnection();

    public List<Matiere > getAllMatieres(String key, Integer niv) throws IOException {
        List<Matiere> list = new ArrayList<Matiere>(){};
        JsonArray matieres = httpConnection.httpGetAll("matieres",niv,key);
        for (JsonElement object: matieres){
            Matiere convertedObject = new Gson().fromJson(String.valueOf(object),Matiere.class);
            list.add(convertedObject);
        }
        return list;
    }
    public Matiere getMatiere(Matiere matiere){
        return null;
    }
    public Matiere addMatiere(Matiere matiere) throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(matiere);
        Message elements = httpConnection.httpPost("matiere",json);
        System.out.println("Message : " + elements.message);
        return null;
    }
    public Etudiant updateMatiere(Matiere matiere) throws IOException     {
        Gson gson = new Gson();
        String json = gson.toJson(matiere);
        Message elements = httpConnection.httpPut("matiere",json);
        System.out.println("Message : " + elements.message);
        return null;
    }
    public boolean removeMatiere(Matiere matiere) throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(matiere.getMatiereId());
        Message elements = httpConnection.httpDelete("matiere" + "/" +json);
        System.out.println("Message : " + json);
        System.out.println("Message : " + elements.message);
        return true;
    }
}
