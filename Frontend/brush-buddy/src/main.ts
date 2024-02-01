import App from './App.vue';
import router from './router';
import stores from "@/stores/index";
import { createApp } from 'vue';
import { createPinia } from 'pinia'
const app = createApp(App);

// Vuetify
import { createVuetify } from 'vuetify'
import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'

const vuetify = createVuetify({
  components,
  directives
})


app.use(createPinia());
app.use(router);
app.use(vuetify);
app.use(stores);

app.mount('#app');

