import library.LibraryDataBase
import library.MemberPrintFormat

class CommonErrorCheck {

    companion object {
        fun checkIfExists(field: String, value: String):Boolean {
            when (field) {
                "bookName" -> {
                    if (LibraryDataBase.bookList.none { it.name == value }) {
                        MemberPrintFormat.RETURNBOOK.print("noBook")
                        return false
                    }
                }

                "author" -> {
                    if (LibraryDataBase.bookList.none { it.author == value }) {
                        MemberPrintFormat.RETURNBOOK.print("noBook")
                        return false
                    }
                }
            }
            return true
        }

        fun checkIfAvailable(field: String, filteredBookList: List<LibraryDataBase.BookInfo>):Boolean {
            when (field) {
                "checkOut" -> {
                    if (filteredBookList.none { it.checkOut == "대여 가능" }) {
                        println("대여 가능한 도서가 없는 상태입니다. 혹시 모르니 도서명을 확인 해보세요. \n ")
                        return false
                    }
                }

                "return" -> {
                    if (filteredBookList.none { it.checkOut == "대여 불가능" }) {
                        println("해당 책은 대여 상태가 아닙니다. 혹시 모르니 도서명을 확인 해보세요. \n ")
                        return false
                    }
                }
            }
            return true
        }
    }
}