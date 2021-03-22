package com.valeriopapi.loadingbutton

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import com.google.android.material.button.MaterialButton


class LoadingButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val progressBar: ProgressBar
    private val buttonTextView: MaterialButton

    private var text: CharSequence? = null
    private var loadingText: CharSequence? = null
    private var successText: CharSequence? = null
    private var errorText: CharSequence? = null

    @ColorInt
    private var backgroundTint = Color.GRAY

    @ColorInt
    private var successBackground = Color.GREEN

    @ColorInt
    private var errorBackground = Color.RED

    private var gravityProgress = GravityProgress.CENTER
    private var isLoading = false
    private var restoreTimeMillis: Int? = null
    private var progressBarSize: Float = 0f

    private val bHPadding: Int by lazy { dpToPx(12) }
    private val bVPadding: Int by lazy { dpToPx(4) }

    init {
        val root = LayoutInflater.from(context).inflate(R.layout.loading_button_layout, this, true)
        buttonTextView = root.findViewById(R.id.button)
        progressBar = root.findViewById(R.id.progress)
        loadAttr(attrs, defStyleAttr)
    }

    private fun loadAttr(attrs: AttributeSet?, defStyleAttr: Int) {
        val arr = context.obtainStyledAttributes(attrs, R.styleable.LoadingButton, defStyleAttr, 0)

        this.text = arr.getString(R.styleable.LoadingButton_text)
        this.loadingText = arr.getString(R.styleable.LoadingButton_loadingText)
        this.successText = arr.getString(R.styleable.LoadingButton_successText)
        this.errorText = arr.getString(R.styleable.LoadingButton_errorText)
        this.backgroundTint =
            arr.getColor(R.styleable.LoadingButton_backgroundTint, backgroundTint)
        this.successBackground =
            arr.getColor(
                R.styleable.LoadingButton_successBackground,
                ContextCompat.getColor(context, R.color.successColor)
            )
        this.errorBackground =
            arr.getColor(
                R.styleable.LoadingButton_errorBackground,
                ContextCompat.getColor(context, R.color.errorColor)
            )
        val minWidth = arr.getDimension(R.styleable.LoadingButton_android_minWidth, 0f)

        val progressGravity: Int = arr.getInteger(R.styleable.LoadingButton_progressGravity, -1)

        val cornerRadius =
            arr.getDimension(R.styleable.LoadingButton_cornerRadius, dpToPx(4).toFloat())
        val elevation = arr.getDimension(R.styleable.LoadingButton_elevation, dpToPx(4).toFloat())
        this.progressBarSize =
            arr.getDimension(R.styleable.LoadingButton_progressBarSize, dpToPx(24).toFloat())

        restoreTimeMillis =
            arr.getInteger(R.styleable.LoadingButton_restoreTimeMillis, -1).checkPositiveOrNull()
        arr.recycle()

        buttonTextView.minWidth = minWidth.toInt()
        buttonTextView.cornerRadius = cornerRadius.toInt()
        setGravity(GravityProgress.fromAttrValue(progressGravity) ?: gravityProgress)
        setProgressSize(progressBarSize.toInt())
        setText(text)
        ViewCompat.setElevation(buttonTextView, elevation)
        buttonTextView.setBackgroundColor(backgroundTint)

        progressBar.background = drawCircle(backgroundTint)

        setIsLoading(false)
    }

    private fun setProgressSize(progressBarSize: Int) {
        progressBar.layoutParams.run {
            this.height = progressBarSize
            this.width = progressBarSize
            progressBar.layoutParams = this
        }
    }

    fun drawCircle(backgroundColor: Int): GradientDrawable {
        val shape = GradientDrawable()
        shape.shape = GradientDrawable.OVAL
        shape.setColor(backgroundColor)
        return shape
    }

    fun setGravity(gravityProgress: GravityProgress) {
        this.gravityProgress = gravityProgress
        (progressBar.layoutParams as LayoutParams).run {
            this.gravity = Gravity.CENTER_VERTICAL or gravityProgress.gravity
            progressBar.layoutParams = this
        }
    }

    override fun setClickable(clickable: Boolean) {
        super.setClickable(clickable)
    }

    private fun refresh() {
        if (isLoading) {
            buttonTextView.isClickable = false
            buttonTextView.icon = null
            buttonTextView.setBackgroundColor(backgroundTint)
            when (gravityProgress) {
                GravityProgress.START -> {
                    buttonTextView.text = this.loadingText ?: this.text
                    buttonTextView.setPadding(
                        progressBarSize.toInt() + bHPadding,
                        bVPadding,
                        bHPadding,
                        bVPadding
                    )
                }
                GravityProgress.CENTER -> {
                    buttonTextView.text = ""
                }
                GravityProgress.END -> {
                    buttonTextView.text = this.loadingText ?: this.text
                    buttonTextView.setPadding(
                        bHPadding,
                        bVPadding,
                        progressBarSize.toInt() + bHPadding,
                        bVPadding
                    )
                }
            }
            progressBar.show()
        } else {
            buttonTextView.isClickable = true
            buttonTextView.text = this.text
            buttonTextView.setPadding(bHPadding, bVPadding, bHPadding, bVPadding)
            progressBar.hide()
        }
    }

    fun setIsLoading(isLoading: Boolean) {
        this.isLoading = isLoading
        refresh()
    }

    fun setText(text: CharSequence?) {
        this.text = text
        refresh()
    }

    fun getText(): CharSequence? {
        return text
    }

    fun setNormal() {
        setIsLoading(false)
        buttonTextView.icon = null
        buttonTextView.text = this.text
        buttonTextView.setBackgroundColor(backgroundTint)
        buttonTextView.requestLayout()

    }

    fun setSuccess(
        successText: CharSequence? = null,
        restoreTimeMillis: Int? = null,
        callback: (() -> Unit)? = null
    ) {
        setIsLoading(false)
        buttonTextView.isClickable = false
        val drawable = ContextCompat.getDrawable(context, R.drawable.avd_anim)
        buttonTextView.icon = drawable
        drawable.animateDrawable()
        this.buttonTextView.text = successText ?: this.successText ?: text
        buttonTextView.setBackgroundColor(successBackground)
        checkRestoreButton(restoreTimeMillis, callback)
        buttonTextView.requestLayout()

    }

    fun setError(
        errorText: CharSequence? = null,
        restoreTimeMillis: Int? = null,
        callback: (() -> Unit)? = null
    ) {
        setIsLoading(false)
        buttonTextView.isClickable = false
        val drawable = ContextCompat.getDrawable(context, R.drawable.ic_close)
        buttonTextView.icon = drawable
        drawable.animateDrawable()
        this.buttonTextView.text = errorText ?: this.errorText ?: text
        buttonTextView.setBackgroundColor(errorBackground)
        checkRestoreButton(restoreTimeMillis, callback)
        buttonTextView.requestLayout()

    }

    private fun checkRestoreButton(
        restoreTimeMillis: Int? = null,
        callback: (() -> Unit)? = null
    ) {
        val mResoreTime = restoreTimeMillis.checkPositiveOrNull() ?: this.restoreTimeMillis
        if (mResoreTime != null) {
            Handler(Looper.getMainLooper()).postDelayed({
                setNormal()
                callback?.invoke()
            }, mResoreTime.toLong())
        }
    }

    override fun setOnClickListener(l: OnClickListener?) {
        buttonTextView.setOnClickListener(l)
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        buttonTextView.isEnabled = enabled
    }
}