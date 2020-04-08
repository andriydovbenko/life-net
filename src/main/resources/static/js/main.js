import Vue from 'vue'
import VueResource from 'vue-resource'
import App from 'pages/Application.vue'

Vue.use(VueResource)

new Vue({
    el: '#app',
    render: a => a(App)
})