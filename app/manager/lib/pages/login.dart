import 'package:flutter/material.dart';
import 'package:manager/constant/constant.dart';
import 'package:manager/theme/textstyle.dart';
import 'package:manager/actions/login.dart';
class LogInPage extends StatefulWidget{
  @override
  _LogInPageState createState()=>_LogInPageState();
}
class _LogInPageState extends State<LogInPage>{
  TextEditingController userControllr,pwdController;
  FocusNode userFocusNode,pwdFocusNode;
  FocusScopeNode focusScopeNode;
  String text="";

  void _doLogIn(){
    if(focusScopeNode==null){
      focusScopeNode=FocusScope.of(context);
    }
    String user=userControllr.text,pwd=pwdController.text;
    if(user==""){
      focusScopeNode.requestFocus(userFocusNode);
    }else if(pwd==""){
      focusScopeNode.requestFocus(pwdFocusNode);
    }else{
      if(login(user, pwd)){
        Navigator.pushNamed(context, MAINPAGE);
      }else{
       userControllr.clear();
       pwdController.clear();
       setState(() {
         text=HINT_ERROR;
       });
      }
    }

  }

  _LogInPageState(){
    userControllr=TextEditingController();
    pwdController=TextEditingController();
    userFocusNode=FocusNode();
    pwdFocusNode=FocusNode();
  }
  @override
  Widget build(BuildContext context) {
    TextStyle defaultStyle=buildTextStyle(Colors.white);
   return Scaffold(
     body: Column(
       children: <Widget>[
         Image(
           image: AssetImage("images/login.jpg"),
            width: 200,
           height: 200,
         ),
         Center(
           widthFactor: 100,
           child: Column(
             children: <Widget>[
               TextField(
                 maxLength: 10,
                 controller: userControllr,
                 focusNode: userFocusNode,
                 decoration: InputDecoration(
                     prefixIcon:Icon(Icons.account_circle),
                     labelText: USER,
                     hintText: HINT_USER
                 ),
               ),
               TextField(
                 maxLength: 10,
                 controller: pwdController,
                 focusNode: pwdFocusNode,
                 decoration: InputDecoration(
                   prefixIcon: Icon(Icons.access_time),
                   labelText: PWD,
                   hintText:HINT_PWD
                 ),
               ),
               Text(text,style: buildTextStyle(Colors.red),),
               FlatButton(
                   padding: EdgeInsets.only(left: 100,right: 100),
                   onPressed:_doLogIn, child: Text(LOGIN),color:Colors.white,textColor: Colors.green,
                   shape:RoundedRectangleBorder(borderRadius:BorderRadius.circular(10)))
             ],
           ),
         )
       ],
     ),
   );
  }
}