import Vue from 'vue'
import Router from 'vue-router'
import { normalizeURL, decode } from 'ufo'
import { interopDefault } from './utils'
import scrollBehavior from './router.scrollBehavior.js'

const _5a90a035 = () => interopDefault(import('../pages/calendar.vue' /* webpackChunkName: "pages/calendar" */))
const _19ac9318 = () => interopDefault(import('../pages/save.vue' /* webpackChunkName: "pages/save" */))
const _21d204d0 = () => interopDefault(import('../pages/slug/_inspire.vue' /* webpackChunkName: "pages/slug/_inspire" */))
const _3991f56b = () => interopDefault(import('../pages/index.vue' /* webpackChunkName: "pages/index" */))

const emptyFn = () => {}

Vue.use(Router)

export const routerOptions = {
  mode: 'history',
  base: '/',
  linkActiveClass: 'nuxt-link-active',
  linkExactActiveClass: 'nuxt-link-exact-active',
  scrollBehavior,

  routes: [{
    path: "/calendar",
    component: _5a90a035,
    name: "calendar"
  }, {
    path: "/save",
    component: _19ac9318,
    name: "save"
  }, {
    path: "/slug/:inspire?",
    component: _21d204d0,
    name: "slug-inspire"
  }, {
    path: "/",
    component: _3991f56b,
    name: "index"
  }, {
    path: "/inspire/:todono",
    component: _21d204d0,
    name: "todono"
  }],

  fallback: false
}

export function createRouter (ssrContext, config) {
  const base = (config._app && config._app.basePath) || routerOptions.base
  const router = new Router({ ...routerOptions, base  })

  // TODO: remove in Nuxt 3
  const originalPush = router.push
  router.push = function push (location, onComplete = emptyFn, onAbort) {
    return originalPush.call(this, location, onComplete, onAbort)
  }

  const resolve = router.resolve.bind(router)
  router.resolve = (to, current, append) => {
    if (typeof to === 'string') {
      to = normalizeURL(to)
    }
    return resolve(to, current, append)
  }

  return router
}
