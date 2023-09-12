import 'package:flutter/material.dart';

class PaddingFactory {
  static Padding createHorizontal({required Widget child}) {
    return Padding(
      padding: const EdgeInsets.symmetric(horizontal: 16),
      child: child,
    );
  }

  static Padding createAll({required Widget child}) {
    return Padding(
      padding: const EdgeInsets.symmetric(horizontal: 16, vertical: 16),
      child: child,
    );
  }

  static Padding createSmallAroundAll({required Widget child}) {
    return Padding(
      padding: const EdgeInsets.symmetric(horizontal: 8, vertical: 8),
      child: child,
    );
  }
}
