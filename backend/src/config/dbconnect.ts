import { Matiere } from './../matiere/matiere.model';
import { DataSource } from "typeorm";
import { Student } from "../person/student/student.model";
import { Notes } from '../notes/notes.model';

export const dataSource = new DataSource({
    type: "mariadb",
    host: "127.0.0.1",
    port: 3306,
    username: "io",
    password: "00001111",
    database: "sco",
    synchronize: true,
    //logging: true,
    entities: [Student,Matiere,Notes],
    subscribers: [],
    migrations: [],
})