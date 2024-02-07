import App from './App.vue';
import router from './router';
import { createApp } from 'vue';
import { createPinia } from 'pinia'
const app = createApp(App);

// Vuetify
import { createVuetify } from 'vuetify'
import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'
import '@mdi/font/css/materialdesignicons.css' // Ensure you are using css-loader\
import 'vuetify/styles'
const vuetify = createVuetify({
  components,
  directives,
  icons: {
    defaultSet: 'mdi', // This is already the default value - only for display purposes
  },
})


app.use(createPinia());
app.use(router);
app.use(vuetify);

app.mount('#app');

