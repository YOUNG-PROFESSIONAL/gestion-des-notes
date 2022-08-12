import { NotesController } from './notes.controller';
import { Application } from "express";

export class RoutesNotes {
    public routes(app: Application): void{
        app.route("/notes")
        .get(NotesController.getAllNotes)
        .post(NotesController.addNotes)
        .put(NotesController.updateNotes);

        app.route("/notes/:id")
        .get(NotesController.getNotes)
        .delete(NotesController.suppNotes);
    }
}