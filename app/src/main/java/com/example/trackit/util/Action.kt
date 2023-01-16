package com.example.trackit.util

enum class Action {
    ADD,
    DELETE,
    NO_ACTION
}

fun String?.toAction(): Action {
    return when {
        this == "ADD" -> Action.ADD
        this == "DELETE" -> Action.DELETE
        else -> Action.NO_ACTION
    }
}