"use strict";
/*
 * Copyright (c) 2022.
 * Author Peter Placzek (tada5hi)
 * For the full copyright and license information,
 * view the LICENSE file that was distributed with this source code.
 */
Object.defineProperty(exports, "__esModule", { value: true });
exports.getExportItem = void 0;
const utils_1 = require("../../../utils");
function getExportItem(data, filterFn) {
    if (filterFn) {
        const keys = Object.keys(data);
        for (let i = 0; i < keys.length; i++) {
            if (filterFn(keys[i], data[keys[i]])) {
                return {
                    key: keys[i],
                    value: data[keys[i]],
                };
            }
        }
    }
    else {
        return {
            key: 'default',
            value: (0, utils_1.hasOwnProperty)(data, 'default') ? data.default : data,
        };
    }
    /* istanbul ignore next */
    return undefined;
}
exports.getExportItem = getExportItem;
//# sourceMappingURL=utils.js.map