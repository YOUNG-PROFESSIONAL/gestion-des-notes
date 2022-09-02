import { Notes } from './notes.model';
import { LEVEL } from './../person/student/level.enum';
import { NextFunction, Request, Response } from "express";
import NotesService from "./notes.service";

export class NotesController{
    public static async getAllNotes(req: Request, res: Response, next: NextFunction){
        let obj:LEVEL = JSON.parse(req.params.niv);
        const data = await NotesService.getListNotes(obj,req.params.matId);
        res.status(200).json(data);
}
public static async getNotes(req: Request, res: Response, next: NextFunction){
    const data = await NotesService.getNotes(req.params.id);
    res.status(200).json(data);
}

public static async updateNotes(req: Request, res: Response, next: NextFunction){
    console.log(req.body);
    
    const data = await NotesService.updateNotes(req.body);
    res.status(200).json(data);
}
public static async suppNotes(req: Request, res: Response, next: NextFunction){
    const data = await NotesService.suppNotes(req.params.id);
    res.status(200).json(data);
}
}