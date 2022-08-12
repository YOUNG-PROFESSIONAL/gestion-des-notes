import { NextFunction, Request, Response } from "express";
import NotesService from "./notes.service";

export class NotesController{
    public static async getAllNotes(req: Request, res: Response, next: NextFunction){
        const data = await NotesService.getListNotes(req.body);
        res.status(200).json(data);
}
public static async getNotes(req: Request, res: Response, next: NextFunction){
    const data = await NotesService.getNotes(req.params.id);
    res.status(200).json(data);
}
public static async addNotes(req: Request, res: Response, next: NextFunction){
        const data = await NotesService.addNotes(req.body);
        res.status(200).json(data);
}
public static async updateNotes(req: Request, res: Response, next: NextFunction){
    const data = await NotesService.updateNotes(req.body);
    res.status(200).json(data);
}
public static async suppNotes(req: Request, res: Response, next: NextFunction){
    const data = await NotesService.suppNotes(req.params.id);
    res.status(200).json(data);
}
}