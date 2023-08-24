import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:session_front/common/widget/input_form.dart';
import 'package:session_front/common/widget/padding_factory.dart';
import 'package:session_front/controller/provider/login_request_provider.dart';
import 'package:session_front/controller/service/auth_service.dart';
import 'package:session_front/model/login_request_dto.dart';

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
        child: const SingleChildScrollView(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            crossAxisAlignment: CrossAxisAlignment.stretch,
            children: [
              _IdForm(),
              _PasswordForm(),
              _LoginButton(),
            ],
          ),
        ),
      ),
    );
  }
}

class _IdForm extends ConsumerWidget {
  const _IdForm({super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    return PlainTextForm(
      onSaved: (newValue) {
        ref.read(loginRequestProvider.notifier).input(id: newValue!);
      },
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

class _PasswordForm extends ConsumerWidget {
  const _PasswordForm({super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    return PlainTextForm(
      onSaved: (newValue) {
        ref.read(loginRequestProvider.notifier).input(password: newValue!);
      },
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

class _LoginButton extends ConsumerWidget {
  const _LoginButton({super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    return PaddingFactory.createAll(
      child: ElevatedButton(
          onPressed: () {
            if (_loginPageFormKey.currentState!.validate()) {
              _loginPageFormKey.currentState!.save();
              final LoginRequestDto request = ref.read(loginRequestProvider);
              ref.read(authServiceProvider).login(request);
            }
          },
          child: Text(
            "Login",
            style: Theme.of(context).textTheme.bodyMedium,
          )),
    );
  }
}
