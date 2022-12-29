package library

import kotlin.system.exitProcess



fun main() = with(System.`in`.bufferedReader()) {

    while (true) {

        var modeChangeFlag = false
        var loginRetry = 0

        when (ProgramPrintFormat.START.print("menu")) {
            "1" -> {

                modeOut@ while(loginRetry<=2){

                    if (modeChangeFlag) break@modeOut

                    val loginSet= ProgramPrintFormat.START.print("login").split(",")
                    val lwmg = LibraryWorkForManager(loginSet[0], loginSet[1])

                    if (!ProgramOperator.login(lwmg.id, lwmg.pwd,"관리자")) {
                        loginRetry+=1
                        ProgramPrintFormat.START.print("loginFail")
                        continue
                    }

                    taskLoop@ while(true){
                        // 원하는 업무 물어보기능
                        when(ManagerPrintFormat.START.print("menu")){
                            "1" -> lwmg.checkBookList()
                            "2" -> lwmg.updateBookListStatus(ManagerPrintFormat.UPDATEBOOKLISTSTATUS.print("menu"))
                            "3" -> lwmg.purchase(ManagerPrintFormat.PURCHASE.print("menu"))
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

                modeOut@ while (loginRetry <= 2) {

                    if (modeChangeFlag) break@modeOut

                    // 로그인 물어보기
                    val loginSet = ProgramPrintFormat.START.print("login").split(",")

                    val lwmb = LibraryWorkForMember(loginSet[0], loginSet[1])

                    if (!ProgramOperator.login(lwmb.id, lwmb.pwd,"멤버")) {
                        loginRetry += 1
                        ProgramPrintFormat.START.print("loginFail")
                        continue
                    }

                    taskLoop@ while (true) {
                        // 원하는 업무 물어보기

                        when (MemberPrintFormat.START.print("menu")) {
                            "1" -> lwmb.printMyCheckOutStatus()
                            "2" -> lwmb.searchBook(MemberPrintFormat.SEARCHBOOK.print("byWhichField"))
                            "3" -> lwmb.borrowBook(MemberPrintFormat.BORROWBOOK.print("menu"))
                            "4" -> lwmb.returnBook(MemberPrintFormat.RETURNBOOK.print("askBookName"))
                            "5" -> {
                                modeChangeFlag = true
                                break@taskLoop
                            }

                            "6" -> run {
                                println("👋도서 관리 프로그램 종료")
                                exitProcess(0)
                            }
                        }
                    }
                }
            }

            "3" -> {
                val personInfo = ProgramPrintFormat.REGISTER.print("askPersonInfo").split(",")
                PeopleInformationRegister.register(personInfo[1], personInfo[2], personInfo[0])
            }
            "4" -> run {
                println("👋도서 관리 프로그램 종료")
                exitProcess(0)
            }

        }

    }

}