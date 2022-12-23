package library

open class LibraryWorkForManager : PurchaseBook() {
//
    fun checkBookList() {
        PrintFormat.MANAGER.print("checkBookList", "NO")
    }

    fun updateBookListStatus(task:String) {

        var taskDoneFlag = false

        when(task){
            "1" -> {
                var bookName = PrintFormat.MANAGER.print("updateBookListStatus", "askBookName")
                for (i in 0 until LibraryDataBase.bookList.size) {
                    if (LibraryDataBase.bookList[i].name == bookName && LibraryDataBase.bookList[i].checkOut == "대여 가능") {
                        LibraryDataBase.bookList[i].checkOut = "대여 불가능"
                        PrintFormat.MANAGER.print("updateBookListStatus", "checkOutAccept")
                        taskDoneFlag = true
                        break
                    }
                    if (LibraryDataBase.bookList[i].name == bookName && LibraryDataBase.bookList[i].checkOut == "대여 불가능") {
                        PrintFormat.MANAGER.print("updateBookListStatus", "checkOutDeny")
                        taskDoneFlag = true
                        break
                    }
                }
                if (!taskDoneFlag) {
                    PrintFormat.MANAGER.print("updateBookListStatus", "noBookName")
                }
            }
            "2" -> {
                var bookName = PrintFormat.MANAGER.print("updateBookListStatus", "askBookName")
                for (i in 0 until LibraryDataBase.bookList.size) {
                    if (LibraryDataBase.bookList[i].name == bookName && LibraryDataBase.bookList[i].checkOut == "대여 불가능") {
                        LibraryDataBase.bookList[i].checkOut = "대여 가능"
                        PrintFormat.MANAGER.print("updateBookListStatus", "return")
                        taskDoneFlag = true
                        break
                    }
                }
                if (!taskDoneFlag) {
                    PrintFormat.MANAGER.print("updateBookListStatus", "noBookName")
                }
            }
        }
    }

}