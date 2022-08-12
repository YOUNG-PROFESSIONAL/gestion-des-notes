import { DataSource } from 'typeorm';
import { SeederFactoryConfig, SeederFactoryManager } from './factory';
export interface Seeder {
    run(dataSource: DataSource, factoryManager: SeederFactoryManager): Promise<void>;
}
export declare type SeederConstructor = new () => Seeder;
export declare type SeederOptions = {
    seeds?: SeederConstructor[] | string[];
    seedName?: string;
    factories?: SeederFactoryConfig[] | string[];
    factoriesLoad?: boolean;
};
