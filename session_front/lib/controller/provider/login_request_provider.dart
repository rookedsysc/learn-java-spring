import 'package:riverpod_annotation/riverpod_annotation.dart';
import 'package:session_front/model/login_request_dto.dart';

part 'login_request_provider.g.dart';

@Riverpod(keepAlive: true)
class LoginRequest extends _$LoginRequest {
  @override
  LoginRequestDto build() {
    return LoginRequestDto(id: "", password: '');
  }

  void input({String? id, String? password}) {
    state = LoginRequestDto(
        id: id ?? state.id, password: password ?? state.password);
  }

  void reset() {
    state = LoginRequestDto(id: "", password: "");
  }
}
