package redux

class ChangeTestTitle(val testIndex:Int, val newTestTitle:String) : RAction

class ChangeQuestionVariant(val testIndex:Int,val questionIndex:Int,val variantIndex:Int, val newQuestionVariant:String) : RAction

class ChangeQuestionTitle(val testIndex:Int,val questionIndex:Int, val newQuestionTitle:String) : RAction

class ChangeCorrectAnswer(val testIndex:Int, val questionIndex:Int, val newAnswer:Int) : RAction

class ChangeQuestionPoint(val testIndex:Int, val questionIndex:Int, val newQPoint:Int) : RAction


class CreateTestTitle(val newTestTitle:String) : RAction

class CreateQuestion(val question:String, val testPageIndex:Int) : RAction

class CreateVariant(val variant:String, val testPageIndex:Int, val variantIndex:Int) : RAction

class CreateAnswer(val answer:Int, val variantIndex:Int) : RAction

class CreatePoints(val points:Int, val variantIndex:Int) : RAction

class CreateTestPage() : RAction


class IncreaseTestPageIndex() : RAction

class IncreaseVariantIndex() : RAction

class NullifyVariantIndex() : RAction

class NullifyTestPageIndex() : RAction