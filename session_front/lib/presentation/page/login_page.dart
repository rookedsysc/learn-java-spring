import 'package:flutter/material.dart';
import 'package:session_front/common/widget/input_form.dart';
import 'package:session_front/common/widget/padding_factory.dart';

final GlobalKey<FormState> _loginPageFormKey = GlobalKey<FormState>();

class LoginPage extends StatelessWidget {
  static String routeName = "login";

  const LoginPage({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(
          "Login Page",
          style: Theme.of(context).textTheme.bodyLarge,
        ),
        centerTitle: true,
      ),
      body: Form(
        key: _loginPageFormKey,
        child: SingleChildScrollView(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            crossAxisAlignment: CrossAxisAlignment.stretch,
            children: [
              _IdForm(),
              _PasswordForm(),
              PaddingFactory.createAll(
                child: ElevatedButton(
                    onPressed: () {},
                    child: Text(
                      "Save",
                      style: Theme.of(context).textTheme.bodyMedium,
                    )),
              ),
            ],
          ),
        ),
      ),
    );
  }
}

class _IdForm extends StatelessWidget {
  const _IdForm({super.key});

  @override
  Widget build(BuildContext context) {
    return PlainTextForm(
      validator: (value) {
        if (value!.isEmpty) {
          return "ID is empty";
        }
        return null;
      },
      title: "ID",
    );
    ;
  }
}

class _PasswordForm extends StatelessWidget {
  const _PasswordForm({super.key});

  @override
  Widget build(BuildContext context) {
    return PlainTextForm(
      validator: (value) {
        if (value!.isEmpty) {
          return "Password is empty";
        }
        return null;
      },
      title: "Password",
    );

  }
}

