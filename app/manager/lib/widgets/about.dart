import 'package:flutter/material.dart';
import 'package:manager/constant/constant.dart';
Widget buildAboutPage(State state){

  return  Column(
      mainAxisAlignment: MainAxisAlignment.start,
      children: <Widget>[
        Center(
           child:Card(

           margin: EdgeInsets.all(10),
         color: Colors.white,
           shape: RoundedRectangleBorder(
           borderRadius: BorderRadius.all(Radius.circular(5))
         ),
           child: Column(
             mainAxisAlignment: MainAxisAlignment.center,
             children: <Widget>[
               Text(
                 SETING,
               style: TextStyle(
                 inherit: false,
                 fontSize: 50,
                 color: Colors.green,
               ),
               textAlign: TextAlign.center,
               ),
              Row(
                   children: <Widget>[
                     Icon(Icons.add),
                     Text("亮度"),
                     Slider(value: 0, onChanged: null,max: 100,)
                   ],
                 ),Row(
                 children: <Widget>[
                   Icon(Icons.add),
                   Text("亮度"),
                   Slider(value: 0, onChanged: null,max: 100,)
                 ],
               ),Row(
                 children: <Widget>[
                   Icon(Icons.add),
                   Text("亮度"),
                   Slider(value: 0, onChanged: null,max: 100,)
                 ],
               ),Row(
                 children: <Widget>[
                   Icon(Icons.add),
                   Text("亮度"),
                   Checkbox(value:true, onChanged: null,activeColor: Colors.green,)
                 ],
               )
             ],
           ),
         )),

         Card(
           margin: EdgeInsets.all(10),
           color: Colors.white,
           shape: RoundedRectangleBorder(
               borderRadius: BorderRadius.all(Radius.circular(5))
           ),
           child: Column(
             children: <Widget>[
               Text("图片",style:
               TextStyle(
                 inherit: false,
                 fontSize: 50, color: Colors.green,
               )
               ),
               Image(image: AssetImage("images/login.jpg"),width: 350,height:100,repeat: ImageRepeat.noRepeat,)
             ],
           )
         ),
         Card(
           margin: EdgeInsets.all(10),
           color: Colors.white,
           shape: RoundedRectangleBorder(
               borderRadius: BorderRadius.all(Radius.circular(5))
           ),
           child: Column(
             children: <Widget>[
               Text("说明",
               style: TextStyle(
                 inherit: false,
                 fontSize: 50, color: Colors.green,
               ),
               ),
               Text(

                 "一体化资源系统是XXXXXXXXXXXX\nXXXXXXXXXXXXXX",
                 textAlign: TextAlign.center,
                 style: TextStyle(
                   inherit: false,
                   fontSize: 20, color: Colors.green,
                 ),
               )
             ],
           )
         ),
      ],
    );
}