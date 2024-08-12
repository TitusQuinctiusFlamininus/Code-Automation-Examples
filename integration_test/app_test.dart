import 'dart:async';
import 'dart:convert';
import 'dart:io';

import 'package:music_app/core/resource/resource.dart';
import 'package:retrofit/dio.dart';
import 'package:test/test.dart';
import 'package:music_app/core/resource/http_response_handler.dart';

void main() {
   group('component tests for http responses', () {
      test('Check Failed Resource can be detected', () {
        var futureBack = (HttpResponseHandler<dynamic>()).handle(MyFuture() as Future<HttpResponse>);
        futureBack.then((Resource f) {
              assert(f.runtimeType == FailedResource);   
        });     
      });

     
   });
}


 class MyFuture implements Future<HttpResponse>{
  @override
  Stream<HttpResponse> asStream() {
    // TODO: implement asStream
    throw UnimplementedError();
  }
 
  @override
  Future<HttpResponse> catchError(Function onError, {bool Function(Object error)? test}) {
    // TODO: implement catchError
    throw UnimplementedError();
  }
 
  @override
  Future<R> then<R>(FutureOr<R> Function(HttpResponse value) onValue, {Function? onError}) {
   throw UnimplementedError();
  }
 
  @override
  Future<HttpResponse> timeout(Duration timeLimit, {FutureOr<HttpResponse> Function()? onTimeout}) {
    // TODO: implement timeout
    throw UnimplementedError();
  }
 
  @override
  Future<HttpResponse> whenComplete(FutureOr<void> Function() action) {
    // TODO: implement whenComplete
     throw Exception('Something bad happened');
  }
 
}
