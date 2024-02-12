import { defineStore } from "pinia";
import { ref } from "vue";
import type { DraftPipoInfo } from "../api/draft";

const useImageStore = defineStore("image", () => {
    const isAI = ref(false)
    const pipoPalette = ref<Object>({})
    const pipoUrl = ref<string>("")
    const prompt = ref<String>("")
    const colorUrl = ref<string>("")
    
    function setImage(imageIsAI: boolean,
        imagePipoPalette: string,
        imagePipoUrl: string,
        imageColorUrl: string,
        imagePrompt: String) {
        isAI.value = imageIsAI
        colorUrl.value = imageColorUrl
        pipoPalette.value = JSON.parse(imagePipoPalette)
        pipoUrl.value = imagePipoUrl
        prompt.value = imagePrompt
    }
    
    return {
        isAI,
        pipoPalette,
        pipoUrl,
        prompt,
        colorUrl,
        setImage
    }
})


export {useImageStore}