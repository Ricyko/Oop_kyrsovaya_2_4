import component.start
import data.*
import react.dom.render
import react.router.dom.browserRouter
import react.router.dom.hashRouter
import redux.*
import wrapper.reduxLogger
import kotlin.browser.document

val store = createStore(
    ::changeReducer,
   State(
           testArray,
           0,
           0
   ),
    compose(
        rEnhancer(),
        applyMiddleware(
            reduxLogger.logger as
                    Middleware<State, Action, Action, Action, Action>
        )
    )
)

val rootDiv = document.getElementById("root")

fun render() = render(rootDiv) {
    hashRouter {
        start(store)
    }
}

fun main() {
    render()
    store.subscribe {
        render()
    }
}