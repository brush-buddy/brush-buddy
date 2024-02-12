import type { AxiosResponse } from "axios";
import { localAxios } from "./axios";

interface DraftPipoInfo { 
    pipoUrl? : String,
    pipoPalette?: JSON,
    isAI?: Boolean,
    prompt?: String
}

interface DraftThumbnail { 
    draftBookmark?: number,
    draftDownload?: number,
    draftId?: number,
    draftThumbnail?: string
}
export type {DraftPipoInfo, DraftThumbnail}