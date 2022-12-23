package library


class ProgramOperator(var id: String, var pwd: String, var type: String) {

    companion object {
        private var checkList = mutableListOf<LibraryDataBase.PeopleInfo>()
        fun login(id: String, pwd: String, type: String): Boolean {

            when (type) {
                "관리자" -> checkList = LibraryDataBase.managerList
                "멤버" -> checkList = LibraryDataBase.memberList
            }

            for (i in 0 until checkList.size) {
                if (checkList[i].id == id) {
                    return checkList[i].pwd == pwd
                }
            }
            return false
        }
    }


//    fun programExit()

    }



