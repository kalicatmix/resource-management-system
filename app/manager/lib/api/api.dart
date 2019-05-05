import 'dart:async';
import "package:dio/dio.dart";
class ApiManager{
  Dio dio;
  ApiManager(){
    this.dio=Dio();
  }
  Future<String>post(url,Map<String,dynamic> data) async{
    Response response = await dio.post(url,queryParameters: data);
    if(response.statusCode==200)
      return response.data;
    else
     return "";
  }
  Future<String>get(url) async{
    Response response =await dio.get(url);
    if(response.statusCode==200)
      return response.data;
    else
      return "";
  }
}
