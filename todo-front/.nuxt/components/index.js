import { wrapFunctional } from './utils'

export { default as Calendar } from '../../components/Calendar.vue'
export { default as NuxtLogo } from '../../components/NuxtLogo.vue'
export { default as Tutorial } from '../../components/Tutorial.vue'
export { default as VuetifyLogo } from '../../components/VuetifyLogo.vue'
export { default as EventFormModal } from '../../components/event-form-modal.vue'
export { default as EventForm } from '../../components/event-form.vue'
export { default as VuetifyCalendar } from '../../components/vuetify-calendar.vue'
export { default as SaveUiDialog } from '../../components/save/ui/dialog.vue'
export { default as SaveUiSchedulePicker } from '../../components/save/ui/schedule-picker.vue'

export const LazyCalendar = import('../../components/Calendar.vue' /* webpackChunkName: "components/calendar" */).then(c => wrapFunctional(c.default || c))
export const LazyNuxtLogo = import('../../components/NuxtLogo.vue' /* webpackChunkName: "components/nuxt-logo" */).then(c => wrapFunctional(c.default || c))
export const LazyTutorial = import('../../components/Tutorial.vue' /* webpackChunkName: "components/tutorial" */).then(c => wrapFunctional(c.default || c))
export const LazyVuetifyLogo = import('../../components/VuetifyLogo.vue' /* webpackChunkName: "components/vuetify-logo" */).then(c => wrapFunctional(c.default || c))
export const LazyEventFormModal = import('../../components/event-form-modal.vue' /* webpackChunkName: "components/event-form-modal" */).then(c => wrapFunctional(c.default || c))
export const LazyEventForm = import('../../components/event-form.vue' /* webpackChunkName: "components/event-form" */).then(c => wrapFunctional(c.default || c))
export const LazyVuetifyCalendar = import('../../components/vuetify-calendar.vue' /* webpackChunkName: "components/vuetify-calendar" */).then(c => wrapFunctional(c.default || c))
export const LazySaveUiDialog = import('../../components/save/ui/dialog.vue' /* webpackChunkName: "components/save-ui-dialog" */).then(c => wrapFunctional(c.default || c))
export const LazySaveUiSchedulePicker = import('../../components/save/ui/schedule-picker.vue' /* webpackChunkName: "components/save-ui-schedule-picker" */).then(c => wrapFunctional(c.default || c))
