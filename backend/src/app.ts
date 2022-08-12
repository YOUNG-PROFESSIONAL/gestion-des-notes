import { RoutesNotes } from './notes/notes.route';
import { RoutesMatiere } from './matiere/matiere.route';
import express,{ Application, NextFunction,Request,Response } from "express";
import { dataSource } from "./config/dbconnect";
import RoutesStudent from "./person/student/student.route";
import helmet from 'helmet';
import RoutesBulletin from './bulletin/bulletin.route';
import RoutesClassement from './classement/classement.route';

class App {
    public app: Application;
    public studentRoutes: RoutesStudent = new RoutesStudent();
    public matiereRoutes: RoutesMatiere = new RoutesMatiere();
    public notesRoutes: RoutesNotes = new RoutesNotes();
    public bulletinRoutes: RoutesBulletin = new RoutesBulletin();
    public classementRoutes: RoutesClassement = new RoutesClassement();

    constructor(){
        this.app = express();
        this.config();
        //ROUTES CONFIGURATION
        this.studentRoutes.routes(this.app); 
        this.matiereRoutes.routes(this.app); 
        this.notesRoutes.routes(this.app);
        this.bulletinRoutes.routes(this.app);
        this.classementRoutes.routes(this.app);
    }
    private config(): void{
         // establish database connection
         dataSource
         .initialize()
         .then(() => {
             console.log("Data Source has been initialized!")
         }).catch((err:any) => {
             console.error("Error during Data Source initialization:", err)
         });
 
        this.app.use((req: Request, res: Response, next: NextFunction) => {
            res.header('Access-Control-Allow-Origin','*');
            res.header('Access-Control-Allow-Methodes','GET,POST,DELETE,OPTIONS,PUT');
            res.header('Access-Control-Allow-Header','*');
            next();
        });
        this.app.use(express.json(),helmet());
    }
}
export default new App().app;