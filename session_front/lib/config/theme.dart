import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';


abstract class ThemeBase {}

const PRIMARY_COLOR = Colors.green;

class ThemeImpl {
  final ThemeBase themeType;

  ThemeImpl(this.themeType);

  ThemeData data() {

    return ThemeData(
      primaryIconTheme: const IconThemeData(color: PRIMARY_COLOR),
      cupertinoOverrideTheme: CupertinoThemeData(
        textTheme: CupertinoTextThemeData (
          dateTimePickerTextStyle: TextStyle(
            color: _defaultColor(),
            fontSize: 20,
            fontWeight: FontWeight.w900,
          ),
        ),
      ),
      scaffoldBackgroundColor: _scaffoldColor(),
      canvasColor: _scaffoldColor(),
      iconTheme: const IconThemeData(color: PRIMARY_COLOR),
      cardColor: _cardColor(),
      primaryColor: PRIMARY_COLOR,
      primaryColorLight: const Color.fromRGBO(89, 171, 225, 1),
      appBarTheme: AppBarTheme(color: _scaffoldColor(), elevation: 0),
      elevatedButtonTheme: ElevatedButtonThemeData(
        style: ButtonStyle(
          fixedSize: MaterialStateProperty.all(const Size(200, 55)),
        ),
      ),
      iconButtonTheme: IconButtonThemeData(
        style: ButtonStyle(
          iconColor: MaterialStateColor.resolveWith(
            (states) => PRIMARY_COLOR,
          ),
        ),
      ),

      // text
      fontFamily: 'Korail',
      textTheme: TextTheme(
        bodyLarge: TextStyle(
            color: _defaultColor(), fontSize: 24, fontWeight: FontWeight.w900),
        bodyMedium: TextStyle(color: _defaultColor(), fontSize: 14),
        bodySmall: TextStyle(color: _defaultColor(), fontSize: 10),
      ),
    );
  }

  Color _defaultColor() {
    if (themeType is DarkTheme) {
      return Colors.white;
    } else {
      return Colors.black;
    }
  }

  Color _scaffoldColor() {
    if (themeType is DarkTheme) {
      return const Color.fromRGBO(18, 18, 18, 1);
    } else {
      return const Color.fromRGBO(244, 243, 248, 1);
    }
  }

  Color _cardColor() {
    if (themeType is DarkTheme) {
      return const Color.fromRGBO(30, 30, 30, 1);
    } else {
      return const Color.fromRGBO(255, 255, 255, 1);
    }
  }
}

class LightTheme implements ThemeBase {
  static ThemeData get() => ThemeImpl(LightTheme()).data();
}

class DarkTheme implements ThemeBase {
  static ThemeData get() => ThemeImpl(DarkTheme()).data();
}
