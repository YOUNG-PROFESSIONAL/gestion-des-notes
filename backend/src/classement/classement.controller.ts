import { NextFunction, Request, Response } from "express";
import ClassementService from "./classement.service";

export class ClassementController{
    public static async getStudents(req: Request,res: Response, next: NextFunction) {
        const data = await ClassementService.getStudents(req.body);
        res.status(200).json(data);
    }
    public static async getClassement(req: Request,res: Response, next: NextFunction){
        const data = await ClassementService.getClassement(req.params.niv);
        console.log(data);
        res.status(200).json(data);
    }
}