import 'package:json_annotation/json_annotation.dart';

part 'login_request.g.dart';

@JsonSerializable()
class LoginRequest {
  final String id;
  final String password;

  LoginRequest({
    required this.id,
    required this.password,
  });

  copyWith({
    String? id,
    String? password,
  }) =>
      LoginRequest(
        id: id ?? this.id,
        password: password ?? this.password,
      );
}
