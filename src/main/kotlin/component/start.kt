package component

import data.*
import hoc.withDisplayName
import org.w3c.dom.*
import react.*
import react.dom.*
import react.router.dom.*
import redux.*
import org.w3c.dom.events.Event
import react.RBuilder
import store
import kotlin.browser.document

interface StartProps : RProps {
    var store: Store<State, RAction, WrapperAction>
}

fun startFC() =
    functionalComponent<StartProps> { props ->
        div("nabBar"){
            ul{
                li{navLink(""){+"HOME"}}
                li {
                    navLink("/testlist") { +"список тестов" }
                    ul {
                        store.getState().tests.mapIndexed { testIndex, test ->
                            li { navLink("/testlist/$testIndex") { +test.testName } }
                        }
                    }
                }
                li{
                    navLink("/edittest"){+"редактор тестов"}
                    ul {
                        store.getState().tests.mapIndexed{testIndex, test ->
                            li { navLink("/edittest/test${testIndex}/0") { +test.testName } }
                        }
                    }
                }
                li{
                    navLink("/newtest"){
                        +"создание теста"
                    }
                }
            }
        }
        switch {
            route(
                "/edittest",
                exact = true,
                render = {
                    editTest(store.getState().tests)
                }
            )
            route("/newtestvariant",
                    exact = true,
                    render = {
                        createTestVariants(store.getState().tests)
                    }
            )
            route("/newtest",
                    exact = true,
                    render = {createTestTitle(store.getState().tests)}
            )
            route("/testlist",
                exact = true,
                render = {testList(store.getState().tests)}
            )
            store.getState().tests.mapIndexed { testIndex,test ->
                route("/testlist/$testIndex",
                    exact = true,
                    render = {
                        questionList(
                            test.testPages,
                            test.testName
                        )
                    }
                )
            }
            props.store.getState().tests.mapIndexed { testIndex, test ->
                for (pageNumber in 0..test.testPages.size) {
                    route(
                        "/edittest/test${testIndex}/$pageNumber",
                        exact = true,
                        render = {
                            editQuestionPage(
                                test.testName,
                                pageNumber,
                                testIndex,
                                test.testPages.size,
                                props.store.getState().tests[testIndex],
                                props.submitSelected(),
                                props.submitQPoints()
                            )
                        }
                    )
                }
            }
        }
    }

fun StartProps.submitQPoints(): (Event) -> Unit = {
    val newPointsForAnswer = (document.getElementById("qPointsInput") as HTMLInputElement).value.toInt()
    val buttonValue = (document.getElementById("aqPointsInputButton") as HTMLButtonElement).value
    val testIndex = buttonValue.substringBefore('|').toInt()
    val questionIndex = buttonValue.substringAfter('|').toInt()
    store.dispatch(ChangeQuestionPoint(testIndex,questionIndex,newPointsForAnswer))
}

fun StartProps.submitSelected(): (Event) -> Unit = {
    val selectedAnswer = (document.getElementById("answerSelect") as HTMLSelectElement).value.toInt()
    val buttonValue = (document.getElementById("answerSelectButton") as HTMLButtonElement).value
    val testIndex = buttonValue.substringBefore('|').toInt()
    val questionIndex = buttonValue.substringAfter('|').toInt()
    store.dispatch(ChangeCorrectAnswer(testIndex,questionIndex,selectedAnswer))
}

fun RBuilder.start(store: Store<State, RAction, WrapperAction>) =
    child(withDisplayName("Start", startFC())){
        attrs.store = store
    }