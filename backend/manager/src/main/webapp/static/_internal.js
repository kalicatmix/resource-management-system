function VerificationDrawer(){
this.canvas=document.createElement("canvas");
this.createCanvas=function(config){
	this.config=undefined==config?{numberSize:4,width:100,height:50}:config;
	this.canvas.setAttribute("id","canvas");
	this._randomNumbers();
    this._randomLines();
    this._randomPoints();
    this.draw();
    return this.canvas;
}
this._randomNumbers=function(){
	this.numbers=[];
	for(i=0;i<this.config.numberSize;i++){
		this.numbers.push({number:Math.floor(Math.random()*10),color:{R:this._randomNumberWithMax(255),G:this._randomNumberWithMax(255),B:this._randomNumberWithMax(255)}});
	}
}
this._randomLines=function(){
	this.lines=[];
	random=this._randomNumberWithMax;
	config=this.config;
	for(i=0;i<5;i++){
		this.lines.push({x:random(config.width),y:random(config.height),X:random(config.width),Y:random(config.height),color:{R:this._randomNumberWithMax(255),G:this._randomNumberWithMax(255),B:this._randomNumberWithMax(255)}});
	}
}
this._randomPoints=function(){
	var width=this.config.width;
	var height=this.config.height;
    this.points=[];
    for(i=0;i<30;i++){
    	this.points.push({point:{X:this._randomNumberWithMax(width),Y:this._randomNumberWithMax(height)},color:{R:this._randomNumberWithMax(255),G:this._randomNumberWithMax(255),B:this._randomNumberWithMax(255)}});
    }
    
}
this._randomNumberWithMax=function(max){
	return Math.floor(Math.random()*max);
}
this._rgbToColor=function(r,g,b){
	var rgb=`rgb(${r},${g},${b})`;
	return rgb;
}
this.getNumbers=function(){
	var str="";
	this.numbers.forEach(function(item){
		str+=item.number;
	})
	return str;
}
this.reDraw=function(){
  this._randomNumbers();
  this._randomPoints();
  this._randomLines();
  this.draw();
}
this.draw=function(){
	   var self=this;
	   var context=this.canvas.getContext('2d');
	   context.fillStyle="white";
	   context.fillRect(0,0,this.config.width,this.config.height);
	   
	   this.points.forEach(
	   function(item,_,_){
		var colors=item.color;
		var points=item.point;
		context.beginPath();
		context.moveTo(points.X,points.Y);
		context.arc(item.point.X,item.point.Y,1,0,2*Math.PI);
		context.stroke();
		context.fillStyle=self._rgbToColor(colors.R,colors.G,colors.B);
		context.fill();
	   });
	   
	   this.lines.forEach(
	   	function(item,_,_){
	   		var colors=item.color;
	   		context.beginPath();
	   		context.moveTo(item.x,item.y);
	   		context.lineTo(item.X,item.Y);
	   		context.strokeStyle=self._rgbToColor(colors.R,colors.G,colors.B);
	   		context.stroke();
	   	}   
	   )
	   
	   this.numbers.forEach(
	    function(item,index,_){
				var colors=item.color; 
				context.font=`${self.config.width/self.config.numberSize}px Verdana`;
				context.strokeStyle=self._rgbToColor(colors.R,colors.G,colors.B);
				context.strokeText(item.number,index*20,self.config.height/2);
			 } 
			   );
	   
	}
}
