import 'package:flutter/material.dart';
import 'package:manager/constant/constant.dart';
import 'package:manager/theme/themes.dart';
import 'package:manager/pages/login.dart';
import 'package:manager/var/var.dart';
import 'package:manager/pages/home.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState()=>_MyAppState();
}
class _MyAppState extends State<MyApp>{
  _MyAppState(){
    appState=this;
    themeData=buildTheme(Colors.green);
  }
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title:TITLE,
      theme: themeData,
      home:LogInPage(),
      routes:{MAINPAGE:(context)=>MyHomePage(title:TITLE)},
    );
  }
}
