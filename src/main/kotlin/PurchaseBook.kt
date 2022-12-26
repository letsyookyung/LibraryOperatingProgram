package library

open class PurchaseBook {

    companion object {
        var totalBalance = 100000
    }

    fun purchase(task: String) {

        when(task){
            "1" -> {
                var bookInfo = ManagerPrintFormat.PURCHASE.print("askBookInfo").split("/")
                if (totalBalance-bookInfo[2].toInt() <= 0) {
                    ManagerPrintFormat.PURCHASE.print("noMoney")
                } else {
                    addInBookList(bookInfo[0], bookInfo[1], bookInfo[2])
                    updateBalance(bookInfo[0], bookInfo[1], bookInfo[2])
                }
            }
            "2" -> ManagerPrintFormat.PURCHASE.print("printPurchaseHistory")
        }
    }

    private fun addInBookList(bookName: String, bookAuthor: String, price: String) {
        LibraryDataBase.bookList.add(LibraryDataBase.BookInfo(bookName, bookAuthor, price.toInt(), "대여 가능"))
        ManagerPrintFormat.PURCHASE.print("addInBookList")
    }

    private fun updateBalance(bookName: String, bookAuthor: String, price: String) {
        LibraryDataBase.purchaseHistoryTable.add(LibraryDataBase.BookInfo(bookName, bookAuthor, price.toInt(),"")
            .apply{remainBalance = totalBalance -price.toInt()})
        totalBalance-=price.toInt()
        }
    }


