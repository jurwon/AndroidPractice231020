package com.example.myapplication_test4



class  Test231023 (val name:String, val age:Int, val email:String) {
    init {
        println("객체 생성 할 때 마다, init 실행이 됨.======================================= ")
    }
    constructor(name:String, age:Int, email:String, password: String) : this(name, age, email)

    var name3 : String = "손주원"
    var email3: String = "sjw@naver.com"

    // 초기화를 미루겠다. 결론, 사용하는 시점에 초기화함.
    lateinit var name2: String
    var email2: String = "클래스 내부에서 선언만 안됨. 예외는 lateinit "
    val price : Int = 1000

    val data4: Int by lazy {
        println("by lazy 사용, 뒤늦게 초기화 ")
        30
    }

    val data5 :Array<String> = Array(3,{"기본값"})

    fun sayHello() {
        println("주생성자의 name을 사용하기 : ${name}")
    }

}

open class SuperClass(val name: String, val email: String) {
    init {
        println("부모 클래스 초기화 됨, 매 자식이 인스턴스 생성때마다")
    }
//    val name : String = "손주원"
    fun sayHello() {
        println("클래스의 멤버 이름과 이메일 출력하기 : ${name}, ${email}")
    }

}

class SubClass : SuperClass("손주원","sjw@naver.com") {
    init {
        println("자식 클래스 초기화 됨, 인스턴스 생성때마다")
    }
    val password: String = "1234"
    fun printPassword() {
        println("password : ${password}")
    }
}

open class Super {
    var publicData = 10
    protected var protectedData = 10
    private var privateData = 10

}

class Sub: Super () {
    fun subFun() {
        publicData++
        protectedData++
        //privateData++
    }
}

class NonDataClass(val name :String, val age: Int)

data class DataClass(val name :String, val age: Int)


class MyClass {
    companion object {
        var data = 10
        fun some() {
            println("컴패니언 object 테스트 ")
        }
    }
}


fun main() {
// 널허용 연산자. ?
    var data: String? = "sjw"

    val resultdata = data?.length ?: 0
    println("resultdata : ${resultdata}")


    // 함수 타입으로 사용
    val sum3: (Int)->Int = {no33:Int ->
        println(no33)
        33
    }
    val result3 = sum3(100)
    println("reuslt3 : ${result3}")


    val sum2 = {no1:Int ->
        println( no1)
        30
    }
// 30 값 재할당.
    val result4 = sum2(10)
    println("result4 : ${result4}")

    val MyClassTest = MyClass()
    MyClass.some()
    val test11 = NonDataClass("손주원",20)
    val test13 = NonDataClass("손주원",20)
    val test12 = DataClass("손주원",20)
    val test14 = DataClass("손주원",20)
    println("NonDataClass equals 확인 : ${test11.equals(test13)}")

    println("DataClass equals 확인 : ${test12.equals(test14)}")

    val test33 = SubClass();
    test33.printPassword()
    test33.sayHello()

    val test1 : Test231023 = Test231023("손주원",40, "sjw@naver.com","1234")
    val test23 : Test231023 = Test231023("손주원23",40, "sjw@naver.com","1234")
    test23.sayHello()
    val test3 : Test231023 = Test231023("손주원3",40, "sjw@naver.com","1234")
    println("비 데이터 클래스 toString 해보기 (의미없는 메모리 주소값 표기): " + test1.toString())

    test1.name2 = "초기값 할당 후 사용."
    val lateInitName = test1.name2;
    println("lateInitName 사용 : " + lateInitName)

    val array_0 = test1.data5.get(0)
    test1.data5.set(0,"손주원 0")
    println("array_0 의 값: " + array_0)

    var mutableList = mutableListOf<Int>(10,20,30)

        for (i in mutableList.indices) {
        println("리스트 가져오기 :${mutableList.get(i)}  ")
    }

    // 불변
    var map = mapOf<String, String>(Pair("one","hello"), "two" to "world")
    println("맵 가져오기 :${map.get("one")}  ")
    // 가변
    var map2 = mutableMapOf<String,String>(Pair("one","hello"), "two" to "world")
    map2.set("3","test")
    println("맵 가져오기 :${map2.get("3")}  ")

    println("when 테스트 =====================  ")
    var data7 : Any =  true
    when (data7) {
        is String -> println("문자열 데이터다")
        in 1..10 -> println("숫자 데이터다")
        else -> {
            println("기타 데이터다")
        }
    }

    println("for 테스트 =====================  ")
    var sum: Int = 0
    for ( i in 1..10) {
        sum += i
    }
    println("sum의 합 구하는 테스트 : ${sum}")

    println("withIndex() 테스트 =====================  ")
    var data10 = arrayOf<Int>(10,20,30)
    for ( (index,value) in data10.withIndex()) {
        print(value)
        if (index !== data10.size -1) print(",")
    }

println()
    // 다른 변수에 재할당(사용하는 시점에 초기화가 되고, 값도 할당 됨. (표현식)
    // 앱이 시작하면, 순차적으로, 다 초기화를 해서 사용함.
    // 하지만, lateinit, by lazy , 특징은 뒤늦게 초기화를 한다. 사용하는 시점에 초기화를 한다..
    // 사용도 안하는데 메모리에 다 로드 하는 것이 아니라, 실제 사용하는 것들만 메모리에 로드한다.
    val dat4_2 = test1.data4
    println("dat4_2 :  " +dat4_2)
    println("dat4_2  문자열 내부에서 특정의 변수값 사용 ${dat4_2}:  " )



    val test2: User = User("손주원2","sjw2@naver.com","1234")
    println("데이터 클래스 toString 해보기 (실제값 표기): " + test2.toString())

}




