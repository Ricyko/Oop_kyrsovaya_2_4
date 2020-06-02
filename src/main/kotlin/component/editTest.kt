package component

import data.Test
import hoc.withDisplayName
import react.*
import react.dom.*
import react.functionalComponent
import react.router.dom.navLink

interface EditTestProps : RProps {
	var testList: Array<Test>
}

fun editTestFC() =
	functionalComponent<EditTestProps> {
		div("container EditTestFC") {
			h1("head") { +"Редактирование теста" }
			div {
				it.testList.mapIndexed { testIndex, qurrentTest ->
					div("button-container"){
						navLink("/edittest/test${testIndex}/0",className = "boton1") {
							+qurrentTest.testName
						}
					}
				}
			}
		}
	}

fun RBuilder.editTest(
		testList:Array<Test>
) = child(withDisplayName("editTest", editTestFC())){
	attrs.testList = testList
}

