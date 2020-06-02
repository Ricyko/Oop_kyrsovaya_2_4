package component

import hoc.withDisplayName
import kotlinx.html.InputType
import kotlinx.html.id
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLInputElement
import react.*
import react.dom.*
import react.functionalComponent
import redux.ChangeTestTitle
import store
import kotlin.browser.document

interface NewTestTitleProps : RProps {
    var numberTest: Int
}

fun newTestTitleFC() =
    functionalComponent<NewTestTitleProps> { props ->
        input(type = InputType.text,classes = "head editPage") {
            attrs.placeholder = "название теста"
            attrs.id = "testTitleInput"
        }
        button {
            +"исправить"
            attrs.id = "change"
            attrs.onClickFunction = {
                val newTestTitle = (document.getElementById("testTitleInput") as HTMLInputElement).value
                store.dispatch(ChangeTestTitle(props.numberTest,newTestTitle))
            }
        }
    }

fun RBuilder.newTestTitle(
        numberTest:Int
) = child(
    withDisplayName("newTestTitle",  newTestTitleFC())
){
    attrs.numberTest = numberTest
}