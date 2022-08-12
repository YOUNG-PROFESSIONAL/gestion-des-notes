"use strict";
/*
 * Copyright (c) 2022.
 * Author Peter Placzek (tada5hi)
 * For the full copyright and license information,
 * view the LICENSE file that was distributed with this source code.
 */
Object.defineProperty(exports, "__esModule", { value: true });
exports.buildQueryRelations = exports.buildQueryRelationsForMany = void 0;
const utils_1 = require("../utils");
const utils_2 = require("../../utils");
function buildQueryRelationsForMany(input) {
    let data;
    for (let i = 0; i < input.length; i++) {
        if (data) {
            data = (0, utils_2.mergeDeep)(data, input[i]);
        }
        else {
            data = input[i];
        }
    }
    return buildQueryRelations(data);
}
exports.buildQueryRelationsForMany = buildQueryRelationsForMany;
function buildQueryRelations(data) {
    const properties = (0, utils_1.flattenNestedProperties)(data);
    const keys = Object.keys(properties);
    return Array.from(new Set(keys));
}
exports.buildQueryRelations = buildQueryRelations;
//# sourceMappingURL=build.js.map