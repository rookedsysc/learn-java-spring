import 'package:flutter/material.dart';
import 'package:go_router/go_router.dart';
import 'package:riverpod_annotation/riverpod_annotation.dart';
import 'package:session_front/presentation/page/header_login_page.dart';
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
          _loginPage(routes: [_headerLoginPage(routes: [])]),
        ],
      ),
    ],
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

GoRoute _headerLoginPage({required List<GoRoute> routes}) {
  return GoRoute(
    path: HeaderLogin.routeName,
    name: HeaderLogin.routeName,
    builder: (context, state) => const HeaderLogin(),
    routes: routes,
  );
}
