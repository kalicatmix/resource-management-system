import 'package:flutter/material.dart';
import 'package:manager/theme/textstyle.dart';
import 'package:manager/widgets/home.dart';
import 'package:manager/widgets/about.dart';
import 'package:manager/constant/constant.dart';
import 'package:manager/var/var.dart';
import 'package:manager/theme/themes.dart';
class MyHomePage extends StatefulWidget {
  MyHomePage({Key key, this.title}) : super(key: key);
  final String title;
  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  Widget HOME_WIDGET;
  Widget ABOUT_WIDGET;
  Widget body;
  _MyHomePageState(){
    HOME_WIDGET=buildHomePage(this);
    ABOUT_WIDGET=buildAboutPage(this);
    body=HOME_WIDGET;
  }
  //改变主题
  void _changeTheme(Color color){
    appState.setState((){themeData=buildTheme(color);});
  }
  void _showThemeChooser(){
    showDialog(context: context,builder: (BuildContext context){
      return SimpleDialog(
        title: Text(THEME_CHOOSE),
        children: <Widget>[
          SimpleDialogOption(
              child:Row(
                children: <Widget>[Text(THEME_BLUE,style: buildTextStyle(Colors.blue))],
              ),
              onPressed:()=>_changeTheme(Colors.blue)
          ),
          SimpleDialogOption(
              child:Row(
                children: <Widget>[Text(THEME_GREEN,style: buildTextStyle(Colors.green))],
              ),
              onPressed:()=>_changeTheme(Colors.green)
          ),
          SimpleDialogOption(
              child:Row(
                children: <Widget>[Text(THEME_Red,style: buildTextStyle(Colors.red))],
              ),
              onPressed:()=>_changeTheme(Colors.red)
          ),
          SimpleDialogOption(
              child:Row(
                children: <Widget>[Text(THEME_YELLOW,style: buildTextStyle(Colors.yellow))],
              ),
              onPressed:()=>_changeTheme(Colors.yellow)
          ),
        ],
      );
    });
  }


  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: body,
      floatingActionButton: FloatingActionButton(
        onPressed:_showThemeChooser,
        tooltip: FLOATBUTTON_TOOL_TIP,
        child: Icon(Icons.color_lens),
      ),
      floatingActionButtonLocation: FloatingActionButtonLocation.centerDocked,
      bottomNavigationBar: BottomAppBar(

        child: Row(
          mainAxisAlignment: MainAxisAlignment.spaceAround,
          children: <Widget>[
            IconButton(
              icon: Icon(Icons.home),
              tooltip: HOME,
              onPressed:(){
                setState(() {
                  body=HOME_WIDGET;
                });
              },
            ),
            SizedBox(
            ),
            IconButton(
              icon: Icon(Icons.account_balance),
              tooltip: ABOUT,
              onPressed: (){
                setState(() {
                  body=ABOUT_WIDGET;
                });
              },
            )
          ],
        ),
      ),
    );
  }
}
