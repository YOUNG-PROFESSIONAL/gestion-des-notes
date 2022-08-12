import { FiltersParseOptions, FiltersParseOutput } from '@trapi/query';
export declare type FiltersApplyOptions = FiltersParseOptions & {
    transform?: FiltersTransformOptions;
};
export declare type FiltersApplyOutput = FiltersParseOutput;
export { FiltersParseOptions, FiltersParseOutput, };
export declare type FiltersTransformOptions = {
    bindingKeyFn?: (key: string) => string;
};
export declare type FilterTransformOutputElement = {
    statement: string;
    binding: Record<string, any>;
};
export declare type FiltersTransformOutput = FilterTransformOutputElement[];
