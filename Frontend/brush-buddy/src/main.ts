import App from './App.vue';
import router from './router';
import { createApp } from 'vue';
const app = createApp(App);

// Vuetify
import { createVuetify } from 'vuetify'
import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'
import { aliases, mdi } from 'vuetify/iconsets/mdi'
import "@mdi/font/css/materialdesignicons.min.css";
import 'vuetify/styles'
import { createPinia } from 'pinia';
import { VueQrcodeReader } from 'vue-qrcode-reader';
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
import piniaPluginPersistedstate from "pinia-plugin-persistedstate";
pinia.use(piniaPluginPersistedstate);

declare global {
  interface Window {
    Kakao: any;
  }
}

window.Kakao.init(import.meta.env.VITE_APP_KAKAO_API_JS_KEY);
createApp(App).use(router).use(vuetify).use(pinia).use(VueQrcodeReader).mount('#app');

