export declare function hasOwnProperty<X extends Record<string, any>, Y extends PropertyKey>(obj: X, prop: Y): obj is X & Record<Y, unknown>;
/**
 * Build alias mapping from strings in array or object representation to object representation.
 *
 * {field1: 'field1', ...} => {field1: 'field1', ...}
 * ['field1', 'field2'] => {field1: 'field1', field2: 'field2'}
 *
 * @param rawFields
 */
export declare function buildObjectFromStringArray(rawFields: string[] | Record<string, string>): Record<string, string>;
//# sourceMappingURL=object.d.ts.map