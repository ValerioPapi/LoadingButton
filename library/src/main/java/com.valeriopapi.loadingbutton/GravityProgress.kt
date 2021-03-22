package com.valeriopapi.loadingbutton

import android.view.Gravity

enum class GravityProgress(val attrValue: Int, val gravity: Int) {
    START(0, Gravity.START),
    CENTER(1, Gravity.CENTER),
    END(2, Gravity.END);

    companion object {
        fun fromAttrValue(attrValue: Int): GravityProgress? {
            return values().associateBy(GravityProgress::attrValue)[attrValue]
        }
    }
}