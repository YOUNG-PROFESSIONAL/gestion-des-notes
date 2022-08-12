import { Application } from 'express';
import { BulletinController } from './bulletin.controller';

class RoutesBulletin{
    public routes( app: Application) :void{
        app.route('/bulletins')
        .get(BulletinController.getStudents);
        app.route('/bulletin')
        .get(BulletinController.getbulletin);
    }
}
export default  RoutesBulletin;