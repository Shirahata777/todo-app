import { wrapFunctional } from './utils'

export { default as NuxtLogo } from '../../components/NuxtLogo.vue'
export { default as Tutorial } from '../../components/Tutorial.vue'
export { default as VuetifyLogo } from '../../components/VuetifyLogo.vue'
export { default as VuetifyCalendar } from '../../components/vuetify-calendar.vue'
export { default as Calendar } from '../../components/calendar/calendar.vue'
export { default as UiDialog } from '../../components/ui/dialog.vue'
export { default as UiFormModal } from '../../components/ui/form-modal.vue'
export { default as UiSaveForm } from '../../components/ui/save-form.vue'
export { default as UiSchedulePicker } from '../../components/ui/schedule-picker.vue'

export const LazyNuxtLogo = import('../../components/NuxtLogo.vue' /* webpackChunkName: "components/nuxt-logo" */).then(c => wrapFunctional(c.default || c))
export const LazyTutorial = import('../../components/Tutorial.vue' /* webpackChunkName: "components/tutorial" */).then(c => wrapFunctional(c.default || c))
export const LazyVuetifyLogo = import('../../components/VuetifyLogo.vue' /* webpackChunkName: "components/vuetify-logo" */).then(c => wrapFunctional(c.default || c))
export const LazyVuetifyCalendar = import('../../components/vuetify-calendar.vue' /* webpackChunkName: "components/vuetify-calendar" */).then(c => wrapFunctional(c.default || c))
export const LazyCalendar = import('../../components/calendar/calendar.vue' /* webpackChunkName: "components/calendar" */).then(c => wrapFunctional(c.default || c))
export const LazyUiDialog = import('../../components/ui/dialog.vue' /* webpackChunkName: "components/ui-dialog" */).then(c => wrapFunctional(c.default || c))
export const LazyUiFormModal = import('../../components/ui/form-modal.vue' /* webpackChunkName: "components/ui-form-modal" */).then(c => wrapFunctional(c.default || c))
export const LazyUiSaveForm = import('../../components/ui/save-form.vue' /* webpackChunkName: "components/ui-save-form" */).then(c => wrapFunctional(c.default || c))
export const LazyUiSchedulePicker = import('../../components/ui/schedule-picker.vue' /* webpackChunkName: "components/ui-schedule-picker" */).then(c => wrapFunctional(c.default || c))
