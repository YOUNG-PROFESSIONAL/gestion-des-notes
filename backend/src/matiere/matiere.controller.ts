import MatiereService from './matiere.service';
import { NextFunction, Request, Response } from "express";

export class MatiereController{
    public static async getAllMatieres(req: Request, res: Response, next: NextFunction){
        const data = await MatiereService.getListMatieres();
        res.status(200).json(data);
}
public static async getMatiere(req: Request, res: Response, next: NextFunction){
    const data = await MatiereService.getMatiere(req.params.id);
    res.status(200).json(data);
}
public static async addMatiere(req: Request, res: Response, next: NextFunction){
        const data = await MatiereService.addMatiere(req.body);
        res.status(200).json(data);
}
public static async updateMatiere(req: Request, res: Response, next: NextFunction){
    const data = await MatiereService.updateMatiere(req.body);
    res.status(200).json(data);
}
public static async suppMatiere(req: Request, res: Response, next: NextFunction){
    const data = await MatiereService.suppMatiere(req.params.id);
    res.status(200).json(data);
}
}