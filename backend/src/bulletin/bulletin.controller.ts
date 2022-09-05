import { NextFunction, Request, Response } from "express";
import BulletinService from "./bulletin.service";

export class BulletinController{
    
    public static async getbulletin(req: Request,res: Response, next: NextFunction){
        const data = await BulletinService.getbulletin(req.params.id);
        console.log(data);
        res.status(200).json(data);
    }
}