package component

import data.Test
import hoc.withDisplayName
import kotlinx.html.InputType
import kotlinx.html.id
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event
import react.*
import react.dom.*
import react.functionalComponent
import react.router.dom.navLink
import redux.CreateTestTitle
import redux.NullifyTestPageIndex
import redux.NullifyVariantIndex
import store
import kotlin.browser.document

interface CreateTestTitleProps : RProps {
	var testList: Array<Test>
}

fun createTestTitleFC() =
		functionalComponent<CreateTestTitleProps> {
			div("container createTestTitleFC") {
				h1("head") { +"Создание теста" }
				div("newTestInputHolder") {
					input(InputType.text) {
						attrs.id = "newTestTitleInput"
						attrs.placeholder = "Название теста"
					}
					navLink("/newtestvariant"){
						button {
							attrs.id = "createTestTitle"
							+"подтвердить"
							attrs.onClickFunction = {
								val newTestTitle = (document.getElementById("newTestTitleInput") as HTMLInputElement).value
								store.dispatch(CreateTestTitle(newTestTitle))
								store.dispatch(NullifyVariantIndex())
								store.dispatch(NullifyTestPageIndex())
							}
						}
					}
				}
			}
		}

fun RBuilder.createTestTitle(
		testList:Array<Test>
) = child(withDisplayName("createTestTitleFC", createTestTitleFC())){
	attrs.testList = testList
}

