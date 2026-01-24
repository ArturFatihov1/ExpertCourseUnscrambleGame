package com.example.expertcourseunscramblegame.views.retry

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import com.example.expertcourseunscramblegame.views.visibilitybutton.UpdateVisibility
import com.example.expertcourseunscramblegame.views.visibilitybutton.VisibilitySavedState
import com.example.expertcourseunscramblegame.views.visibilitybutton.VisibilityUiState

class RetryButton : androidx.appcompat.widget.AppCompatButton, UpdateVisibility {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private lateinit var state: VisibilityUiState

    override fun onSaveInstanceState(): Parcelable? {
        return super.onSaveInstanceState()?.let {
            val savedState = VisibilitySavedState(it)
            savedState.save(state)
            return savedState
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val restoredState = state as VisibilitySavedState
        super.onRestoreInstanceState(restoredState.superState)
        update(restoredState.restore())
    }

    override fun update(visibility: Int) {
        this.visibility = visibility
    }

    override fun update(state: VisibilityUiState) {
        this.state = state
        this.state.update(this)
    }

}
