import Vuex from 'vuex'

const createStore = () => {
  return new Vuex.Store({
    state: function () {
      return {
        isDarken: false //ダークモードのオンオフ
      }
    },
    mutations: {
      toggle: function (state) {
        state.isDarken = !state.isDarken //トグルの切替
      }
    },
    actions: {
      changeToggle: function (context) {
        context.commit("toggle") //actionからmutationのtoggleを呼び出している
      }
    }
  })
}

export default createStore
