import { defineStore } from "pinia";
import { ref } from "vue";
import type { DraftPipoInfo } from "../api/draft";

const useImageStore = defineStore("image", () => {
    const isAI = ref(false)
    const pipoPalette = ref("{}")
    const pipoUrl = ref("")
    const prompt = ref("")
    function setImage(imageIsAI: boolean,
        imagePipoPalette: Record<string, string>,
        imagePipoUrl: string,
        imagePrompt: string) {
        isAI.value = imageIsAI
        pipoPalette.value = imagePipoPalette
        pipoUrl.value = imagePipoUrl
        prompt.value = imagePrompt

    }
    
    return {
        isAI,
        pipoPalette,
        pipoUrl,
        prompt,
        setImage
    }
})


export {useImageStore}