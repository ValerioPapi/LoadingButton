package com.valeriopapi.loadingbutton.enums

enum class ProgressPadding(val attrValue: Int) {
    NONE(0),
    ONLY_PROGRESS(1),
    BOTH(2);

    companion object {
        fun fromAttrValue(attrValue: Int): ProgressPadding? {
            return values().associateBy(ProgressPadding::attrValue)[attrValue]
        }
    }
}