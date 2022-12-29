import library.LibraryDataBase

class DataManipulator {

    companion object {

        // people
        fun add(table: MutableList<LibraryDataBase.PeopleInfo>, value: LibraryDataBase.PeopleInfo) {
            table.add(value)
        }


        // books
        fun add(table: MutableList<LibraryDataBase.BookInfo>, value: LibraryDataBase.BookInfo) {
            table.add(value)
        }



    }
}

