import 'package:json_annotation/json_annotation.dart';

part 'login_request_dto.g.dart';

@JsonSerializable()
class LoginRequestDto {
  final String id;
  final String password;

  LoginRequestDto({
    required this.id,
    required this.password,
  });

  copyWith({
    String? id,
    String? password,
  }) =>
      LoginRequestDto(
        id: id ?? this.id,
        password: password ?? this.password,
      );

  factory LoginRequestDto.fromJson(Map<String, dynamic> json) =>
      _$LoginRequestDtoFromJson(json);

  Map<String, dynamic> toJson() => _$LoginRequestDtoToJson(this);
}
