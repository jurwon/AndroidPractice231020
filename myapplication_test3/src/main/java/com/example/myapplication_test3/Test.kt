package com.example.myapplication_test3

class Test {

}

// 함수 밖, 최고 상위 영역
val test1 = "test";
var test2 = null;
val test5:String = "";
//var test6:String;
// 결론, IDE 를 사용하는 이유? 작업의 편의성, 생산성
// 그래서, 기본 문법에 너무 외워서 다 맞춰서 작성보다는
// IDE에 맡기는 것을 추천.
// 자동 완성 코드 등, 여러가지를 이용할 예정.

val data1 : Int by lazy {
    println("순서3 in lazy")
    10
}

// 자바, 모양.
// 일반 함수 다른게, 매개변수 자리에 함수형 타입, 반환형 타입에도 함수형 타입.
fun hofFun(arg: (Int) -> Boolean): () -> String {
    val result = if(arg(10)) {
        "valid"
    } else {
        "invalid"
    }
    return {"hofFun result : $result"}
}

fun main() {
    val result = hofFun({no -> no > 0})
    println(result())

    println("순서1 main 먼저 실행.")
    println("순서2 lazy 호출 확인: " + data1)

    val test3:String;
    var test4:String;

    // var 변수 , val 상수 ,
    // ex) var 변수명 : 타입 = 값;
    // 기본은 변경이 되지 않는 상수를 주로 사용을 하되, 만약, 변경이 되는 부분이면 var 쓰면 됨.
    // 코틀린에서는 모든 타입이 , 참조형(객체)
    // 자바는 : 기본(형) 타입 8가지, 그외 형(참조형)
    var name = "sjw";
    var name2 : String = "sjw2";
    var age : Int = 10;
    var age2 = 10;
    println("이름 : " + name)
    println("hello android by kotlin")
}