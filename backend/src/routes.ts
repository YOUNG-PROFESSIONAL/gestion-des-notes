import { Application} from 'express';
import StudentController from './person/student/student.controller'
import RouteStudent from './person/student/student.route';
class Routes {
    public routeStudent: RouteStudent = new RouteStudent();

    public routes(app: Application):void {
        //STUDENT ROUTES
        this.routeStudent.routes(app);
    }
}
export default Routes;