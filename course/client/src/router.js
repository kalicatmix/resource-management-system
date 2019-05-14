import Vue from 'vue'
import Router from 'vue-router'
import Login from './views/Login.vue'
import About from './views/About.vue'
import Reg from './views/Reg.vue'
import Manager from './views/Manager.vue'
import Home from './views/Home.vue'

import MyCourse from './views/MyCourse.vue'
import Course from './views/Course.vue'
import News from './views/News.vue'

Vue.use(Router)

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      components:{
        index:Home,
        subhome:Login
      }
    }  
    ,{
      path: '/about',
      name: 'about',
      components:{
        index:Home,
        subhome:About
      }
    },{
      path: '/reg',
      name: 'reg',
      components:{
        index:Home,
        subhome:Reg
      }
    },{
     path:'/index',
     name: 'index',
     components:{
       index:Manager
     }
    }
  ]
})
