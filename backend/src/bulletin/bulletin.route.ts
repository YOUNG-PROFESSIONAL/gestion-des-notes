import { Application } from 'express';
import { BulletinController } from './bulletin.controller';

class RoutesBulletin{
    public routes( app: Application) :void{
      
        app.route('/bulletin/:id')
        .get(BulletinController.getbulletin);
    }
}
export default  RoutesBulletin;