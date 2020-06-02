package data

data class Test(
        var testName:String,
        val testPages: MutableList<TestPage>
){
    var testPagesQuantity:Int = this.testPages.lastIndex+1
    override fun toString(): String  = this.testName
}
var testArray : Array<Test> =
    arrayOf(
        Test("Kotlin. Начальный уровень", mutableListOf(
            TestPage("Можно ли в файле Kotlin прописать код на Java? ",
                    arrayOf(
                    "Можно, но лишь некоторые классы и функции",
                    "Да, можно прописывать любой Java код",
                    "Нельзя"),
                    1,//очки за правильный ответ
                    2),//правильный ответ
            TestPage("Верно ли записана переменная?\n" +
                    "var number: Float = 45",
                    arrayOf(
                    "Не верно, так как после 45 необходимо добавить символ «f»",
                    "Да, верно"),1,0),
            TestPage("Какого типа данных не существует в Kotlin?",
                    arrayOf(
                    "List",
                    "Все перечисленные типы данных существуют",
                    "Object",
                    "Array"),1,1),
            TestPage("Какая функция считается основной в Kotlin?",
                    arrayOf(
                    "run()",
                    "main()",
                    "Main()",
                    "start()"),1,1),
            TestPage("Какая функция служит для вывода данных?",
                    arrayOf(
                    "system.out()",
                    "print()",
                    "log()",
                    "write()"),1,1),
            TestPage("В Kotlin каждый файл представляет из себя класс?",
                    arrayOf(
                    "Да, верно",
                    "Нет"),1,1))),

        Test("Java. Начальный уровень?", mutableListOf(
            TestPage("Сколько параметров может принимать функция?",
                    /*var testName:String,*/
                    /*val testPages: Array<TestPage>*/
                    arrayOf(
                    "Не более 3",
                    "Не более 10",
                    "Неограниченное количество",
                    "Не более 5",
                    "Не более 20"),1,2),
            TestPage("Где правильно создан массив?",arrayOf(
                    "int a[] = 1, 2, 3, 4, 5;",
                    "int[] a = new int {1, 2, 3, 4, 5};",
                    "int[] a = int[] {1, 2, 3, 4, 5};",
                    "int a = new int[] {1, 2, 3, 4, 5};",
                    "int[] a = new int[] {1, 2, 3, 4, 5};"),1,4),
            TestPage("Где правильно создана простая переменная?",arrayOf(
                    "bool isDone = true;",
                    "float x = 23.3f;",
                    "int[] a;",
                    "char str = 'ab';",
                    "byte x = 100000;"),1,1),
            TestPage("Где правильно присвоено новое значение к многомерному массиву?",arrayOf(
                    "a[0, 0] = 1;",
                    "a{0}{0} = 1;",
                    "a[0 0] = 1;",
                    "a(0)(0) = 1;",
                    "a[0][0] = 1;"),1,4),
            TestPage("Какие математические операции есть в Java?",arrayOf(
                    "+, -, *, /, --, ++",
                    "Все перечисленные",
                    "+, -, *, /, %",
                    "+, -, *, /"),1,1)
        )),

        Test("Git. Начальный уровень",
                mutableListOf(
            TestPage("Что такое GitHub?",arrayOf(
                    "Графический интерфейс для работы с Git",
                    "Веб-сервис для хостинга IT-проектов",
                    "Технология для контроля версий проектов"
            ),5,1),
            TestPage("В каком редакторе можно работать с Git?",arrayOf(
                    "Только в редакторе Atom",
                    "Можно во всех редакторах, что поддерживают подключение терминала",
                    "Можно во всех редакторах"
            ),4,3),
            TestPage("Как в Git установить глобально Email пользователя?",arrayOf(
                    "git user.email",
                    "git config --global user.email",
                    "git --global user.email",
                    "git config-global user.email",
                    "git config user.email"
            ),3,0),
            TestPage("Как в Git установить значение «Имя пользователя»?",arrayOf(
                    "git config --global user.name",
                    "Все команды, кроме git user.name",
                    "git user.name",
                    "git config user.name"
            ),2,0),
            TestPage("Что такое Git?",arrayOf(
                    "Это сервер для ваших проектов",
                    "Это таск-менеджер",
                    "Это облачное хранилище",
                    "Это распределённая система управления версиями"
            ),1,3)
        )),

        Test("[EXAMPLE]",
                mutableListOf(
                TestPage("PAGE0",arrayOf(
                        "PAGE0 VARIANT0",
                        "PAGE0 VARIANT1",
                        "PAGE0 VARIANT2",
                        "PAGE0 VARIANT3"),
                7,0),
                TestPage("PAGE1",arrayOf(
                        "PAGE1 VARIANT0",
                        "PAGE1 VARIANT1",
                        "PAGE1 VARIANT2",
                        "PAGE1 VARIANT3"),
                6,1),
                TestPage("PAGE2",arrayOf(
                        "PAGE2 VARIANT0",
                        "PAGE2 VARIANT1",
                        "PAGE2 VARIANT2",
                        "PAGE2 VARIANT3"),
                5,2),
                TestPage("PAGE3",arrayOf(
                        "PAGE3 VARIANT0",
                        "PAGE3 VARIANT1",
                        "PAGE3 VARIANT2",
                        "PAGE3 VARIANT3"),
                4,3),
                TestPage("PAGE4",arrayOf(
                        "PAGE4 VARIANT0",
                        "PAGE4 VARIANT1",
                        "PAGE4 VARIANT2",
                        "PAGE4 VARIANT3",
                        "PAGE4 VARIANT4",
                        "PAGE4 VARIANT5",
                        "PAGE4 VARIANT6",
                        "PAGE4 VARIANT7"),
                3,4),
                TestPage("PAGE5 ",arrayOf(
                        "PAGE5 VARIANT0",
                        "PAGE5 VARIANT1",
                        "PAGE5 VARIANT2",
                        "PAGE5 VARIANT3",
                        "PAGE5 VARIANT4",
                        "PAGE5 VARIANT5",
                        "PAGE5 VARIANT6",
                        "PAGE5 VARIANT7"),
                2,5),
                TestPage("PAGE6",arrayOf(
                        "PAGE6 VARIANT0",
                        "PAGE6 VARIANT1",
                        "PAGE6 VARIANT2",
                        "PAGE6 VARIANT3",
                        "PAGE6 VARIANT4",
                        "PAGE6 VARIANT5",
                        "PAGE6 VARIANT6",
                        "PAGE6 VARIANT7"),
                1,6)
            )
        )
    )
