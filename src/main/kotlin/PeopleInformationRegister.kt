package library
class PeopleInformationRegister {
    companion object {
        fun register(id: String, pwd: String, type: String) {
            if (type == "관리자") {
                LibraryDataBase.managerList.add(LibraryDataBase.PeopleInfo(id, pwd, type))
                println("관리자 $id 등록 완료")
            } else if (type == "멤버") {
                LibraryDataBase.memberList.add(LibraryDataBase.PeopleInfo(id, pwd, type))
                println("멤버 $id 등록 완료")
            }
        }
    }
}


