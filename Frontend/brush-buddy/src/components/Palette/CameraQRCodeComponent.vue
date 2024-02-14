<script setup lang="ts">
import { QrcodeStream } from 'vue-qrcode-reader'
import { useRouter} from 'vue-router';
import { connectMachine } from "../../api/machine"
import { useUserStore } from '../../stores/user'
const router = useRouter();
type QRCode = {
    boundingBox: Object,
    conerPoints : Array<Object>,
    format : String,
    rawValue : String,
}
const emit = defineEmits(["machineConnect"])
const onDetect = async (code :Array<QRCode>) => {
    const userStore = useUserStore();

    try{
        const myCode = code[0].rawValue
        if(myCode.startsWith("brush-buddy Login")){
            const number  = myCode.split(":",2)[1].trim()
            // router.push("/machineLogin/"+ number)
            await connectMachine(parseInt(number))
            .then((data) => {
                userStore.setConnectedMachine(parseInt(number))
                alert("기기 로그인이 완료되었습니다")
                emit('machineConnect'); //  상부 컴포넌트에 연결 되었음을 알린다.
            }).catch((error) => {
                alert("기기 연결 중 오류가 발생했습니다.")
            })
        }
    }
    catch(error){
        alert("유효하지 않는 코드 입니다.")
        router.back()
    }
}

</script>

<template>
    <div>
        <QrcodeStream @detect="onDetect"></QrcodeStream>
    </div>
</template>

<style scoped>

</style>