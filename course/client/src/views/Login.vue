<template>
<div class="app-login">
<md-card class="md-elevation-24">
<div class="app-header-bar"> 
<img src="@/assets/avatar.jpg"/>
<h2> 登陆</h2>
</div>

<md-dialog-alert
:md-active.sync="isAlert"
:md-content="alertMessage"
md-confirm-text="确定"
/>

<md-card-content>
<div>
    <label v-if="userIsInput" class="app-danger">{{userCanLogin?"用户名不能为空":"用户名格式不对"}}</label>
<md-field>
    <md-icon md-src="/img/user.svg"/>
    <label>用户名</label>
    <md-input v-model="user" required></md-input>
</md-field>

 <label v-if="pwdIsInput" class="app-danger">{{pwdCanLogin?"密码不能为空":"密码格式不对"}}</label>
<md-field>
<md-icon md-src="/img/pwd.svg"/>
<label>密码</label>
<md-input type="password" v-model="pwd" required></md-input>
</md-field>
<md-button @click="login()" class="md-primary md-raised app-max-width">登陆</md-button>
</div> 
</md-card-content>
</md-card> 
</div>
</template>
<script>
import checker from "../util/checker.js";
import api from "../util/api.js";
import tokenstore from "../util/tokenstore.js";
import qs from "qs";
import cookie from '../util/cookie.js';
export default {
  data() {
    return {
      user: "",
      userCanLogin: true,
      userIsInput: false,
      pwd: "",
      pwdCanLogin: true,
      pwdIsInput: false,
      isAlert: false,
      alertMessage: ""
    };
  },
  methods: {
    login() {
      this.userIsInput = false;
      this.pwdIsInput = false;
      var user = this.user;
      var pwd = this.pwd;

      var userIsOk = false;
      var pwdIsOk = false;

      if (checker.strIsNullOrBlank(user)) {
        this.userCanLogin = true;
        this.userIsInput = true;
      } else if (
        !checker.checkStrPattern(user, /[\w]{3,10}/) ||
        user.length > 10
      ) {
        this.userCanLogin = false;
        this.userIsInput = true;
      } else {
        userIsOk = true;
      }
      if (checker.strIsNullOrBlank(pwd)) {
        this.pwdCanLogin = true;
        this.pwdIsInput = true;
      } else if (
        !checker.checkStrPattern(pwd, /[\S]{6,10}/) ||
        pwd.length > 10
      ) {
        this.pwdCanLogin = false;
        this.pwdIsInput = true;
      } else {
        pwdIsOk = true;
      }
      if (userIsOk && pwdIsOk) {
        var data = {
          user,
          pwd
        };
        this.axios({
          method: "POST",
          headers: { "content-type": "application/x-www-form-urlencoded" },
          data: qs.stringify(data),
          url: api.login
        })
          .then(res => {
            switch (res.data.status) {
              case api.success:
                tokenstore.addToken(res.data.info.token);
                cookie.addCookie("uid",res.data.info.uid);
                this.$router.push("/index")
                break;
              case api.error:
                this.alertMessage = "登陆失败,请检查账户或密码";
                this.isAlert = true;
                break;
            }
          })
          .catch(err => {
            this.alertMessage = "请检查网络情况";
            this.isAlert = true;
          });
      }
    }
  }
};
</script>

<style>
.app-login {
  position: relative;
  left: 10%;
  width: 80%;
}
.app-header-bar {
  display: block;
  text-align: center !important;
  margin: 0.5rem;
  color: #448aff;
}
.app-header-bar img {
  height: 10rem;
  width: 100%;
  border-radius: 2px;
  margin: 2px;
}
li {
  list-style: none;
}
.app-max-width {
  width: 100%;
}
.app-danger {
  color: red;
}
</style>


