import { defineStore } from "pinia";
import { ref } from "vue";


const usePayStore = defineStore("pay", () => {

    const tid = ref<String>("null")
   
    
    function setTid(getTid: String) {
        tid.value = getTid
    }
    
    return {
        tid,
        setTid
    }
}, { persist: true });


export {usePayStore}