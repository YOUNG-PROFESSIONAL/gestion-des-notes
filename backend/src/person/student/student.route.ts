import { Application, Request, Response } from "express";
import StudentController from "./student.controller";

class RoutesStudent{
    public routes(app: Application):void {

        app.route("/students/:niv")
        .get(StudentController.getAllStudents);
        app.route("/students/:niv/:mc")
        .get(StudentController.getAllStudents);

        app.route("/student")
        .post(StudentController.addStudent)
        .put(StudentController.updateStudent);

        app.route("/student/:id")
        .get(StudentController.getStudent)
        .delete(StudentController.deleteStudent);
    }
}

export default RoutesStudent