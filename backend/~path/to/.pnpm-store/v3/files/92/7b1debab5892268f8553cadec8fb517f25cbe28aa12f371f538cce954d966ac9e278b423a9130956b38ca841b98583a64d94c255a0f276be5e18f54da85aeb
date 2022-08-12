import { EntitySchema, ObjectType } from 'typeorm';
import { SeederFactoryManager } from './manager';
import { FactoryCallback, SeederFactoryConfig } from './type';
export declare function useSeederFactoryManager(): SeederFactoryManager;
export declare function setSeederFactory<O extends Record<string, any>>(entity: ObjectType<O> | EntitySchema<O>, factoryFn: FactoryCallback<O>): SeederFactoryConfig;
export declare function useSeederFactory<O extends Record<string, any>>(entity: ObjectType<O> | EntitySchema<O>): import("./module").SeederFactory<O>;
