import 'package:flutter/material.dart';
import 'package:go_router/go_router.dart';
import 'package:session_front/common/widget/padding_factory.dart';
import 'package:session_front/presentation/page/login_page.dart';

class HomePage extends StatelessWidget {
  static String routeName = "/";
  const HomePage({super.key});


  @override
  Widget build(BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.stretch,
      mainAxisAlignment: MainAxisAlignment.center,
      children: [
        PaddingFactory.createAll(
          child: ElevatedButton(
              onPressed: () {
                context.pushNamed(LoginPage.routeName);
              },
              child: Text(
                "Go to login page",
                style: Theme.of(context).textTheme.bodyMedium,
              )),
        ),
      ],
    );
  }
}

