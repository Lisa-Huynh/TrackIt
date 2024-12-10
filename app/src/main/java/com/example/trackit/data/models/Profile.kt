package com.example.trackit.data.models

sealed class Profile {

    data class Loaded(
        val id: String,
        val firstName: String,
        val lastName: String,
        val cards: List<Card>,
    ) : Profile()

    data object Blank : Profile()

    companion object {
        fun fromMap(data: Map<String, Any>): Profile {
            return Profile.Loaded(
                    id = data["id"].toString(),
                    firstName = data["firstName"].toString(),
                    lastName = data["lastName"].toString(),
                    cards = data["cards"].toString().mapToCards(),
                )
        }

        private fun String.mapToCards(): List<Card> {
            return emptyList()
        }
    }
}