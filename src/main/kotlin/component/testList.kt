package component

import data.Test
import hoc.withDisplayName
import react.*
import react.dom.*
import react.functionalComponent
import react.router.dom.navLink

interface TestListProps : RProps {
    var testList: Array<Test>
}

fun testListFC() =
    functionalComponent<TestListProps> {
        div("container TestListFC"){
            h1("head testList") { +"Список тестов" }
            it.testList.mapIndexed { currentTestIndex,currentTest ->
                div("button-container"){
                    navLink("/testlist/$currentTestIndex",className = "boton1") {
                        +currentTest.testName
                    }
                }
            }
        }
    }

fun RBuilder.testList(
    testList:Array<Test>
) = child(withDisplayName("testList", testListFC())){
    attrs.testList = testList
}

