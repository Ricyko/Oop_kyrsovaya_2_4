package component

import data.Test
import hoc.withDisplayName
import kotlinx.html.InputType
import kotlinx.html.id
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLInputElement
import react.*
import react.dom.*
import react.functionalComponent
import react.router.dom.navLink
import redux.*
import store
import kotlin.browser.document

interface CreateTestVariantsProps : RProps {
	var testList: Array<Test>
}

fun createTestVariantsFC() =
		functionalComponent<CreateTestVariantsProps> {
			div("container editQuestionPage") {
				val testPageIndex = store.getState().testPageIndex
				val variantIndex = store.getState().variantIndex
				val currentTestIndex = store.getState().tests.lastIndex
				val currentTestPage = it.testList[currentTestIndex].testPages[testPageIndex]
				h1("head") { +"Создание теста" }
				div {
					div {
						p("questionTitle") { +"${currentTestPage.question}  "
							input(InputType.text) {
								attrs.id = "newQuestionInput"
								attrs.placeholder = "Вопрос"
							}
							button {
								attrs.id = "createQuestion"
								+"подтвердить"
								attrs.onClickFunction = {
									val newQuestion = (document.getElementById("newQuestionInput") as HTMLInputElement).value
									store.dispatch(CreateQuestion(newQuestion, testPageIndex))
								}
							}
						}
					}

					div {
						ol("zebra questionListVars") {
							div("variant") {
								currentTestPage.answerVars.forEach {
									li {
										+it
									}
								}
								li {
									input(InputType.text) {
										attrs.id = "newVariantInput"
										attrs.placeholder = "Вариант ответа"
									}
									button {
										attrs.id = "createVariant"
										+"добавить"
										attrs.onClickFunction = {
											val newVariant = (document.getElementById("newVariantInput") as HTMLInputElement).value
											console.log("variantIndex = $variantIndex")
											store.dispatch(CreateVariant(newVariant, testPageIndex, variantIndex))
											store.dispatch(IncreaseVariantIndex())
										}
									}
								}
							}
						}
					}
					div("qPointInputHolder") {
						p("answer") {
							+" Правильный ответ: ("
							span("highlight") { +"${currentTestPage.correctAnswer}" }
							span { +") " }
							span("highlight") { +currentTestPage.answerVars[currentTestPage.correctAnswer] }/**/

						}
						input(InputType.number) {
							attrs.id = "answerInput"
							attrs.defaultValue = "0"

						}
						button {
							attrs.id = "createAnswer"
							+"добавить"
							attrs.onClickFunction = {
								val newAnswer = (document.getElementById("answerInput") as HTMLInputElement).value.toInt()
								console.log("newAnswer = $newAnswer")
								store.dispatch(CreateAnswer(newAnswer, testPageIndex))
							}
						}
					}
					div("qPointInputHolder") {
						p("qPoint") {
							+"Шкала оценивания: "
							span("highlight") { +"${currentTestPage.pointsForAnswer}" }
							span { +" за ответ" }
						}
						input(InputType.number) {
							attrs.id = "pointsInput"
							attrs.defaultValue = "0"
						}
						button {
							attrs.id = "createPoints"
							+"добавить"
							attrs.onClickFunction = {
								val newPoints = (document.getElementById("pointsInput") as HTMLInputElement).value.toInt()
								console.log("newPoints = $newPoints")
								store.dispatch(CreatePoints(newPoints, testPageIndex))
							}
						}
					}
					br{}
					div("navigationButton createTest"){
						div("prewButton"){
							button(classes = "button third") {
								attrs.id = "bttn2"
								+"Подтвердить"
								attrs.onClickFunction = {
									console.log("TestPageInde1 = $testPageIndex")
									store.dispatch(IncreaseTestPageIndex())
									store.dispatch(NullifyVariantIndex())
									store.dispatch(CreateTestPage())
									console.log("TestPageInde2 = $testPageIndex")
								}
							}
						}
						div("nextButton"){
							navLink("/testlist/${currentTestIndex}"){
								button(classes = "button first") {
									attrs.id = "bttn1"
									+"Подтвердить и закончить создание теста"
									attrs.onClickFunction = {
										console.log("TestPageInde1 = $testPageIndex")
										store.dispatch(IncreaseTestPageIndex())
										store.dispatch(NullifyVariantIndex())
										store.dispatch(NullifyTestPageIndex())
										console.log("TestPageInde2 = $testPageIndex")
									}
								}
							}
						}
					}
				}
			}
		}


fun RBuilder.createTestVariants(
		testList:Array<Test>
) = child(withDisplayName("newTest", createTestVariantsFC())){
	attrs.testList = testList
}

