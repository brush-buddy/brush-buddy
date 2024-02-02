import { createApp } from 'vue';
<<<<<<< HEAD

import { createPinia } from 'pinia';

import App from './App.vue';
import router from './router';

const app = createApp(App);

app.use(createPinia());
app.use(router);

app.mount('#app');
=======
import App from './App.vue';
import router from './router';
import stores from "@/stores/index";

const app = createApp(App);

app.use(router).use(stores);

app.mount('#app');


>>>>>>> 5639bc58f74ffe143c962ddfd0efe76a1eb4173c
