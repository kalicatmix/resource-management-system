<template>
    <div>
    <md-card>
   <md-card-header>
   <h1 class="app-card-title">选课情况</h1>
   <img class="app-card-avatar" src="@/assets/avatar.jpg"/>
   </md-card-header>
    <md-card-content>
   <h1>截至{{date}}你已选择了{{courses.length}}门课</h1>
    </md-card-content>
    </md-card>
   <div class="app-container" v-for="course in courses"  :key="course">
   <md-card class="app-card">
<md-card-header>
    <img class="app-card-avatar" src="@/assets/avatar.jpg"/>
</md-card-header>
<md-card-content>
    <h1 class="app-card-title">{{course}}</h1>
</md-card-content>
<md-card-actions class="app-max-width">
    <md-button  class="app-max-width md-accent md-raised">
      <div :id="course" @click.prevent="rollBack($event)" >退选</div></md-button>
</md-card-actions>
   </md-card>

   <md-dialog-alert
   :active.sync="isAlert"
   :md-content.sync="alertContent" md-confirm-text="知道了"
   />
   </div>
    </div>

</template>
<script>
import tokenstore from '../util/tokenstore.js'
import api from "../util/api.js"
import qs from "qs"
import { setInterval } from 'timers'

export default {
    
  beforeMount(){ 
       this.initView()
       requestAnimationFrame(this.playTimer)
       },
       data(){
         return {
           courses:[],
           date:"",
           isAlert:false,
           alertContent:""
         }
       },
       methods:{
         initView(){
       this.axios({
       method:'get',
       url:api.queryCourse,
       headers:{authorize:tokenstore.getToken()}
       }).then(
           result=>{
             switch(result.data.status){
               case api.success:
              var coursesTemp=[]
               var results=result.data.info.result
               results.forEach(element => {
                   coursesTemp.push(element.name)
               });
               this.courses=coursesTemp
               break;
               case api.error:
               break;
             }
           }
       )
         },
         rollBack(e){
         this.axios({
       method:'post',
       url:api.delCourse,
       data:qs.stringify({course:e.target.id}),
       headers:{authorize:tokenstore.getToken()}
       }).then(
           result=>{
             switch(result.data.status){
               case api.success:
               this.initView()
               break;
               case api.error:
               this.alertContent="删除失败"
               this.isAlert=true
               break;
             }
           }
       )


         },
         playTimer(){
           this.date=new Date().toLocaleString("zh")
           requestAnimationFrame(this.playTimer)
         }
       }
}
</script>
<style scoped>
.app-max-width{
min-width: 100% !important;
}
.app-max-width:hover{
  background:gray !important;
}
</style>

