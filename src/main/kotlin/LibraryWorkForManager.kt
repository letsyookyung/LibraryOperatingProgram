package library
import java.time.LocalDate

open class LibraryWorkForManager(var id:String, var pwd:String) : PurchaseBook() {
//
    fun checkBookList() {
        ManagerPrintFormat.CHECKBOOKLIST.print("")
    }

    fun updateBookListStatus(task:String) {

        var taskDoneFlag = false

        when(task){
            "1" -> {
                var bookName = ManagerPrintFormat.UPDATEBOOKLISTSTATUS.print("askBookName")
                for (i in 0 until LibraryDataBase.bookList.size) {
                    if (LibraryDataBase.bookList[i].name == bookName && LibraryDataBase.bookList[i].checkOut == "대여 가능") {
                        var id = ManagerPrintFormat.UPDATEBOOKLISTSTATUS.print("askID")
                        updateMemberStatus@ for(j in 0 until LibraryDataBase.memberList.size) {
                            if (LibraryDataBase.memberList[j].id == id) {
                                LibraryDataBase.memberList[j].checkOutHistory.add(LibraryDataBase.HistoryByPersonInfo(LocalDate.now(), bookName, "대여중"))
                                break@updateMemberStatus
                            }
                        }
                        LibraryDataBase.bookList[i].checkOut = "대여 불가능"
                        ManagerPrintFormat.UPDATEBOOKLISTSTATUS.print("checkOutAccept")
                        taskDoneFlag = true
                        break
                    }
                    if (LibraryDataBase.bookList[i].name == bookName && LibraryDataBase.bookList[i].checkOut == "대여 불가능") {
                        ManagerPrintFormat.UPDATEBOOKLISTSTATUS.print("checkOutDeny")
                        taskDoneFlag = true
                        break
                    }
                }
                if (!taskDoneFlag) {
                    ManagerPrintFormat.UPDATEBOOKLISTSTATUS.print("noBookName")
                }
            }
            "2" -> {
                var bookName = ManagerPrintFormat.UPDATEBOOKLISTSTATUS.print("askBookName")
                for (i in 0 until LibraryDataBase.bookList.size) {
                    if (LibraryDataBase.bookList[i].name == bookName && LibraryDataBase.bookList[i].checkOut == "대여 불가능") {
                        var id = ManagerPrintFormat.UPDATEBOOKLISTSTATUS.print("askID")
                        updateMemberStatus@ for(j in 0 until LibraryDataBase.memberList.size) {
                            if (LibraryDataBase.memberList[j].id == id) {
                                LibraryDataBase.memberList[j].checkOutHistory.add(LibraryDataBase.HistoryByPersonInfo(LocalDate.now(), bookName, "반납 완료" ))
                                break@updateMemberStatus
                            }
                        }
                        LibraryDataBase.bookList[i].checkOut = "대여 가능"
                        ManagerPrintFormat.UPDATEBOOKLISTSTATUS.print("return")
                        taskDoneFlag = true
                        break
                    }
                }
                if (!taskDoneFlag) {
                    ManagerPrintFormat.UPDATEBOOKLISTSTATUS.print("noBookName")
                }
            }
        }
    }

}