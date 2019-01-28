import 'core-js/es6/promise'
import 'core-js/es6/string'
import 'core-js/es7/array'
import Vue from 'vue'
import BootstrapVue from 'bootstrap-vue'
import App from './App'
import router from './router'
import firebase from 'firebase/app'

Vue.use(BootstrapVue)

// Initialize Firebase
var config = {
    apiKey: process.env.VUE_APP_AUTH_API_KEY,
    authDomain: process.env.VUE_APP_AUTH_DOMAIN,
    databaseURL: process.env.VUE_APP_AUTH_DATABASE_URL,
    projectId: process.env.VUE_APP_AUTH_PROJECT_ID,
    storageBucket: process.env.VUE_APP_AUTH_STORAGE_BUCKET,
    messagingSenderId: process.env.VUE_APP_AUTH_MESSAGING_SENDER_ID
};
firebase.initializeApp(config);

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: {
    App
  }
})
