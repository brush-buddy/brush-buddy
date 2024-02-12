import { defineStore } from "pinia";
import { ref } from "vue";
import { localAxios } from "../api/axios";
import type { DraftPipoInfo } from "../api/draft";

const useLikeStore = defineStore("like", () => {
    const isLike = ref(false)
    
    function setLikeState(boardId : number, ) {
        localAxios().get(`board/${boardId}/heart`)
        .then((response) => { 
            isLike.value = response.data
        })
    }

    function addLikeState(boardId : number) {
        localAxios().post(`board/${boardId}/heart`)
        .then((response) => { 
            isLike.value = true
        })

    }

    function removeLikeState(boardId : number) {
        localAxios().delete(`board/${boardId}/heart`)
        .then((response) => { 
            isLike.value = false
        })
    }
    
    return {
        isLike,
        setLikeState,
        addLikeState,
        removeLikeState
    }
})


export {useLikeStore}