import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import stores from "@/stores/index";

const app = createApp(App);

app.use(router).use(stores);

app.mount('#app');


