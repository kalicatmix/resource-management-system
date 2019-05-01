import 'package:flutter/material.dart';
Widget buildHomePage(){
  return  Center(
    // Center is a layout widget. It takes a single child and positions it
    // in the middle of the parent.
    child: Column(
      mainAxisAlignment: MainAxisAlignment.center,
      children: <Widget>[
        Text(
          'page home',
        ),
      ],
    ),
  );

}