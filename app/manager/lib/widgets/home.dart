import 'package:flutter/material.dart';
import 'package:manager/var/var.dart';
import 'package:manager/api/api.dart';
import 'package:manager/constant/constant.dart';
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
            showDialog(context: context,builder: (context){
              String result;
              ApiManager().get(SERVER_ADRESS+API_ITEM+items[index]['name']+TOKEN).then((res){result=res;});
              return Dialog(child: Text(result));
            });
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
