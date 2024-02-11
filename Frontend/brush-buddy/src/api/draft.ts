import type { AxiosResponse } from "axios";
import { localAxios } from "./axios";

interface DraftPipoInfo { 
    pipoUrl? : String,
    pipoPalette?: JSON,
    isAI?: Boolean,
    prompt?: String
}
export type {DraftPipoInfo}