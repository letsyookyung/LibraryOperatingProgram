package library

import DataManipulator

open class PurchaseBook {

    fun purchase(task: String) {
        when (task) {
            "1" -> {
                try {
                    val bookInfo = ManagerPrintFormat.PURCHASE.print("askBookInfo").split("/")
                    if (LibraryDataBase.totalBalance-bookInfo[2].toInt() <= 0) {
                        ManagerPrintFormat.PURCHASE.print("noMoney")
                    } else {
//                        addInBookList(bookInfo[0], bookInfo[1], bookInfo[2])
                        updateBalance(bookInfo[0], bookInfo[1], bookInfo[2])
                    }
                } catch (e:IndexOutOfBoundsException) {
                    println("예시 형태로 입력해주세요.")
                    return
                }
            }
            "2" -> ManagerPrintFormat.PURCHASE.print("printPurchaseHistory")
        }
    }

    private fun updateBalance(bookName: String, bookAuthor: String, price: String) {
        DataManipulator.add(LibraryDataBase.purchaseHistoryTable,
            LibraryDataBase.BookInfo(bookName, bookAuthor, price.toInt(),"대여 가능")
            .apply{remainBalance = LibraryDataBase.totalBalance -price.toInt()})
        LibraryDataBase.totalBalance -= price.toInt()
        }
    }


