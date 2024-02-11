import { defineStore } from "pinia";
import { ref } from "vue";
import type { DraftPipoInfo } from "../api/draft";

const useImageStore = defineStore("image", () => {
    const isAI = ref(false)
    const pipoPalette = ref<Object>({})
    const pipoUrl = ref<String>("")
    const prompt = ref<String>("")
    
    
    function setImage(imageIsAI: boolean,
        imagePipoPalette: string,
        imagePipoUrl: String,
        imagePrompt: String) {
        isAI.value = imageIsAI
        pipoPalette.value = JSON.parse(imagePipoPalette)
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