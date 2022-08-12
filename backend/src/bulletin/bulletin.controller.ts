import { NextFunction, Request, Response } from "express";
import BulletinService from "./bulletin.service";

export class BulletinController{
    public static async getStudents(req: Request,res: Response, next: NextFunction) {
        const data = await BulletinService.getStudents(req.body);
        res.status(200).json(data);
    }
    public static async getbulletin(req: Request,res: Response, next: NextFunction){
        const data = await BulletinService.getbulletin(req.body);
        res.status(200).json(data);
    }
}