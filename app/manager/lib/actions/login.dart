import 'package:flutter/material.dart';
import 'package:manager/api/api.dart';
import 'package:manager/constant/constant.dart';
bool login(String user,String pwd){
  var data={user:user,pwd:pwd};
  ApiManager().post(API_USER,data).then((res){
    //pass
    return true;
  }).catchError((){
    return false;
  });
}