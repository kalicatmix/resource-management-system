<%@page import="com.manager.model.Item"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.manager.model.Admin"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
<head>
<meta charset="UTF-8"/>
<meta name="keywords" content="校园管理,资源管理,管理系统"/>
<meta name='description' content="校园一体化资源管理系统：.......">
<title>校园一体化资源管理系统</title>
<script type="text/javascript" src="static/layui.all.js"></script>
<script type="text/javascript" src="static/jquery3.2.1.min.js"></script>
<link rel="stylesheet" href="static/css/layui.css" />
<style type="text/css"> 
html,body{
height:100%;
width:100%;
font-size:15px;
}
@media screen and (max-width:768px) {
html,body{
font-size:10px;
}
}
@media screen and(min-width:1500px){
html,body{
font-size:30px;
}
}
.max-width{
width:100%;
height:auto;
}
.nav-bar{
position:fixed;
left:0;
width:20%;
float:left;
}
.nav-bar-avar{
height:40%;
width:100%;
margin-left:0%;
}
.nav-bar-avar-font{
font-size:2rem;
text-align:center;
}
.nav-bar-font{
font-size:1.5rem;
text-align:center;
}
.text-center{
text-align:center;
}
.nav-content{
position:relative;
left:20%;
width:80%;
height:auto;
}
.about-dialog,.change-dialog{
display:none;
}
.change-dialog{
width:18rem;
height:18rem;
}
.content-card{
width:95%;
margin:2%;
border-style:solid;
border-color:#009688;
border-radius:0.5rem;
border-width:0.2rem;
box-shadow:0.2rem 0.2rem #009688;
}
.content-card-title{
text-align:center;
text-shadow:0.1rem 0.1rem black;
font-size:3rem;
}
.content-card-panel{
display:flex;
flex-direction:row;
flex-wrap:wrap;
justify-content:center;
}
.content-card-item{
width:50%;
display:inline-block
}
.content-card-img img{ 
float:left;
width:50%;
height:20%;
maring:0.5rem;
}
.content-card-item-content{
font-size:0.5rem;
text-align:center;
}
</style>
<%!
Admin admin;
String imgs[]={
"static/images/book.jpg",		
"static/images/chair.jpg",		
"static/images/chalk.jpg",		
"static/images/classroom.jpg",		
"static/images/computer.jpg",		
"static/images/desktop.jpg",		
"static/images/notepad.jpg",		
"static/images/pen.jpg",				
};
String names[]={
		"书",
		"椅子",
		"粉笔",
		"教室",
		"电脑",
		"桌子",
		"笔记本",
		"笔"
};
ArrayList<Item>items;
%>
<%
admin=(Admin)session.getAttribute("user");
if(admin==null){
	response.sendError(404);
}else{
	items=(ArrayList<Item>)request.getAttribute("items");
}
%>
<script type="text/javascript">
var layer,form,items=[];
$(window).ready(function(){
layer=layui.layer;
form=layui.form;
//transform
<%for(Item item:items){
	%>
	items.push('<%=item.getType()%>');
	<%}%>
	
form.on("submit(change)",function(data){
	var field=data.field;
	if(field.newpwd!=field.newpwd1){
		layer.msg("密码不一致");
	}else{
		$.post("web/password/change",{pwd:field.pwd,changeword:field.changeword,newpwd:field.newpwd},function(data){
			if(data.message==0){
				layer.msg("更改失败，请检查密码和注册码");
			}else{
				layer.msg("更改成功");
				$("#changeForm").get(0).reset();
			}
		});
	}
	
	return false;
})
})
function reduce(id){
	layer.confirm("确定减少资源",function(index){
		layer.close(index);
		var target=$('#'+id);
		var number=parseInt(target.text())-1;
		if(number>0){
		var type=items[parseInt(id.slice(-1))];
		var url="api/item/change/"+type+"/"+number;
		$.post(url,{},function(data){
			if(data.message===0){
				layer.msg("更改失败");
			}
			layer.close(index);
			target.text(number);
			})	
			
		}else{
			layer.msg("不能在减低标准了");
		}
		
	});
}
function increase(id){
	layer.confirm("增加资源",function(index){
		layer.close(index);
		var index=layer.load();
		var target=$('#'+id);
		var number=parseInt(target.text())+1;
		var type=items[parseInt(id.slice(-1))];
		var url="api/item/change/"+type+"/"+number;
		$.post(url,{},function(data){
			if(data.message===0){
				layer.msg("更改失败");
			}
		   layer.close(index);
		   target.text(number);
		})
		
	})
}
function showAbout(){
	layer.open({
		title:"关于",
		type:1,
		shadeClose:false,
		scrollbar:false,
		content:$('#about'),
		anim:6,
		resize:false,
		area:['20rem','20rem'],
		cancel:function(){
			$('#about').hide();
		}
		});
}
function showChange(){
	layer.open({
		title:"密码更改",
		type:1,
		shadeClose:false,
		scrollbar:false,
		content:$('#change'),
		anim:6,
		resize:false,
		area:['20rem','20rem'],
		cancel:function(){
			$('#change').hide();
		}
		});
}
</script>
</head>

