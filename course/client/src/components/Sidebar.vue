<template>
  <div >
      <md-app>
      <md-app-drawer :md-active.sync="actived"  md-persistent="mini">
     <md-app-toolbar class="md-primary" md-elevation="0">
        <md-button @click="reload()">
          <md-icon md-src="/img/head.svg"/>
        </md-button>
        <span class="md-title">信息</span>
      </md-app-toolbar>
        <md-list>
          <md-list-item>
                <img src="@/assets/avatar.jpg"/>
          </md-list-item>
          <md-list-item class="app-manager-divider">
 <span  class="app-manager-list-item">{{name}}</span>
          </md-list-item>
            <md-list-item>
                 <span class="app-manager-list-item">幸运数字</span>
            </md-list-item>
            <md-list-item class="app-manager-divider">
            <span class="app-manager-list-item" >{{luckynumber}}</span>
          </md-list-item>

          <md-list-item class="app-manager-divider">
           <md-menu md-direction="top-end" class="app-manager-list-item">
           <md-button  md-menu-trigger class="app-manager-list-item">课程</md-button>
          <md-menu-content>
          <md-menu-item>
              <md-button @click="viewCourse()">课程查看
              </md-button>
          </md-menu-item>
          <md-menu-item>
              <md-button @click="course()">我的选课</md-button></md-menu-item>
          </md-menu-content>
           </md-menu>
          </md-list-item>

          <md-list-item>
            <md-menu md-direction="top-end" class="app-manager-list-item">
           <md-button md-menu-trigger class="app-manager-list-item">我的信息</md-button>
           <md-menu-content>
           <md-menu-item>
             <md-button @click="chgpwd()">更改密码</md-button>
         </md-menu-item>
         <md-menu-item>
             <md-button @click="chginfo()">更改信息</md-button>
         </md-menu-item>
           </md-menu-content>
            </md-menu>
          </md-list-item>
        </md-list>
      </md-app-drawer>
      </md-app>
  </div>
</template>

<script>
 import tokenstore from '../util/tokenstore.js'
 import api from "../util/api.js"
  export default {
   
  beforeMount(){
     //get user info
     this.axios({
       method:'get',
       url:api.queryUserInfo+"/name",
       headers:{authorize:tokenstore.getToken()}
       }).then(
           result=>{
             switch(result.data.status){
               case api.success:
               this.name=result.data.info.info
               break;
               case api.error:
               break;
             }
           }
       )

       this.axios({
       method:'get',
       url:api.queryUserInfo+"/luckynumber",
       headers:{authorize:tokenstore.getToken()}
       }).then(
           result=>{
             switch(result.data.status){
               case api.success:
               this.luckynumber=result.data.info.info
               break;
               case api.error:
               break;
             }
           }
       )
  },
    data: () => ({
      actived:true,  
      name:"幸运的小袋鼠",
      luckynumber:"12324324"
    }),
    methods: {
      viewCourse(){
      this.$emit("viewCourse")
      },
      course(){
      this.$emit("course")
      },
      chginfo(){
      this.$emit("chginfo")
      },
      chgpwd(){
      this.$emit("chgpwd")
      },
      reload(){
        this.$emit("reload")
      }
    }
  }
</script>

<style>
  
  .md-drawer {
    width: 100%;
  }
  .app-manager-list-item{
      width:100%;
      text-align: center;
      font-size: 2rem;
  }
  .app-manager-divider{
      margin-left: 10%;
      margin-right: 10%;
      border-bottom-color: gray;
      border-bottom-style:solid;
      border-width: 1px;
  }
</style>