import App from './App.vue';
import router from './router';
import stores from "@/stores/index";
import { createApp } from 'vue';
const app = createApp(App);

app.use(router).use(stores);

app.mount('#app');