<body>
<div>
<ul class="layui-nav layui-nav-tree layui-nav-side nav-bar">
  <li class="layui-nav-item">
   <img class="nav-bar-avar layui-anim layui-anim-rotate " src="static/images/login.jpg"/>
   <h1 class="nav-bar-avar-font"><%=admin.getUser() %></h1>
   <h2 class="text-center" >密码签名</h1>
   <h2 class="text-center"><%=admin.getPwdmd5()%></h2>
  </li>
  <li class="layui-nav-item">
  <a class="nav-bar-font" href="javascript:layer.msg('暂未实现')">添加资源</a>
  </li>
  <li class="layui-nav-item">
    <a class="nav-bar-font" href="javascript:showChange()">更改密码</a>
  </li>
  <li class="layui-nav-item"><a class="nav-bar-font" href="javascript:showAbout()">关于</a></li>
</ul>
</div>
<div class="nav-content">
<div class="content-card">
<h3 class="content-card-title">原始资源</h3>
<div class="content-card-panel">
 <%
 for(int i=0;i<imgs.length;i++){
 %>
 <div class="content-card-item">
  <div class="content-card-img">
  <img src="<%=imgs[i]%>"/>
  </div>
  <div class="content-card-item-content">
  <h1><%=names[i]%></h1>
  <h1>数量：</h1><h1 id='number<%=i%>'><%=items.get(i).getNumber()%></h1>
  <button class="layui-btn layui-btn-normal" onclick="increase('number<%=i%>')">增加</button>
  <button class="layui-btn layui-btn-danger" onclick="reduce('number<%=i%>')">减少</button>
  </div>
 </div>
 <%} %>
</div>
</div>

<div class="content-card">
<h3 class="content-card-title">新增资源</h3>
</div>
</div>
</body>
<div id="about" class="about-dialog">
<h5 class="text-center">校园管理,资源管理,管理系统</h1>
 <p>
       校园一体化资源管理系统：.......
       这是一个系统XXXXXXXXXXXXXXXXXXX
 </p>
 <h5 class="text-center">V1.0</h5>
</div>

<div id="change" class="change-dialog">
<form id="changeForm" class="layui-form">

<div class="layui-form-item">
<label class="layui-form-label">原密码</label>
<div class="layui-input-block">
<input type="password" name="pwd" class="layui-input" placeholder="请输入密码" required lay-verify="required" pattern="[a-z0-9]{6,10}" />
</div>
</div>

<div class="layui-form-item">
<label class="layui-form-label">注册码</label>
<div class="layui-input-block">
<input type="password" name="changeword" class="layui-input" placeholder="请输入注册码" required lay-verify="required" pattern="[0-9]{6,10}" />
</div>
</div>

<div class="layui-form-item">
<label class="layui-form-label">新密码</label>
<div class="layui-input-block">
<input type="password" name="newpwd" class="layui-input" placeholder="请输入新密码" required lay-verify="required" pattern="[0-9]{6,10}" />
</div>
</div>

<div class="layui-form-item">
<label class="layui-form-label">新密码</label>
<div class="layui-input-block">
<input type="password" name="newpwd1" class="layui-input" placeholder="再次输入新密码" required lay-verify="required" pattern="[0-9]{6,10}" />
</div>
</div>

<div class="layui-form-item">
<div class="layui-input-block">
<button  class="layui-btn max-width" lay-submit lay-filter="change">更改</button>
</div>
</div>
</form>
</div>
</html>