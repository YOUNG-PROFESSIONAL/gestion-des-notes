import { Application } from 'express';
import { ClassementController } from './classement.controller';

class RoutesClassement{
    public routes( app: Application) :void{
        app.route('/classement/:niv')
        .get(ClassementController.getClassement);
        app.route('/histogramme')
        .get(ClassementController.getStudents);
    }
}
export default  RoutesClassement;