package library
import CheckOutReturnBook

open class LibraryWorkForManager(var id:String, var pwd:String) : PurchaseBook() {

    private val checkOutBook = CheckOutReturnBook("manager", id)

    companion object {
        fun askId(): String {
            var checkId = 0
            while (checkId <= 2) {
                val memberID = ManagerPrintFormat.UPDATEBOOKLISTSTATUS.print("askID")
                if (LibraryDataBase.memberList.none { it.id == memberID }) {
                    println("입력하신 멤버의 ID는 존재하지 않습니다. 다시 확인하여 입력해주세요.")
                    checkId += 1
                    continue
                }
                return memberID
            }
            return ""
        }
    }
    
    fun checkBookList() {
        println("\n📚전체 도서 목록: ")
        LibraryDataBase.bookList.forEach { item ->
            println("➡️ ${item.name} | ${item.author} | ${item.checkOut} ")
        }
    }

    fun updateBookListStatus(task: String) {
        when (task) {
            "1" -> {
                when (MemberPrintFormat.BORROWBOOK.print("menu")) {
                    "1" -> checkOutBook.byName("checkOut")
                    "2" -> checkOutBook.byAuthor()
                }
            }
            "2" -> checkOutBook.byName("return")
        }
    }

}








