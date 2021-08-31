import Vue from 'vue'
import { wrapFunctional } from './utils'

const components = {
  NuxtLogo: () => import('../../components/NuxtLogo.vue' /* webpackChunkName: "components/nuxt-logo" */).then(c => wrapFunctional(c.default || c)),
  Tutorial: () => import('../../components/Tutorial.vue' /* webpackChunkName: "components/tutorial" */).then(c => wrapFunctional(c.default || c)),
  VuetifyLogo: () => import('../../components/VuetifyLogo.vue' /* webpackChunkName: "components/vuetify-logo" */).then(c => wrapFunctional(c.default || c)),
  VuetifyCalendar: () => import('../../components/vuetify-calendar.vue' /* webpackChunkName: "components/vuetify-calendar" */).then(c => wrapFunctional(c.default || c)),
  Calendar: () => import('../../components/calendar/calendar.vue' /* webpackChunkName: "components/calendar" */).then(c => wrapFunctional(c.default || c)),
  UiDialog: () => import('../../components/ui/dialog.vue' /* webpackChunkName: "components/ui-dialog" */).then(c => wrapFunctional(c.default || c)),
  UiFormModal: () => import('../../components/ui/form-modal.vue' /* webpackChunkName: "components/ui-form-modal" */).then(c => wrapFunctional(c.default || c)),
  UiSaveForm: () => import('../../components/ui/save-form.vue' /* webpackChunkName: "components/ui-save-form" */).then(c => wrapFunctional(c.default || c)),
  UiSchedulePicker: () => import('../../components/ui/schedule-picker.vue' /* webpackChunkName: "components/ui-schedule-picker" */).then(c => wrapFunctional(c.default || c))
}

for (const name in components) {
  Vue.component(name, components[name])
  Vue.component('Lazy' + name, components[name])
}
