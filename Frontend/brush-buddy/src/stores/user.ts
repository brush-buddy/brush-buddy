import { defineStore } from "pinia";
import { ref, type Ref } from "vue";

const useUserStore = defineStore("user", () => {
    const accessToken = ref('');
    const connectedMachine : Ref<Number> = ref(-1)
    function setAccessToken(newToken:string){
        accessToken.value = newToken
    }

    function setConnectedMachine(machineId : Number){
        connectedMachine.value = machineId
    }

    return {
        accessToken,
        setAccessToken,
        connectedMachine,
        setConnectedMachine
    }
})


export {useUserStore}