<template>
    <div>
        <app-title/>
    <div class="app">
    <div class="app-size-bar"><side-bar @viewCourse="viewCourse()" @course="course()" @chginfo="chginfo()" @chgpwd="chgpwd()" @reload="reload()"/></div>
    <div class="app-content">
       <news v-if="viewIsNews" />
       <my-course v-if="viewIsMyCourse"/>
        <course v-if="viewIsCourse"/>
   <md-dialog :md-active.sync="isPwdDialog">
   <md-dialog-title>更改密码</md-dialog-title>
 <div class="app-dialog-content">
   <md-field>
<md-icon md-src="/img/head.svg"/>
<label>旧密码</label>
<md-input v-model="oldPwd" type="password" required></md-input>
   </md-field>
   <md-field>
       <md-icon md-src="/img/head.svg"/>
<label>新密码</label>
<md-input v-model="newPwd1" type="password" required></md-input>
   </md-field>
   <md-field>
       <md-icon md-src="/img/head.svg"/>
<label>再次输入新密码</label>
<md-input v-model="newPwd2" type="password" required></md-input>
   </md-field>
   <md-button @click="hideDialog()" class="app-dialog-btn md-accent md-raised">取消修改</md-button>
   <md-button @click="pwdChangeCfm()" class="app-dialog-btn md-primary md-raised">确定修改</md-button>
  </div>
   </md-dialog>


   <md-dialog :md-active.sync="isInfoDialog">
   <md-dialog-title>更改信息</md-dialog-title>
  <div class="app-dialog-content">
       <md-field>
<md-icon md-src="/img/head.svg"/>
<label>昵称</label>
<md-input></md-input>
   </md-field>
   <md-field>
       <md-icon md-src="/img/head.svg"/>
<label>幸运数字</label>
<md-input></md-input>
   </md-field>
   <md-field>
       <md-icon md-src="/img/head.svg"/>
<label>email</label>
<md-input></md-input>
   </md-field>
   <md-button @click="hideDialog()" class="app-dialog-btn md-accent md-raised">取消修改</md-button>
   <md-button @click="infoChangeCfm()" class="app-dialog-btn md-primary md-raised">确定修改</md-button>
  </div>
   </md-dialog>
   <md-dialog-alert :md-active.sync="isAlertDialog"  :md-content.sync="alertContent" md-confirm-text="知道了"/>
    </div>
    </div>
    </div>
</template>

<script>
import appTitle from "../components/Title.vue"
import sideBar from "../components/Sidebar.vue"
import news from "./News.vue"
import myCourse from './MyCourse.vue'
import course from './Course.vue'
import tokenstore from '../util/tokenstore.js'
import api from "../util/api.js"
import qs from "qs"
export default {
  beforeMount(){
    
    },
    data(){
    return {
     viewIsNews:true,
     viewIsMyCourse:false,
     viewIsCourse:false,
     isPwdDialog:false,
     isInfoDialog:false,
     isAlertDialog:false,
     alertContent:"",
     oldPwd:"",
     newPwd1:"",
     newPwd2:""
    }
    },
    components:{
        appTitle,
        sideBar,
        news,
        myCourse,
        course
    },
    methods:{
        hideAllView(){
         var views=["viewIsNews","viewIsCourse","viewIsMyCourse"];
         for (var view of views){
          this[view]=false
        }
       },
        viewCourse(){
            this.hideAllView()
            this.viewIsCourse=true
        },
        course(){
            this.hideAllView()
            this.viewIsMyCourse=true
        },
        reload(){
           this.hideAllView()
           this.viewIsNews=true  
        },
        chginfo(){
          this.isInfoDialog=true
        },
        chgpwd(){
         this.isPwdDialog=true
        },
        hideDialog(){
        this.isPwdDialog=false
        this.isInfoDialog=false
        },
        infoChangeCfm(){
        this.alertContent="暂未实现哟"
        this.isAlertDialog=true
        },
        clearAllContent(){
        var contents=["oldPwd","newPwd1","newPwd2"];
        for(var content of contents)
        this[content]=""
        },
        pwdChangeCfm(){
        if(this.oldPwd==""||this.newPwd1==""||this.newPwd2==""){
            this.alertContent="请检查输入格式";
            this.isAlertDialog=true
        }
        else if(this.newPwd1!=this.newPwd2){
            this.alertContent="密码输入不一致";
            this.isAlertDialog=true
        }else{
       this.axios({
       method:'post',
       url:api.updateUserPwd,
       data:qs.stringify({oldpwd:this.oldPwd,newpwd:this.newPwd1}),
       headers:{authorize:tokenstore.getToken()}
       }).then(
           result=>{
             switch(result.data.status){
               case api.success:
               this.alertContent="更改成功"
               this.isAlertDialog=true
               this.isPwdDialog=false
               this.clearAllContent()
               break;
               case api.error:
               this.alertContent="更改失败,请重新更改"
               this.isAlertDialog=true
               this.clearAllContent()
               break;
             }
           }
       )

        }
        }
    }
}
</script>


<style>
.app-size-bar {
  position: relative;
  margin-left: 1%;
  width: 30%;
}
.app-content {
  position: relative;
  left: 5%;
  width: 60%;
}
.app {
  display: flex;
  flex-flow: row nowrap;
}
.app-dialog-content{
    widows: 100%;
}
.app-dialog-btn{
    width: 80% !important;
    margin-left: 10% !important;
}
</style>

