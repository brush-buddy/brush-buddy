import { defineStore } from "pinia";
import { ref } from "vue";

const useUserStore = defineStore("user", () => {
    const accessToken = ref('');
    function setAccessToken(newToken:string){
        accessToken.value = newToken
    }

    return {
        accessToken,
        setAccessToken
    }
})


export {useUserStore}