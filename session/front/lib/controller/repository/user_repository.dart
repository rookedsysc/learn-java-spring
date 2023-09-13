import 'package:dio/dio.dart' hide Headers;
import 'package:retrofit/retrofit.dart';
import 'package:riverpod_annotation/riverpod_annotation.dart';
import 'package:session_front/controller/provider/dio_provider.dart';
import 'package:session_front/model/login_request_dto.dart';
import 'package:session_front/model/user_dto.dart';

part 'user_repository.g.dart';

@riverpod
class UserService extends _$UserService {
  @override
  UserRepository build() {
    final Dio dio = ref.read(dioProvider);
    return UserRepository(dio);
  }
}

@RestApi(baseUrl: "http://localhost:8080/api/user")
abstract class UserRepository {
  factory UserRepository(Dio dio, {String baseUrl}) = _UserRepository;

  @GET("/me2")
  Future<HttpResponse<UserDto>> me2(@Header("authorization") userId);
}
