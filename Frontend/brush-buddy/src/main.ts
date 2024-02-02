import App from './App.vue';
import router from './router';
import { createApp } from 'vue';
import { createPinia } from 'pinia'

// import { aliases, mdi } from 'vuetify/iconsets/mdi'


// Vuetify
import { createVuetify } from 'vuetify'
import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'

const app = createApp(App);

const vuetify = createVuetify({
  components,
  directives
})

app.use(createPinia());
app.use(router);
app.use(vuetify);

app.mount('#app');