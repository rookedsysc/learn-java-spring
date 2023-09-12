import 'package:flutter/material.dart';
import 'package:session_front/common/widget/padding_factory.dart';

class PlainTextForm extends StatelessWidget {
  final String? hintText;
  final FormFieldValidator<String?>? validator;
  final FormFieldSetter<String>? onSaved;
  final GestureTapCallback? onTap;
  final ValueChanged<String>? onChanged;
  final bool isPassword;
  final String title;

  const PlainTextForm(
      {this.onTap,
      this.title = "",
      this.isPassword = false,
      this.onChanged,
      this.validator,
      this.onSaved,
      this.hintText,
      super.key});

  @override
  Widget build(BuildContext context) {
    return PaddingFactory.createAll(
      child: TextFormField(
        style: Theme.of(context).textTheme.bodyMedium,
        keyboardType: TextInputType.text,
        obscureText: isPassword,
        textAlign: TextAlign.right,
        validator: validator,
        onSaved: onSaved,
        onChanged: onChanged,
        autovalidateMode: AutovalidateMode.onUserInteraction,
        autofocus: true,
        onTap: onTap,
        decoration: InputDecoration(
          label: Text(
            title,
            style: Theme.of(context).textTheme.bodyMedium,
          ),
          labelStyle: Theme.of(context).textTheme.bodySmall,
          enabledBorder: _enabledBorder(context),
          focusedBorder: _focusedBorder(context),
          hintStyle: Theme.of(context).textTheme.bodyMedium,
          hintText: hintText,
        ),
      ),
    );
  }

  OutlineInputBorder _enabledBorder(BuildContext context) => OutlineInputBorder(
        borderSide: BorderSide(
          color: Theme.of(context).primaryColorLight,
        ),
      );

  OutlineInputBorder _focusedBorder(BuildContext context) => OutlineInputBorder(
        borderSide: BorderSide(
          color: Theme.of(context).primaryColor,
        ),
      );
}
