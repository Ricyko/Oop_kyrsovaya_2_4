package component

import data.Test
import hoc.withDisplayName
import kotlinx.html.*
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event
import react.*
import react.dom.*
import react.router.dom.navLink
import redux.ChangeQuestionTitle
import redux.ChangeQuestionVariant
import store
import kotlin.browser.document

interface EditQuestionPageProps : RProps {
	var test: Test
	var head: String
	var testQuantity :Int
	var pageNumber:Int
	var testNumber:Int
	var submitSelectedAnswer : (Event) -> Unit
	var submitqPoints:(Event) -> Unit
}

fun editQuestionPageFC() =
	functionalComponent<EditQuestionPageProps> { props ->
		div("container editQuestionPage") {
			div("head editPage"){
				h2() { +props.head }
				newTestTitle(props.testNumber)
			}
			p("questionTitle"){
				attrs.id = "questionTitle>${props.pageNumber}<"
				+props.test.testPages[props.pageNumber].question
				input(type = InputType.text) {
					attrs.placeholder = "вопрос"
					attrs.id = "questionTitleInput>${props.pageNumber}<"
				}
				button {
					+"исправить"
					attrs.id = "change"
					attrs.onClickFunction = {
						val newTestTitle = (document.getElementById("questionTitleInput>${props.pageNumber}<") as HTMLInputElement).value
						store.dispatch(ChangeQuestionTitle(props.testNumber, props.pageNumber, newTestTitle))
					}
				}
			}
			ul("variants"){
				props.test.testPages[props.pageNumber].answerVars.mapIndexed { variantIndex, variant ->
					li("inputGroup") {
						attrs.id ="${variantIndex}|${props.pageNumber}"
						span{ +variant }
						div{
							input(type = InputType.text) {
								attrs.placeholder = "вариант ответа"
								attrs.id = "variantInput>$variantIndex<"
							}
							button {
								+"исправить"
								attrs.id = "change"
								attrs.onClickFunction = {
									val newTestTitle = (document.getElementById("variantInput>$variantIndex<") as HTMLInputElement).value
									store.dispatch(ChangeQuestionVariant(props.testNumber, props.pageNumber, variantIndex, newTestTitle))
								}
							}
						}
					}
				}
				div("toFlex"){
					span("toLeft") {
						+" Правильный ответ: ("
						span("highlight") { +"${props.test.testPages[props.pageNumber].correctAnswer}" }
						span { +") " }
						span("highlight") {
							+props.test.testPages[props.pageNumber].answerVars[props.test.testPages[props.pageNumber].correctAnswer]
						}
					}
					div("toRight"){
						select {
							attrs.id = "answerSelect"
							for (i in 0 until props.test.testPages[props.pageNumber].answerVars.size) {
								option {
									+"$i"
								}
							}
						}
						button {
							+"изменить ответ"
							attrs.id = "answerSelectButton"
							attrs.value = "${props.testNumber}|${props.pageNumber}"
							attrs.onClickFunction = props.submitSelectedAnswer
						}
					}
				}
				span("answer"){
					span("qPoint") { +"Шкала оценивания: "
						span("highlight"){+"${props.test.testPages[props.pageNumber].pointsForAnswer}"}
						span{+" за ответ"}}

					div("qPointInputHolder"){
						input(type = InputType.number) {
							attrs.max = "1000"
							attrs.placeholder = "введите баллы за ответ"
							attrs.defaultValue = "0"
							attrs.id = "qPointsInput"
						}
						button {
							+"изменить баллы"
							attrs.id = "aqPointsInputButton"
							attrs.value = "${props.testNumber}|${props.pageNumber}"
							attrs.onClickFunction = props.submitqPoints
						}
					}
				}
				br {  }
				div("navigationButton"){
					if (props.pageNumber != 0) {
						div("prewButton"){//пред. страница
							navLink("${props.pageNumber - 1}") {
								button(classes = "button third") {
									+"\u276E"
								}
							}
						}
					}
					else{
						div("prewButton"){
							navLink("/edittest") {
								button(classes = "button third") {
									+"\u276E"
								}
							}
						}
					}
					console.log("props.pageNumber = ${props.pageNumber}\nprops.testQuantity - 1 = ${props.testQuantity - 1}")
					if (props.pageNumber < props.testQuantity - 1) {
						div("nextButton"){
							navLink("${props.pageNumber + 1}") {
								button(classes = "button first") {
									+"\u276F"
								}
							}
						}
					}else{div("nextButtonLorem"){}}
				}
			}
		}
	}


fun RBuilder.editQuestionPage(
		header: String,
		pageNumber:Int,
		testNumber:Int,
		testQuantity:Int,
		currentTest: Test,
		submitSelected:(Event) -> Unit,
		submitqPoints:(Event) -> Unit
) = child(
		withDisplayName("editQuestionPage", editQuestionPageFC())
) {
	attrs.head = header
	attrs.testQuantity = testQuantity
	attrs.submitSelectedAnswer = submitSelected
	attrs.testNumber = testNumber
	attrs.pageNumber = pageNumber
	attrs.submitqPoints = submitqPoints
	attrs.test = currentTest
}