import { LEVEL } from './level.enum';
import { Request,Response,NextFunction } from 'express';
import StudentService from './student.service';
import NotesService from '../../notes/notes.service';

class StudentController{
    public static async getAllStudents(req: Request, res: Response, next: NextFunction){;
        let niv:LEVEL =  JSON.parse(req.params.niv);
        let mc:string = "";
        if(req.params.mc != undefined) mc = req.params.mc;
            const data = await StudentService.getListStudents(mc,niv);
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
        let obj:string = JSON.parse(req.params.id);
        const data = await StudentService.deleteStudent(obj);
        res.status(200).json(data);
    }
}

export default  StudentController;