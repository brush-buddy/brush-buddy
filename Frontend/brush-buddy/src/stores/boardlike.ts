import { defineStore } from "pinia";
import { ref } from "vue";
import { localAxios } from "../api/axios";

const useLikeStore = defineStore("like", () => {
    const isLike = ref(false)
    
    function setLikeState(likeState : boolean) {
        isLike.value = likeState
    }

    function addLikeState(boardId : number) {
        localAxios().post(`board/${boardId}/heart`)
        .then(() => { 
            isLike.value = true
        })

    }

    function removeLikeState(boardId : number) {
        localAxios().delete(`board/${boardId}/heart`)
        .then(() => { 
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