package component

import data.TestPage
import hoc.withDisplayName
import react.*
import react.dom.*
import react.functionalComponent

interface QuestionListProps : RProps {
	var testPages: MutableList<TestPage>
	var header:String
}

fun questionListFC() =
	functionalComponent<QuestionListProps> {
		div("container questionListFC"){
			h2("head") { +it.header }
				it.testPages.mapIndexed { _, currentQuestion ->
					p("questionTitle") { +currentQuestion.question }
					ol("zebra questionListVars") {
						attrs.start = "0"
						div("variant"){
							currentQuestion.answerVars.mapIndexed { _, currentVariant ->
							li {
								+currentVariant
							}
						}
					}
					p("answer") { +" Правильный ответ: ("
						span("highlight") { +"${ currentQuestion.correctAnswer }" }
						span { +") "}
						span("highlight") { +currentQuestion.answerVars[currentQuestion.correctAnswer]}/**/
						p("qPoint") { +"Шкала оценивания: "
						span("highlight"){+"${currentQuestion.pointsForAnswer}"}
						span{+" за ответ"}}
					}
				}
			}
		}
	}

fun RBuilder.questionList(
		testPages:MutableList<TestPage>,
		header:String
) = child(withDisplayName("questionList", questionListFC())){
	attrs.testPages = testPages
	attrs.header = header
}

