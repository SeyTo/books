package com.rj.books.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import com.rj.books.R

class QuestionsView : LinearLayout {

    private var _title: String? = null

    constructor(context: Context?) : super(context) {
        init(null, 0)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(attrs, defStyleAttr)
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        init(attrs, defStyleAttr, defStyleRes)
    }

    private fun init(attrs: AttributeSet?, defStyle: Int, defStyleRes: Int = 0) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.QuestionsView, defStyle, defStyleRes)

        _title = if (a.hasValue(R.styleable.QuestionsView_question_title)) a.getString(R.styleable.QuestionsView_question_title) else "Got to ask you something ..."

        a.recycle()

        orientation = VERTICAL
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)

        val title = TextView(context)
        title.text = _title
        addView(title)

        val question = TextView(context)
        question.text = "This is a random question"
        addView(question)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()

    }

}