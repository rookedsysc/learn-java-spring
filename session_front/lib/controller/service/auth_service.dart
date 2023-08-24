import 'package:dio/dio.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:riverpod_annotation/riverpod_annotation.dart';
import 'package:session_front/controller/provider/dio_provider.dart';
import 'package:session_front/controller/repository/auth_repository.dart';
import 'package:session_front/model/login_request_dto.dart';


part 'auth_service.g.dart';

@riverpod
AuthService authService(AuthServiceRef ref) {
  final Dio dio = ref.read(dioProvider);
  final AuthRepository authRepository= AuthRepository(dio);
  return AuthService(authRepository:authRepository);
}

class AuthService {
  final AuthRepository _authRepository;
  AuthService({required AuthRepository authRepository})
      : _authRepository = authRepository;

  Future<void> login(LoginRequestDto request) async {
    await _authRepository.login(request);
  }
}