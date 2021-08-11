import 'dart:ui';

import 'package:flutter/material.dart';

void main() {
  runApp(MaterialApp(home: idcard()));
}

class idcard extends StatefulWidget {
  const idcard({Key? key}) : super(key: key);

  @override
  _idcardState createState() => _idcardState();
}

class _idcardState extends State<idcard> {
  int counter = 0;
  String text = "";

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.grey[900],
      appBar: AppBar(
        title: Text('The ID application'),
        centerTitle: true,
        backgroundColor: Colors.grey[850],
        elevation: 0.0,
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          if (counter < 10) {
            setState(() {
              counter++;
              if (counter > 6) {
                text = "Thanx!";
              }
            });
          }
        },
        child: Icon(Icons.add),
        backgroundColor: Colors.grey[800],
      ),
      body: Padding(
        padding: EdgeInsets.fromLTRB(30.0, 40.0, 30.0, 0.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: <Widget>[
            Center(
                child: CircleAvatar(
              backgroundImage: AssetImage('assets/ui.jpg'),
              radius: 50.0,
            )),
            SizedBox(height: 60.0),
            Text(
              'Name',
              style: TextStyle(
                color: Colors.grey,
                letterSpacing: 1.0,
              ),
            ),
            SizedBox(
              height: 15.0,
            ),
            Text(
              'Aaron Mathew',
              style: TextStyle(
                  color: Colors.white,
                  letterSpacing: 2.0,
                  fontSize: 20.0,
                  fontWeight: FontWeight.bold),
            ),
            SizedBox(height: 30.0),
            Text(
              'Current flutter level',
              style: TextStyle(
                color: Colors.grey,
                letterSpacing: 1.0,
              ),
            ),
            SizedBox(
              height: 15.0,
            ),
            Text(
              '0.10%',
              style: TextStyle(
                  color: Colors.white,
                  letterSpacing: 2.0,
                  fontSize: 20.0,
                  fontWeight: FontWeight.bold),
            ),
            SizedBox(height: 30.0),
            Row(
              children: <Widget>[
                Icon(
                  Icons.email,
                  color: Colors.grey[400],
                ),
                SizedBox(
                  width: 10.0,
                ),
                Text(
                  'aaronstone11.2001@gmial.com',
                  style: TextStyle(
                      color: Colors.grey[400],
                      fontSize: 15.0,
                      letterSpacing: 1.0),
                ),
              ],
            ),
            SizedBox(
              height: 60.0,
            ),
            Center(
              child: Text('RATE MY PROFILE',
                  style: TextStyle(
                    color: Colors.white,
                    letterSpacing: 1.0,
                  )),
            ),
            SizedBox(height: 10.0),
            Center(
                child: Text('$counter',
                    style: TextStyle(
                      color: Colors.red[300],
                      fontSize: 68.0,
                    ))),
            Center(
                child: Text('$text',
                    style: TextStyle(
                      color: Colors.red[300],
                      fontSize: 15.0,
                    ))),
            SizedBox(height: 37.0),
            ElevatedButton(
                onPressed: () {
                  setState(() {
                    counter = 0;
                  });
                },
                child: Text('Reset'),
                style: ElevatedButton.styleFrom(primary: Colors.grey[800]))
          ],
        ),
      ),
    );
  }
}
