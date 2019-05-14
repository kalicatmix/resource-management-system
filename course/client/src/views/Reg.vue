<template>
<div class="app-login">
  <form>
<md-card class="md-elevation-24">
<div class="app-header-bar"> 
<img src="@/assets/timg.jpg"/>
<h2> 注册</h2>
</div>
<md-dialog-alert :md-active.sync="isAlert"
:md-content="alertMessage"
md-confirm-text="知道了"
/>

<md-card-content>
<div>
    <label v-if="userIsInput" class="app-danger">{{userCanLogin?"用户名不能为空":"用户名格式不对"}}</label>
<md-field>
    <md-icon md-src="/img/user.svg"/>
    <label>用户名</label>
    <md-input v-model="user" required></md-input>
</md-field>

 <label v-if="nameIsInput" class="app-danger">昵称不能不填哟</label>
<md-field>
    <md-icon md-src="/img/head.svg"/>
    <label>昵称</label>
    <md-input v-model="name" required  max-length="10"></md-input>
</md-field>

 <label v-if="pwdIsInput" class="app-danger">{{pwdCanLogin?"密码不能为空":"密码格式不对"}}</label>
<md-field>
<md-icon md-src="/img/pwd.svg"/>
<label>密码</label>
<md-input type="password" v-model="pwd" required></md-input>
</md-field>

<label v-if="emailIsInput" class="app-danger">{{emailCanLogin?"邮箱不能不填哟":"邮箱格式不对哟"}}</label>
<md-field>
<md-icon md-src="/img/email.svg"/>
<label>邮箱</label>
<md-input type="email" v-model="email" required></md-input>
</md-field>

<label v-if="numberIsInput" class="app-danger">填一填你的幸运数字</label>
<md-field>
<md-icon md-src="/img/number.svg"/>
<label>星运数字</label>
<md-input type="number" v-model="number" required></md-input>
</md-field>


<div class="app-field">
<h3>性别</h3>
<md-radio v-model="gender" value="male">男</md-radio>
<md-radio v-model="gender" value="female">女</md-radio>
</div>
<md-button @click="login()" class="md-primary md-raised app-max-width">注册</md-button>
</div> 
</md-card-content>
</md-card> 
  </form>
</div>
</template>
<script>
import checker from "../util/checker.js";
import api from '../util/api.js';
import qs from 'qs';

export default {
  data() {
    return {
      user: "",
      userCanLogin: true,
      userIsInput: false,
      pwd: "",
      pwdCanLogin: true,
      pwdIsInput: false,
      gender: "male",
      name: "",
      nameIsInput: false,
      email: "",
      emailCanLogin: true,
      emailIsInput: false,
      number: "",
      numberIsInput: false,
      isAlert: false,
      alertMessage: ""
    };
  },
  methods: {
    login() {
      this.userIsInput = false;
      this.pwdIsInput = false;
      this.nameIsInput = false;
      this.emailIsInput = false;
      this.numberIsInput = false;

      var user = this.user;
      var pwd = this.pwd;
      var userIsOK = false;
      var pwdIsOk = false;
      var nameIsOk = false;
      var emailIsOk = false;
      var numberIsOk = false;

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
        userIsOK = true;
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

      if (checker.strIsNullOrBlank(this.name)) {
        this.nameIsInput = true;
      } else {
        nameIsOk = true;
      }

      if (checker.strIsNullOrBlank(this.email)) {
        this.emailCanLogin = true;
        this.emailIsInput = true;
      } else if (
        !checker.checkStrPattern(this.email, /[\w\._]+@[\w_]+\.[\w]+/) ||
        this.email > 20
      ) {
        this.emailCanLogin = false;
        this.emailIsInput = true;
      } else {
        emailIsOk = true;
      }

      if (checker.strIsNullOrBlank(this.number)) {
        this.numberIsInput = true;
      } else {
        numberIsOk = true;
      }

      var checkOk = userIsOK && pwdIsOk && nameIsOk && emailIsOk;
      if (checkOk) {
        var data={
          user:this.user,
          name:this.name,
          pwd:this.pwd,
          email:this.email,
          luckynumber:this.number
          }
        this.axios(
        {
          method: "POST",
          headers: { "content-type": "application/x-www-form-urlencoded" },
          data: qs.stringify(data),
          url: api.addUser
        }
        ).then(res => {
            switch(res.data.status){
              case api.success:
              this.alertMessage="注册成功了,快去登陆吧"
              this.isAlert=true
              break;
              case api.error:
              this.alertMessage="注册失败了,重新注册吧"
              this.isAlert=true
              break;
            }
          })
          .catch(err => {
            this.alertMessage="请检查你的网络哟"
            this.isAlert=true;
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
.app-field {
  color: #448aff;
}
</style>



