package redux

import data.*

fun changeReducer(state: State, action: RAction) =
    when (action) {
        is ChangeTestTitle ->State(
                changeTestTitle(
                        state.tests,action.testIndex,
                        action.newTestTitle),
                state.variantIndex,
                state.testPageIndex
        )
        is ChangeQuestionVariant -> State(
                changeVariant(
                        state.tests,
                        action.testIndex,
                        action.questionIndex,
                        action.variantIndex,
                        action.newQuestionVariant),
                state.variantIndex,
                state.testPageIndex
        )
        is ChangeQuestionTitle -> State(
                changeQuestionTitle(
                        state.tests,
                        action.testIndex,
                        action.questionIndex,
                        action.newQuestionTitle),
                state.variantIndex,
                state.testPageIndex
        )
        is ChangeCorrectAnswer -> State(
                changeQuestionAnswer(
                        state.tests,action.testIndex,
                        action.questionIndex,
                        action.newAnswer),
                state.variantIndex,
                state.testPageIndex
        )
        is ChangeQuestionPoint -> State(
                changeQuestionPoint(state.tests,
                        action.testIndex,
                        action.questionIndex,
                        action.newQPoint),
                state.variantIndex,
                state.testPageIndex
        )
        is CreateTestTitle -> State(
                state.tests.toMutableList().apply {
                    add(Test(
                            action.newTestTitle,
                            mutableListOf(TestPage(
                            "Введите вопрос",
                            arrayOf("Добавьте вариант ответа"),
                            0,
                             0
                    )
                    )))

                }.toTypedArray(),
                state.variantIndex,
                state.testPageIndex

        )

        is CreateQuestion -> State(
                createQuestion(state.tests,action.testPageIndex,action.question),
                state.variantIndex,
                state.testPageIndex
        )
        is CreateVariant -> State(
                createVariant(state.tests,action.testPageIndex,action.variant,action.variantIndex),
                state.variantIndex,
                state.testPageIndex
        )
        is IncreaseTestPageIndex -> State(
                state.tests,
                state.variantIndex,
                state.testPageIndex + 1
        )
        is IncreaseVariantIndex -> State(
                state.tests,
                state.variantIndex + 1,
                state.testPageIndex
        )
        is CreateAnswer -> State(
                createAnswer(state.tests,action.variantIndex,action.answer),
                state.variantIndex,
                state.testPageIndex
        )
        is CreatePoints -> State(
                createPoints(state.tests,action.variantIndex,action.points),
                state.variantIndex,
                state.testPageIndex
        )
        is CreateTestPage -> State(
                createTestPage(state.tests),
                state.variantIndex,
                state.testPageIndex
        )
        is NullifyVariantIndex -> State(
                state.tests,
                0,
                state.testPageIndex
        )
        is NullifyTestPageIndex -> State(
                state.tests,
                state.variantIndex,
                0
        )
        else -> state
    }

fun createTestPage(tests: Array<Test>):Array<Test>{
    tests[tests.lastIndex].testPages.add(
            TestPage(
            "Введите вопрос",
            arrayOf("Добавьте вариант ответа"),
            0,
            0
        )
    )
    return tests
}

fun createPoints(tests: Array<Test>, index:Int, newPoints:Int):Array<Test>{
    tests[tests.lastIndex].testPages[index].pointsForAnswer = newPoints
    return tests
}

fun createAnswer(tests: Array<Test>, index:Int, newAnswer:Int):Array<Test>{
    tests[tests.lastIndex].testPages[index].correctAnswer = newAnswer
    return tests
}

fun createVariant(tests: Array<Test>, testIndex:Int, newVariant:String,variantIndex:Int):Array<Test>{
    tests[tests.lastIndex].testPages[testIndex].answerVars[variantIndex] = newVariant
    return tests
}

fun createQuestion(tests: Array<Test>, testPageIndex:Int, newQuestion:String):Array<Test>{
    tests[tests.lastIndex].testPages[testPageIndex].question = newQuestion
    return tests
}

fun changeQuestionPoint(tests: Array<Test>, testIndex:Int, questionIndex:Int, newQPoint:Int):Array<Test>{
    tests[testIndex].testPages[questionIndex].pointsForAnswer = newQPoint
    return tests
}
fun changeQuestionAnswer(tests: Array<Test>, testIndex:Int,  questionIndex:Int,  newAnswer:Int):Array<Test>{
    tests[testIndex].testPages[questionIndex].correctAnswer = newAnswer
    return tests
}
fun changeTestTitle(test:Array<Test>,tIndex:Int,newTitle:String):Array<Test>{
    test[tIndex].testName = newTitle
    return test
}
fun changeVariant(tests:Array<Test>,testIndex:Int, questionIndex:Int,variantIndex:Int,newVariant:String):Array<Test>{
    tests[testIndex].testPages[questionIndex].answerVars[variantIndex] = newVariant
    return tests
}
fun changeQuestionTitle(tests:Array<Test>,testIndex:Int, questionIndex:Int,newQuestiontitle:String):Array<Test>{
    tests[testIndex].testPages[questionIndex].question = newQuestiontitle
    return tests
}