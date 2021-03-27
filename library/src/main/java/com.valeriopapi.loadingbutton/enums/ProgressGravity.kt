package com.valeriopapi.loadingbutton.enums

import android.view.Gravity

enum class ProgressGravity(val attrValue: Int, val gravity: Int) {
    START(0, Gravity.START),
    CENTER(1, Gravity.CENTER),
    END(2, Gravity.END);

    companion object {
        fun fromAttrValue(attrValue: Int): ProgressGravity? {
            return values().associateBy(ProgressGravity::attrValue)[attrValue]
        }
    }
}