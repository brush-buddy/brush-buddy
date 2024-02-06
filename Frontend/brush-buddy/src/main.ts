import App from './App.vue';
import router from './router';
import { createApp } from 'vue';
import 'vuetify/styles'
import { createVuetify } from 'vuetify'
import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'
import { aliases, mdi } from 'vuetify/iconsets/mdi'
import "@mdi/font/css/materialdesignicons.min.css";
import { createPinia } from 'pinia';

const vuetify = createVuetify({
    components,
    directives,
    icons: {
        defaultSet: 'mdi',
        aliases,
        sets: {
          mdi
        }
      },
});
const pinia = createPinia();

window.Kakao.init(import.meta.env.VITE_APP_KAKAO_API_JS_KEY);
createApp(App).use(router).use(vuetify).use(pinia).mount('#app');

