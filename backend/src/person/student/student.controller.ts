import { Request,Response,NextFunction } from 'express';
import StudentService from './student.service';

class StudentController{
    public static async getAllStudents(req: Request, res: Response, next: NextFunction){
            const data = await StudentService.getListStudents();
            res.status(200).json(data);
    }
    public static async getStudent(req: Request, res: Response, next: NextFunction){
        const data = await StudentService.getStudent(req.params.id);
        res.status(200).json(data);
    }
    public static async addStudent(req: Request, res: Response, next: NextFunction){
            const data = await StudentService.addStudent(req.body);
            res.status(200).json(data);
    }
    public static async updateStudent(req: Request, res: Response, next: NextFunction){
        const data = await StudentService.updateStudent(req.body);
        res.status(200).json(data);
    }
    public static async deleteStudent(req: Request, res: Response, next: NextFunction){
        const data = await StudentService.deleteStudent(req.params.id);
        res.status(200).json(data);
    }
}

export default  StudentController;