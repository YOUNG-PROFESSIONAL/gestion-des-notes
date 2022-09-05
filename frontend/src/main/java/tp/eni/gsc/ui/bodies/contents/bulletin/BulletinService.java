package main.java.tp.eni.gsc.ui.bodies.contents.bulletin;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import main.java.tp.eni.gsc.config.HttpConnection;
import main.java.tp.eni.gsc.config.Message;
import main.java.tp.eni.gsc.ui.bodies.contents.notes.Notes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BulletinService {
    private final HttpConnection httpConnection = new HttpConnection();

    public List<Bulletin> getBulletin(Integer niv, String matiereId) throws IOException {
        List<Bulletin> list = new ArrayList<>(){};
        JsonArray b = httpConnection.httpGetAll("bulletin",niv,matiereId);
        for (JsonElement object: b){
            Bulletin convObject = new Gson().fromJson(String.valueOf(object),Bulletin.class);
            list.add(convObject);
        }
        return list;
    }
}
