"use strict";
/*
 * Copyright (c) 2022.
 * Author Peter Placzek (tada5hi)
 * For the full copyright and license information,
 * view the LICENSE file that was distributed with this source code.
 */
Object.defineProperty(exports, "__esModule", { value: true });
exports.isLocatorInfo = exports.buildLocatorOptions = void 0;
const utils_1 = require("../utils");
function buildLocatorOptions(options) {
    options = options || {};
    options.path = options.path || [];
    options.path = (0, utils_1.toArray)(options.path);
    if (options.path.length === 0) {
        options.path.push(process.cwd());
    }
    return options;
}
exports.buildLocatorOptions = buildLocatorOptions;
/* istanbul ignore next */
function isLocatorInfo(data) {
    if (typeof data !== 'object') {
        return false;
    }
    if (!(0, utils_1.hasOwnProperty)(data, 'path') ||
        typeof data.path !== 'string') {
        return false;
    }
    if (!(0, utils_1.hasOwnProperty)(data, 'fileName') ||
        typeof data.fileName !== 'string') {
        return false;
    }
    return !(!(0, utils_1.hasOwnProperty)(data, 'fileExtension') ||
        typeof data.fileExtension !== 'string');
}
exports.isLocatorInfo = isLocatorInfo;
//# sourceMappingURL=utils.js.map