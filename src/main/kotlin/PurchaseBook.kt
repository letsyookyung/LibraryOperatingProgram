package library

open class PurchaseBook {

    companion object {
        var totalBalance = 100000
    }

    fun purchase(task: String) {

        when(task){
            "1" -> {
                var bookInfo = PrintFormat.MANAGER.print("purchase", "askBookInfo").split("/")
                if (totalBalance-bookInfo[2].toInt() <= 0) {
                    PrintFormat.MANAGER.print("purchase", "noMoney")
                } else {
                    addInBookList(bookInfo[0], bookInfo[1], bookInfo[2])
                    updateBalance(bookInfo[0], bookInfo[1], bookInfo[2])
                }
            }
            "2" -> PrintFormat.MANAGER.print("purchase","printPurchaseHistory")
        }
    }

    private fun addInBookList(bookName: String, bookAuthor: String, price: String) {
        LibraryDataBase.bookList.add(LibraryDataBase.BookInfo(bookName, bookAuthor, price.toInt(), "대여 가능"))
        PrintFormat.MANAGER.print("purchase", "addInBookList")
    }

    private fun updateBalance(bookName: String, bookAuthor: String, price: String) {
        LibraryDataBase.purchaseHistoryTable.add(LibraryDataBase.BookInfo(bookName, bookAuthor, price.toInt(),"")
            .apply{remainBalance = totalBalance -price.toInt()})
        totalBalance-=price.toInt()
        PrintFormat.MANAGER.print("purchase", "updateBalance")
        }
    }


