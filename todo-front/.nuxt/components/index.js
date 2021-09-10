import { wrapFunctional } from './utils'

export { default as VuetifyCalendar } from '../../components/vuetify-calendar.vue'
export { default as Calendar } from '../../components/calendar/calendar.vue'
export { default as UiDialog } from '../../components/ui/dialog.vue'
export { default as UiDraggableCardList } from '../../components/ui/draggable-card-list.vue'
export { default as UiFormModal } from '../../components/ui/form-modal.vue'
export { default as UiSaveForm } from '../../components/ui/save-form.vue'
export { default as UiSchedulePicker } from '../../components/ui/schedule-picker.vue'

export const LazyVuetifyCalendar = import('../../components/vuetify-calendar.vue' /* webpackChunkName: "components/vuetify-calendar" */).then(c => wrapFunctional(c.default || c))
export const LazyCalendar = import('../../components/calendar/calendar.vue' /* webpackChunkName: "components/calendar" */).then(c => wrapFunctional(c.default || c))
export const LazyUiDialog = import('../../components/ui/dialog.vue' /* webpackChunkName: "components/ui-dialog" */).then(c => wrapFunctional(c.default || c))
export const LazyUiDraggableCardList = import('../../components/ui/draggable-card-list.vue' /* webpackChunkName: "components/ui-draggable-card-list" */).then(c => wrapFunctional(c.default || c))
export const LazyUiFormModal = import('../../components/ui/form-modal.vue' /* webpackChunkName: "components/ui-form-modal" */).then(c => wrapFunctional(c.default || c))
export const LazyUiSaveForm = import('../../components/ui/save-form.vue' /* webpackChunkName: "components/ui-save-form" */).then(c => wrapFunctional(c.default || c))
export const LazyUiSchedulePicker = import('../../components/ui/schedule-picker.vue' /* webpackChunkName: "components/ui-schedule-picker" */).then(c => wrapFunctional(c.default || c))
