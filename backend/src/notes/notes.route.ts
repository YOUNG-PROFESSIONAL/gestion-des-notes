import { NotesController } from './notes.controller';
import { Application } from "express";

export class RoutesNotes {
    public routes(app: Application): void{
       
        app.route("/notes/:niv/:matId")
        .get(NotesController.getAllNotes)

        app.route("/note")
        .put(NotesController.updateNotes);
    }
}