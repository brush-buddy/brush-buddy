import { defineStore } from "pinia";
import { ref, type Ref } from "vue";

const useUserStore = defineStore("user", () => {
    const accessToken = ref('');
    const connectedMachine : Ref<Number> = ref(-1)
    const isRefreshing : Ref<Boolean> = ref(false);
    const subscribers  :Ref<any[]> = ref([]);

    function setAccessToken(newToken:string){
        accessToken.value = newToken
    }

    function setConnectedMachine(machineId : Number){
        connectedMachine.value = machineId
    }
    function isMachineConnected(){ // 기기연결 여부 확인
        return connectedMachine.value != -1;
    }
    function setRefresh(value : boolean){
        isRefreshing.value = value
    }

    function addSubscriber(callback:any){
        subscribers.value.push(callback)
    }

    function onAccessTokenFetched(accessToken:string) {
        subscribers.value.forEach((callback:any) => callback(accessToken));
        subscribers.value = [];
    }
      

    return {
        accessToken,
        setAccessToken,
        connectedMachine,
        setConnectedMachine,
        isRefreshing,
        setRefresh,
        subscribers,
        addSubscriber,
        onAccessTokenFetched,
        isMachineConnected

    }
  }
)

export { useUserStore }
