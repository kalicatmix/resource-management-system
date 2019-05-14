<template>
<div class="app-container">
<app-title/>
 <md-card>
<md-card-header>
<div class="app-header">
<router-link to="/">
  <md-button class="md-primary">登陆</md-button>
</router-link>
<router-link to="/reg">
<md-button class="md-primary">注册</md-button>
</router-link>
<router-link to="/about">
<md-button class="md-primary">关于</md-button>
</router-link>
  </div>
</md-card-header>
 <md-card-content>
 </md-card-content>
 </md-card>
</div>
</template>
<script>
import appTitle from '@/components/Title.vue'
export default {
  name:'home',
  components:{
    appTitle
  }
}
</script>
<style>
.app-header{
  display:flex;
  flex-flow: row wrap
}
.app-container{
  position: relative;
  left:10%;
  width:80%;
}
</style>


