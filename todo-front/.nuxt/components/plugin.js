import Vue from 'vue'
import { wrapFunctional } from './utils'

const components = {
  Calendar: () => import('../../components/Calendar.vue' /* webpackChunkName: "components/calendar" */).then(c => wrapFunctional(c.default || c)),
  NuxtLogo: () => import('../../components/NuxtLogo.vue' /* webpackChunkName: "components/nuxt-logo" */).then(c => wrapFunctional(c.default || c)),
  Tutorial: () => import('../../components/Tutorial.vue' /* webpackChunkName: "components/tutorial" */).then(c => wrapFunctional(c.default || c)),
  VuetifyLogo: () => import('../../components/VuetifyLogo.vue' /* webpackChunkName: "components/vuetify-logo" */).then(c => wrapFunctional(c.default || c)),
  EventFormModal: () => import('../../components/event-form-modal.vue' /* webpackChunkName: "components/event-form-modal" */).then(c => wrapFunctional(c.default || c)),
  EventForm: () => import('../../components/event-form.vue' /* webpackChunkName: "components/event-form" */).then(c => wrapFunctional(c.default || c)),
  VuetifyCalendar: () => import('../../components/vuetify-calendar.vue' /* webpackChunkName: "components/vuetify-calendar" */).then(c => wrapFunctional(c.default || c))
}

for (const name in components) {
  Vue.component(name, components[name])
  Vue.component('Lazy' + name, components[name])
}
