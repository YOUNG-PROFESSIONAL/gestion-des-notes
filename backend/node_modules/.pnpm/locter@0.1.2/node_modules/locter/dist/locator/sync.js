"use strict";
/*
 * Copyright (c) 2022.
 * Author Peter Placzek (tada5hi)
 * For the full copyright and license information,
 * view the LICENSE file that was distributed with this source code.
 */
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.locateFileSync = exports.locateFilesSync = void 0;
const glob_1 = require("glob");
const path_1 = __importDefault(require("path"));
const utils_1 = require("./utils");
function locateFilesSync(pattern, options) {
    options = (0, utils_1.buildLocatorOptions)(options);
    const patterns = Array.isArray(pattern) ?
        pattern :
        [pattern];
    const items = [];
    for (let i = 0; i < patterns.length; i++) {
        for (let j = 0; j < options.path.length; j++) {
            const files = (0, glob_1.sync)(patterns[i], {
                absolute: true,
                cwd: options.path[j],
                nodir: true,
            });
            for (let k = 0; k < files.length; k++) {
                const fileInfo = path_1.default.parse(files[k]);
                items.push({
                    path: fileInfo.dir.split('/').join(path_1.default.sep),
                    fileName: fileInfo.name,
                    fileExtension: fileInfo.ext,
                });
            }
        }
    }
    return items;
}
exports.locateFilesSync = locateFilesSync;
function locateFileSync(pattern, options) {
    options = (0, utils_1.buildLocatorOptions)(options);
    const patterns = Array.isArray(pattern) ?
        pattern :
        [pattern];
    for (let i = 0; i < patterns.length; i++) {
        for (let j = 0; j < options.path.length; j++) {
            const files = (0, glob_1.sync)(patterns[i], {
                absolute: true,
                cwd: options.path[j],
                nodir: true,
            });
            const element = files.shift();
            if (element) {
                const fileInfo = path_1.default.parse(element);
                return {
                    path: fileInfo.dir.split('/').join(path_1.default.sep),
                    fileName: fileInfo.name,
                    fileExtension: fileInfo.ext,
                };
            }
        }
    }
    return undefined;
}
exports.locateFileSync = locateFileSync;
//# sourceMappingURL=sync.js.map