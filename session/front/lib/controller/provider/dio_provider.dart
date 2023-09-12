import 'package:dio/dio.dart';
import 'package:flutter/material.dart';
import 'package:riverpod_annotation/riverpod_annotation.dart';

part 'dio_provider.g.dart';

@riverpod
Dio dio(DioRef ref) {
  Dio dio = Dio();
  dio.interceptors.add(CustomInterceptor());
  return dio;
}

class CustomInterceptor extends Interceptor {
  @override
  void onRequest(RequestOptions options, RequestInterceptorHandler handler) {
    debugPrint("path : ${options.uri} data : ${options.data.toString()}\n");
    return super.onRequest(options, handler);
  }

  @override
  void onResponse(
    Response response,
    ResponseInterceptorHandler handler,
  ) {
    debugPrint("path : ${response.requestOptions.uri} data : ${response.data.toString()}\n");
    return super.onResponse(response, handler);
  }
}

