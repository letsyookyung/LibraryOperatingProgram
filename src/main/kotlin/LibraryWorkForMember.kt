package library

class LibraryWorkForMember(var id:String, var pwd:String) {


    fun printMyCheckOutStatus() {
        for (j in 0 until LibraryDataBase.memberList.size) {
            if (LibraryDataBase.memberList[j].id == id) {
                MemberPrintFormat.PRINTMYCHECKOUTSTATUS.print(j)
                break
            }
        }
    }
    fun searchBook() {} //name, author
    fun borrowBook(task:String) {}

    fun returnBook() {}






}