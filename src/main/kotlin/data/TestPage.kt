package data

data class TestPage (
		var question : String,
		var answerVars : Array<String>,
		var pointsForAnswer:Int,
		var correctAnswer:Int
) { val variantsQuantity = this.answerVars.size} //количество вариантов ответа в тесте