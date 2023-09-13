import 'package:json_annotation/json_annotation.dart';

part 'user_dto.g.dart';

@JsonSerializable()
class UserDto {
  final String id;
  final String name;
  final String password;

  UserDto({
    required this.id,
    required this.name,
    required this.password,
  });

  copyWith({
    String? id,
    String? name,
    String? email,
  }) =>
      UserDto(
        id: id ?? this.id,
        name: name ?? this.name,
        password: email ?? this.password,
      );

  factory UserDto.fromJson(Map<String, dynamic> json) =>
      _$UserDtoFromJson(json);

  Map<String, dynamic> toJson() => _$UserDtoToJson(this);
}