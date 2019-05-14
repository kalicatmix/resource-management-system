<template>
  <div>
    <md-card>
      <md-card-header>
        <md-card-header-text>
          <div class="md-title">最新大事</div>
          <div class="md-subhead">xxxx同学获得了大奖.......</div>
        </md-card-header-text>

        <md-card-media>
         <md-avatar> <img src="@/assets/avatar.jpg" alt="loading"/>
         </md-avatar>
        </md-card-media>
      </md-card-header>

    </md-card>

    <md-card>
      <md-card-header>
        <md-card-header-text>
          <div class="md-title">某某课程已经选了......</div>
        </md-card-header-text>
      </md-card-header>
     <md-card-content>
大学的课程按大类来说一般分为必修课和选修课。必修一般指学校或院系规定学生必须修习某课程，学校对必修课程一般有统一的要求和安排。选修是指根据学生个人兴趣或专业需要自由选择修习某课程。简言之，必修就是必须修读，选修就是选择性修读。一般来说，基础性的知识都作为必修课程。有些知识不是基础性的，与兴趣和研究方向有关，这部分知识可以选择。这是大学与中学最大的不同之处。
     </md-card-content>
      <md-card-actions>
        <md-button><md-icon md-src="/img/head.svg"/>赞</md-button>
        <md-button><md-icon md-src="/img/number.svg"/>踩</md-button>
      </md-card-actions>
    </md-card>

    <md-card>
      <md-card-header>
        <md-card-header-text>
          <div class="md-title">选课知识</div>
        </md-card-header-text>
      </md-card-header>
      <md-card-content>
          <div class="app-box">
          <img class="app-img" src="/img/bg.jpg"/>
          <p>跨院系选修课，从字面上讲，就是学生选择非本专业的课程进行修读。</p>
          </div>
      </md-card-content>
    </md-card>
  </div>
</template>

<style>
  .md-card {
    margin: 0.5rem;
    width: 100%;
    max-height:10%;
  }
  .app-box{
      display:inline-block;
      position: relative;
  }
  .app-img{
      height: 10rem !important;
      float: left;
  }
  p{
      float: left;
  }
</style>
