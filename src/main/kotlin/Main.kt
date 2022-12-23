package library

import kotlin.system.exitProcess



fun main(args: Array<String>) = with(System.`in`.bufferedReader()) {

    var lwmg = LibraryWorkForManager()
    PeopleInformationRegister.register("ron", "1234", "관리자")
    PeopleInformationRegister.register("ivy", "1234", "멤버")
    PeopleInformationRegister.register("hailey", "1234", "멤버")
    PeopleInformationRegister.register("betty", "1234", "멤버")


    while (true) {

        var modeChangeFlag = false
        var loginRetry = 0

        when(PrintFormat.PROGRAM.print("start","menu")){
            "1" -> {
                modeOut@ while(loginRetry<=2){

                    if (modeChangeFlag) break@modeOut

                    // 로그인 물어보기
                    var loginSet= PrintFormat.PROGRAM.print("start", "login").split(",")

                    if (!ProgramOperator.login(loginSet[0], loginSet[1], "관리자")) {
                        loginRetry+=1
                        PrintFormat.PROGRAM.print("start", "loginFail")
                        continue
                    }

                    taskLoop@ while(true){
                        // 원하는 업무 물어보기
                        var task = PrintFormat.MANAGER.print("start", "menu")
                        when(task){
                            "1" -> lwmg.checkBookList()
                            "2" -> lwmg.updateBookListStatus(PrintFormat.MANAGER.print("updateBookListStatus","menu"))
                            "3" -> lwmg.purchase(PrintFormat.MANAGER.print("purchase", "menu"))
                            "4" -> {
                                modeChangeFlag = true
                                break@taskLoop
                            }
                            "5" -> run {
                                println("👋도서 관리 프로그램 종료")
                                exitProcess(0)
                            }
                        }
                    }

                }

            }

            "2" -> {
//                modeOut@ while(loginRetry<=3){
//                    if (modeChangeFlag) break@modeOut
//
//                    println("로그인 id 및 비밀번호를 입력해주세요.")
//                    println("id: ")
//                    var id = readLine()
//                    println("pwd: ")
//                    var pwd = readLine()
//
//                    var mb = Member(id, pwd)
//
//                    if (!mb.login(mb.id, mb.pwd, mb.type)) {
//                        loginRetry+=1
//                        continue}
//
//                    taskLoop@ while(true){
//                        println("원하는 업무 번호를 선택하세요. " +
//                                "\n1.전체 도서 목록 조회 " +
//                                "\n2.대여/반납 업데이트" +
//                                "\n3.도서 구매 및 도서 목록 업데이트"+
//                                "\n4.로그아웃"+
//                                "\n5.프로그램 종료")
//                        var task = readLine()
//                        when(task){
//                            "1" -> LibraryWorkForMember.checkBookList()
//                            "2" -> LibraryWorkForMember.updateBookListStatus()
//                            "3" -> LibraryWorkForMember.purchase()
//                            "4" -> {
//                                modeChangeFlag = true
//                                break@taskLoop
//                            }
//                            "5" -> {
//                                println("도서 관리 프로그램을 종료합니다.")
//                                exitProcess(0)
//                            }
//                        }
//                    }
//
//                }
//
//            }
            }
            "3" -> {
                var personInfo = PrintFormat.PROGRAM.print("register", "askPersonInfo").split(",")
                PeopleInformationRegister.register(personInfo[1], personInfo[2], personInfo[0])
            }
            "4" -> run {
                println("👋도서 관리 프로그램 종료")
                exitProcess(0)
        }

        }

    }

}