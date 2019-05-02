import 'package:flutter/material.dart';
import 'package:manager/var/var.dart';

Image buildItemImage(String path){
  return Image(
    fit: BoxFit.cover,
    image: AssetImage(path),
    width: 350,
    height: 100,
    repeat: ImageRepeat.noRepeat,
  );
}
Widget buildHomePage(state){
  TextStyle defaultStyle=TextStyle(
    inherit: false,
    fontSize: 30,
    color: Colors.black
  );
  ScrollController controller=ScrollController();
  return ListView.builder(
       itemCount: items.length,
      controller:controller ,
      itemBuilder:(context,index){
        return Container(
         child: Card(
           elevation: 1.5,
           color: themeData.primaryColor,
           margin: EdgeInsets.all(2),
           child: GestureDetector(
           onTap: (){
             print("$index is taped");
           },
           child: Column(
           children: <Widget>[
        Text(items[index]["name"],style: defaultStyle),
        buildItemImage(items[index]["img"])
        ],
        )
        ))
        );
      }
  );

}
