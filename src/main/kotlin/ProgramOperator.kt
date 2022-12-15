package library

private val LibraryDataBase.Companion.managerTable: Unit
    get() {}

class ProgramOperator {

    fun login(mode: Any, id: String, pwd:String) {

        if (mode == "1") {
            LibraryDataBase.managerTable

        }


    }

    fun startIfRegistered()

    fun logout()

    fun programExit()

}