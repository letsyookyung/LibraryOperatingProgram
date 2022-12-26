package library
import java.time.LocalDate
import java.util.*

open class LibraryWorkForManager(var id:String, var pwd:String) : PurchaseBook() {

    var sc = Scanner(System.`in`)
//
    fun checkBookList() {
        ManagerPrintFormat.CHECKBOOKLIST.print("")
    }

    fun updateBookListStatus(task:String) {

        var isExisted = false
        var isAvailable = true

        when (task) {
            //대여
            "1" -> {
                var bookName = ManagerPrintFormat.UPDATEBOOKLISTSTATUS.print("askBookName")
                MemberPrintFormat.BORROWBOOK.print("selectBook")
                for (i in 0 until LibraryDataBase.bookList.size) {
                    if (LibraryDataBase.bookList[i].name == bookName) {
                        isExisted = true
                        if (LibraryDataBase.bookList[i].checkOut == "대여 가능") {
                            println(
                                "$i | " + "${LibraryDataBase.bookList[i].name} | " +
                                        "${LibraryDataBase.bookList[i].author} | ${LibraryDataBase.bookList[i].checkOut}"
                            )
                        } else {
                            println(
                                "$i | " + "${LibraryDataBase.bookList[i].name} | " +
                                        "${LibraryDataBase.bookList[i].author} | ${LibraryDataBase.bookList[i].checkOut}"
                            )
                            isAvailable = false
                        }
                    }
                }

                if (!isExisted) {
                    MemberPrintFormat.BORROWBOOK.print("noBook")
                    return
                }

                println("번호는 ${LibraryDataBase.bookList.size - 1}를 벗어날 수 없으니 주의해주세요.\n번호 입력: ")

                var idx = sc.nextLine()

                if (errorByAnyChance(isAvailable, bookName = bookName, input = idx)) {
                    var memberID = ManagerPrintFormat.UPDATEBOOKLISTSTATUS.print("askID")
                    LibraryDataBase.bookList[idx.toInt()].checkOut = "대여 불가능"
                    ManagerPrintFormat.UPDATEBOOKLISTSTATUS.print("checkOutAccept")
                    for (j in 0 until LibraryDataBase.memberList.size) {
                        if (LibraryDataBase.memberList[j].id == memberID) {
                            LibraryDataBase.memberList[j].checkOutHistory.add(
                                LibraryDataBase.HistoryByPersonInfo(
                                    LocalDate.now(), LibraryDataBase.bookList[idx.toInt()].name, "대여중"
                                )
                            )
                            break
                        }
                    }
                }

            }

            //반납
            "2" -> {}

        }

    }

    private fun errorByAnyChance(isAvailable:Boolean, bookName:String="", author:String="", input:String): Boolean {

        var idx = input

        if (idx == "q") {
            return false
        }

        try {
            if (idx.toInt() >= LibraryDataBase.bookList.size) {
                println("입력값의 범위를 다시 확인 후 입력하세요. \n")
                return false
            }

            if (LibraryDataBase.bookList[idx.toInt()].name != bookName && author == ""){
                println("도서명과 입력하신 해당 도서 번호가 일치하지 않습니다. \n")
                return false
            }

            if (LibraryDataBase.bookList[idx.toInt()].author != author && bookName == ""){
                println("도서명과 입력하신 해당 도서 번호가 일치하지 않습니다. \n")
                return false
            }

            if (!isAvailable) {
                println("이 도서는 대여 불가능 상태입니다. \n")
                return false
            }

            if (idx.toInt() >= LibraryDataBase.bookList.size) {
                println("입력하신 값을 확인해주세요.")
                return false
            }

        } catch (e: NumberFormatException) {
            println("입력값은 숫자로 입력해주세요.")
            return false
        }

        return true


    }

}
