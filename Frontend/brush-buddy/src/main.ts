import App from './App.vue';
import router from './router';
import { createVuetify } from 'vuetify'
import stores from "@/stores/index";
import { createApp } from 'vue';
import * as components from 'vuetify/components'
const app = createApp(App);
import * as directives from 'vuetify/directives'
// import { aliases, mdi } from 'vuetify/iconsets/mdi'


const vuetify = createVuetify({
    components,
    directives,
   
    })

app.use(router).use(stores);
app.use(vuetify);

app.mount('#app');