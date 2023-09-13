import 'package:dio/dio.dart';
import 'package:retrofit/retrofit.dart';
import 'package:riverpod_annotation/riverpod_annotation.dart';
import 'package:session_front/controller/provider/dio_provider.dart';
import 'package:session_front/model/login_request_dto.dart';

part 'auth_repository.g.dart';

@riverpod
class AuthService extends _$AuthService {

  @override
  AuthRepository build() {
    final Dio dio = ref.read(dioProvider);
    return AuthRepository(dio);
  }
}

@RestApi(baseUrl: "http://localhost:8080/api/account")
abstract class AuthRepository {
  factory AuthRepository(Dio dio, {String baseUrl}) = _AuthRepository;

  @POST("/login")
  Future<HttpResponse<dynamic>> login(@Body() LoginRequestDto request);

  @POST("/user_id")
  Future<HttpResponse<String>> headerLogin(@Body() LoginRequestDto request);
}