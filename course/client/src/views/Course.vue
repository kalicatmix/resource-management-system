<template>
    <div>
   <div class="app-container" v-for="(type,index) in data"  :key="index">
   <md-card class="app-card">
<md-card-header>
    <img class="app-card-avatar" src="@/assets/avatar.jpg"/>
</md-card-header>
<md-card-content>
    <h1 class="app-card-title">{{type.type}}</h1>
    <h1 class="app-card-content-text">
    {{type.choosed?"已选择本课":"未选择本课"}}
    </h1>
    <h1 class="app-card-content-text app-text-red">已有{{type.size}}人选择</h1>
</md-card-content>
<md-card-actions>
    <md-button class="app-btn md-primary md-raised" :disabled="type.choosed?true:false"><div :id="type.type" @click="onConfirmClick($event)">选择</div></md-button>
</md-card-actions>
   </md-card>
   </div>
    </div>
</template>
<script>
import tokenstore from '../util/tokenstore.js'
import api from "../util/api.js"
import qs from 'qs'
export default {
  beforeMount(){ 
       this.initView()
       },
       data(){
           return {
               data:[]
           }
       },
       methods:{
           initView(){

        var datas=[] 
        var types=[] 
        var myTypes=[]
        var size=[]
       //get types
       this.axios({
       method:'get',
       url:api.queryType,
       headers:{authorize:tokenstore.getToken()}
       }).then(
           result=>{
             switch(result.data.status){
               case api.success:
               var results=result.data.info.result
               results.forEach(element => {
                   types.push(element.name)
               });
               break;
               case api.error:
               break;
             }
        //get myTypes
        this.axios({
       method:'get',
       url:api.queryCourse,
       headers:{authorize:tokenstore.getToken()}
       }).then(
           result=>{
             switch(result.data.status){
               case api.success:
               var results=result.data.info.result
               results.forEach(element => {
                  myTypes.push(element.name)
               });
               break;
               case api.error:
               break;
             }
           
       //get size
       this.axios({
       method:'post',
       url:api.queryCourseSize,
       data:qs.stringify({courses:JSON.stringify(types)}),
       headers:{authorize:tokenstore.getToken()}
       }).then(
           result=>{
             switch(result.data.status){
               case api.success:
               var results=result.data.info.result
               results.forEach(element => {
                  size.push(element)
               });
               break;
               case api.error:
               break;
             }
             for(var index in types){
               var data={}
               data['type']=types[index]
               var isChoosed=false
               for(var myType of myTypes){
                 if(myType==types[index]){
                     isChoosed=true
                 }
               }
               data['choosed']=isChoosed
               data['size']=size[index]
               datas.push(data)
               this.data=datas
           }
             })
              //end size
           }
       )
       //end myTypes
           }
       )
       //end types
           },
     onConfirmClick(e){
       this.axios({
       method:'post',
       url:api.addCourse,
       data:qs.stringify({course:e.target.id}),
       headers:{authorize:tokenstore.getToken()}
       }).then(
           result=>{
             switch(result.data.status){
               case api.success:
               break;
               case api.error:
               break;
             }
            this.initView()
           }
       )
           }
       }
}
</script>
<style>
.app-card{
    position: relative;
    width: 30%;
    margin: 0.5rem;
    float: left;
}
.app-card-avatar{
    width: 100% !important;
    height: 5rem !important;
    border-radius: 0% !important;
    margin: 0.5rem;
}
.app-card-title{
    width:100%;
    text-align:center;
}
.app-container{
    display: block
}
.app-btn{
    width: 100% !important
}
.app-btn:hover{
    background: gray !important
}
.app-card-content-text{
    font-size: 1rem;
    text-align: center;
}
.app-text-red{
    color:#ff5252
}
</style>
