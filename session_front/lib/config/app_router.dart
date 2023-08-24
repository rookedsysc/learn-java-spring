import 'package:flutter/material.dart';
import 'package:go_router/go_router.dart';
import 'package:riverpod_annotation/riverpod_annotation.dart';
import 'package:session_front/presentation/page/home_page.dart';
import 'package:session_front/presentation/page/login_page.dart';

part 'app_router.g.dart';

@riverpod
GoRouter goRouter(GoRouterRef ref) {
  return GoRouter(
    routes: <RouteBase>[
      ShellRoute(
        builder: (BuildContext context, GoRouterState state, Widget child) {
          return Scaffold(body: child);
        },
        routes: [
          _homePage(routes: [
            _loginPage(routes: []),
          ]),
        ],
      ),
    ],
  );
}

GoRoute _homePage({required List<GoRoute> routes}) {
  return GoRoute(
    path: HomePage.routeName,
    name: HomePage.routeName,
    builder: (context, state) => const HomePage(),
    routes: routes,
  );
}
GoRoute _loginPage({required List<GoRoute> routes}) {
  return GoRoute(
    path: LoginPage.routeName,
    name: LoginPage.routeName,
    builder: (context, state) => const LoginPage(),
    routes: routes,
  );
}

