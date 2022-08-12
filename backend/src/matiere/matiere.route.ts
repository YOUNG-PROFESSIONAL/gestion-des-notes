import { MatiereController } from './matiere.controller';
import { Application } from 'express';
export class RoutesMatiere {
    public routes(app: Application): void{
        app.route("/matiere")
        .get(MatiereController.getAllMatieres)
        .post(MatiereController.addMatiere)
        .put(MatiereController.updateMatiere);

        app.route("/matiere/:id")
        .get(MatiereController.getMatiere)
        .delete(MatiereController.suppMatiere);
    }
}