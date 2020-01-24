<template>
  <div class="app flex-row align-items-center">
    <div class="container">
      <b-row class="justify-content-center">
        <b-col md="8">
          <b-card-group>
            <b-card no-body class="p-4">
              <b-card-body>
                <b-form>
                  <h1>Login</h1>
                  <p class="text-muted">アカウント情報を入力してください</p>
                  <b-input-group class="mb-3">
                    <b-input-group-prepend><b-input-group-text><i class="icon-user"></i></b-input-group-text></b-input-group-prepend>
                    <b-form-input type="text" class="form-control" placeholder="Eメール" autocomplete="username email" v-model="username" />
                  </b-input-group>
                  <b-input-group class="mb-4">
                    <b-input-group-prepend><b-input-group-text><i class="icon-lock"></i></b-input-group-text></b-input-group-prepend>
                    <b-form-input type="password" class="form-control" placeholder="パスワード" autocomplete="current-password" v-model="password"/>
                  </b-input-group>
                  <b-row>
                    <b-col cols="6">
                      <b-button variant="primary" class="px-4" @click="signIn">ログイン</b-button>
                    </b-col>
                    <b-col cols="6" class="text-right">
                      <b-button variant="link" class="px-0">パスワードをお忘れですか?</b-button>
                    </b-col>
                  </b-row>
                </b-form>
              </b-card-body>
            </b-card>
            <b-card no-body class="text-white bg-primary py-5 d-md-down-none" style="width:44%">
              <b-card-body class="text-center">
                <div>
                  <h2>Sign up</h2>
                  <p>新規アカウントを登録する場合は、以下の「アカウント登録」から登録してください。</p>
                  <b-button variant="primary" class="active mt-3" @click="register">アカウント登録</b-button>
                </div>
              </b-card-body>
            </b-card>
          </b-card-group>
        </b-col>
      </b-row>
    </div>
  </div>
</template>

<script>
  import firebase from 'firebase/app'

  export default {
    name: "Signin",
    data () {
      return {
        username: '',
        password: ''
      }
    },
    methods: {
      signIn: async function () {
        await firebase.auth().signInWithEmailAndPassword(this.username, this.password)
          .then(() => this.$router.push('/dashboard'))
          .catch(e => {
             alert(e)
          })
      },
      register: function () {
		this.$router.push('/pages/register')
      }
    }
  }
</script>
