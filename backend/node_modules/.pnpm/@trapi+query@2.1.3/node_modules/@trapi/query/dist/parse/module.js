"use strict";
/*
 * Copyright (c) 2021-2022.
 * Author Peter Placzek (tada5hi)
 * For the full copyright and license information,
 * view the LICENSE file that was distributed with this source code.
 */
Object.defineProperty(exports, "__esModule", { value: true });
exports.parseQuery = void 0;
const constants_1 = require("../constants");
const parameter_1 = require("./parameter");
function parseQuery(input, options) {
    var _a, _b, _c, _d, _e;
    options !== null && options !== void 0 ? options : (options = {});
    const output = {};
    const nonEnabled = Object.keys(options).length === 0;
    let relations;
    if (!!options[constants_1.Parameter.RELATIONS] || nonEnabled) {
        relations = (0, parameter_1.parseQueryParameter)(constants_1.Parameter.RELATIONS, (_a = input[constants_1.Parameter.RELATIONS]) !== null && _a !== void 0 ? _a : input[constants_1.URLParameter.RELATIONS], options[constants_1.Parameter.RELATIONS]);
        output[constants_1.Parameter.RELATIONS] = relations;
    }
    const keys = [
        constants_1.Parameter.FIELDS,
        constants_1.Parameter.FILTERS,
        constants_1.Parameter.PAGINATION,
        constants_1.Parameter.SORT,
    ];
    for (let i = 0; i < keys.length; i++) {
        const enabled = !!options[keys[i]] ||
            nonEnabled;
        if (!enabled)
            continue;
        const key = keys[i];
        switch (key) {
            case constants_1.Parameter.FIELDS:
                output[constants_1.Parameter.FIELDS] = (0, parameter_1.parseQueryParameter)(keys[i], (_b = input[constants_1.Parameter.FIELDS]) !== null && _b !== void 0 ? _b : input[constants_1.URLParameter.FIELDS], options[constants_1.Parameter.FIELDS], relations);
                break;
            case constants_1.Parameter.FILTERS:
                output[constants_1.Parameter.FILTERS] = (0, parameter_1.parseQueryParameter)(keys[i], (_c = input[constants_1.Parameter.FILTERS]) !== null && _c !== void 0 ? _c : input[constants_1.URLParameter.FILTERS], options[constants_1.Parameter.FILTERS], relations);
                break;
            case constants_1.Parameter.PAGINATION:
                output[constants_1.Parameter.PAGINATION] = (0, parameter_1.parseQueryParameter)(keys[i], (_d = input[constants_1.Parameter.PAGINATION]) !== null && _d !== void 0 ? _d : input[constants_1.URLParameter.PAGINATION], options[constants_1.Parameter.PAGINATION], relations);
                break;
            case constants_1.Parameter.SORT:
                output[constants_1.Parameter.SORT] = (0, parameter_1.parseQueryParameter)(keys[i], (_e = input[constants_1.Parameter.SORT]) !== null && _e !== void 0 ? _e : input[constants_1.URLParameter.SORT], options[constants_1.Parameter.SORT], relations);
                break;
        }
    }
    return output;
}
exports.parseQuery = parseQuery;
//# sourceMappingURL=module.js.map