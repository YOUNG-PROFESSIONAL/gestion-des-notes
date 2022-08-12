"use strict";
/*
 * Copyright (c) 2022.
 * Author Peter Placzek (tada5hi)
 * For the full copyright and license information,
 * view the LICENSE file that was distributed with this source code.
 */
Object.defineProperty(exports, "__esModule", { value: true });
exports.buildQueryPaginationForMany = void 0;
const utils_1 = require("../../utils");
function buildQueryPaginationForMany(inputs) {
    const inputSources = Array.isArray(inputs) ? inputs : [inputs];
    let data;
    for (let i = 0; i < inputSources.length; i++) {
        if (data) {
            data = (0, utils_1.mergeDeep)(data, inputSources[i]);
        }
        else {
            data = inputSources[i];
        }
    }
    return data;
}
exports.buildQueryPaginationForMany = buildQueryPaginationForMany;
//# sourceMappingURL=build.js.map