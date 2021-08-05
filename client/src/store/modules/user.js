// import userApi from '@/api/user'
import router from '@/router'
import userApi from '@/api/user'

const state = {
  isLogin: false,
  isResister: false, 
  accessToken: null,
  refreshToken: null,
  tempNickname: null,
  myInfo: {
    id: null,
    nickname: null,
    userFileUrl: null
  }
}

const actions = {
  // 페이지 이동
  moveToLogin () {
    router.push({ name: 'Login' })
  },
  moveToSignup () {
    router.push({ name: 'Signup' })
  },
  moveToSignupEmail () {
    router.push({ name: 'SignupEmail' })
  },
  moveToUpdateInfo () {
    router.push({ name: 'UpdateInfo' })
  },
  // api 요청
  async onSignup ({ dispatch, commit }, userData) {
    console.log(userData)
    await userApi.signup(userData)
      .then((res) => {
        console.log(res)
        commit('SET_TEMP_NICKNAME', userData.nickname)

        // 인증 메일 발송을 위한 데이터 준비
        const userInfo = {
          email: userData.email,
          id: res.data.id
        }
        // 메일 발송 요청 함수로 연결
        dispatch('sendEmail', userInfo)
        // 페이지 이동
        dispatch('moveToSignupEmail')
      })
      .catch((err) => {
        // 실패 > 왜 실패할까..
        console.log(err.response)
      })
  },
  async sendEmail ({ commit }, userData) {
    await userApi.sendEmail(userData)
      .then((res) => {
        console.log(res)
        commit('SET_ISRESISTER', true)
      })
      .catch((err) => {
        console.log(err.response)
      })
  },
  async onLogin ({ commit }, userData) {
    await userApi.login(userData)
      .then((res) => {
        console.log(res)
        commit('SET_ISLOGIN', true)
        commit('SET_ACCESS_TOKEN', res.headers.accesstoken)
        commit('SET_REFRESH_TOKEN', res.headers.refreshtoken)
        commit('SET_MY_INFO', res.data.data)
        router.push({ name: 'Feed' })
      })
      .catch((err) => {
        if (err.response.status === 403) {
          commit('SET_MY_INFO', err.response.data.data)
        }
        return Promise.reject(err.response)
      })
  },
  onLogout({ commit, dispatch }) {
    commit('SET_ISLOGIN', false)
    dispatch('moveToLogin')
    commit('SET_ACCESS_TOKEN', null)
    commit('SET_REFRESH_TOKEN', null)
    commit('RESET_MY_INFO')
  },
  async onUpdatePassword({ state }, userData) {
    await userApi.changePassword(state.myInfo.id, userData)
      .then((res) => {
        console.log(res)
        return res
      })
      .catch((err) => {
        console.log(err.response)
        return Promise.reject(err.response)
      })
  },
  // async onResetPassword({ dispatch }, userData) {
    // console.log(userData)
    // await userApi.resetPassword(userData)
    //   .then((res) => {
    //     console.log(res)
    //     dispatch('moveToLogin')
    //   })
    //   .catch((err) => {
    //     console.log(err.response)
    //     return Promise.reject(err.response)
    //   })
  // },
}

const mutations = {
  SET_ISLOGIN(state, payload) {
    state.isLogin = payload
  },
  SET_ISRESISTER(state, payload) {
    state.isResister = payload
  },
  SET_ACCESS_TOKEN(state, payload) {
    state.accessToken = payload
  },
  SET_REFRESH_TOKEN(state, payload) {
    state.refreshToken = payload
  },
  SET_TEMP_NICKNAME(state, payload) {
    state.tempNickname = payload
  },
  SET_MY_INFO(state, payload) {
    state.myInfo.id = payload.id
    state.myInfo.nickname = payload.nickname
    state.myInfo.userFileUrl = payload.userFileUrl
  },
  RESET_MY_INFO(state) {
    state.myInfo.id = null
    state.myInfo.nickname = null
    state.myInfo.userFileUrl = null
  },

}

const getters = {

}

export default {
  namespaced: true,
  state,
  actions,
  mutations,
  getters
}