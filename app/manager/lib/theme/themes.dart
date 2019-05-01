import 'package:flutter/material.dart';
ThemeData buildTheme(Color color){
  return ThemeData(
    primaryColor: color,
    primarySwatch: color,
    buttonColor: color,
    hintColor: color,
    dialogBackgroundColor:Colors.white,
  );
}