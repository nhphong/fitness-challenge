package com.nhphong.fitnesschallenge.models

data class Workout(val type: Type, val imageUrl: String) {
    enum class Type(private val type: String) {
        ABS("Abs"),
        BUTT("Butt"),
        ARM("Arm"),
        LEG("Leg"),
        FULL_BODY("Full Body");

        override fun toString() = type
    }
}
