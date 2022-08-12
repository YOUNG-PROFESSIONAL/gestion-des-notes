"use strict";
/*
 * Copyright (c) 2022.
 * Author Peter Placzek (tada5hi)
 * For the full copyright and license information,
 * view the LICENSE file that was distributed with this source code.
 */
var __createBinding = (this && this.__createBinding) || (Object.create ? (function(o, m, k, k2) {
    if (k2 === undefined) k2 = k;
    var desc = Object.getOwnPropertyDescriptor(m, k);
    if (!desc || ("get" in desc ? !m.__esModule : desc.writable || desc.configurable)) {
      desc = { enumerable: true, get: function() { return m[k]; } };
    }
    Object.defineProperty(o, k2, desc);
}) : (function(o, m, k, k2) {
    if (k2 === undefined) k2 = k;
    o[k2] = m[k];
}));
var __setModuleDefault = (this && this.__setModuleDefault) || (Object.create ? (function(o, v) {
    Object.defineProperty(o, "default", { enumerable: true, value: v });
}) : function(o, v) {
    o["default"] = v;
});
var __importStar = (this && this.__importStar) || function (mod) {
    if (mod && mod.__esModule) return mod;
    var result = {};
    if (mod != null) for (var k in mod) if (k !== "default" && Object.prototype.hasOwnProperty.call(mod, k)) __createBinding(result, mod, k);
    __setModuleDefault(result, mod);
    return result;
};
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.loadScriptFileExport = exports.loadScriptFile = void 0;
const utils_1 = require("./utils");
const locator_1 = require("../../../locator");
const utils_2 = require("../../utils");
function loadScriptFile(data) {
    return __awaiter(this, void 0, void 0, function* () {
        const filePath = (0, locator_1.isLocatorInfo)(data) ?
            (0, utils_2.buildLoaderFilePath)(data) :
            data;
        try {
            return yield Promise.resolve().then(() => __importStar(require(filePath)));
        }
        catch (e) {
            /* istanbul ignore next */
            return undefined;
        }
    });
}
exports.loadScriptFile = loadScriptFile;
function loadScriptFileExport(data, filterFn) {
    return __awaiter(this, void 0, void 0, function* () {
        const filePath = (0, locator_1.isLocatorInfo)(data) ?
            (0, utils_2.buildLoaderFilePath)(data) :
            data;
        try {
            const data = yield loadScriptFile(filePath);
            return (0, utils_1.getExportItem)(data, filterFn);
        }
        catch (e) {
            /* istanbul ignore next */
            return undefined;
        }
    });
}
exports.loadScriptFileExport = loadScriptFileExport;
//# sourceMappingURL=async.js.map