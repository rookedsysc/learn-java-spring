import 'package:dio/dio.dart';
import 'package:retrofit/http.dart';
import 'package:riverpod_annotation/riverpod_annotation.dart';
import 'package:session_front/controller/provider/dio_provider.dart';
import 'package:session_front/model/login_request_dto.dart';

part 'auth_repository.g.dart';

@RestApi(baseUrl: "http://localhost:8080/api/auth")
abstract class AuthRepository {
  factory AuthRepository(Dio dio, {String baseUrl}) = _AuthRepository;

  @POST("/login")
  Future<void> login(@Body() LoginRequestDto request);
}