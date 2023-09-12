import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:session_front/config/app_router.dart';
import 'package:session_front/config/theme.dart';

void main() {
  runApp(const ProviderScope(child: App()));
}

class App extends ConsumerWidget {
  const App({super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final router = ref.watch(goRouterProvider);
    return MaterialApp.router(
          debugShowCheckedModeBanner: false,
          theme: LightTheme.get(),
          darkTheme: DarkTheme.get(),
          routerConfig: router,
    );
  }
}