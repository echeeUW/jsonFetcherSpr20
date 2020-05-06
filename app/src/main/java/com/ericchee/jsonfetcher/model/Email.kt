package com.ericchee.jsonfetcher.model

data class Email(
    val id: Int,
    val from: String,
    val content: String?,
    val isImportant: Boolean
)

/**
{
    "id": 0,
    "from": "seahawks@gmail.com",
    "content": "Go Hawks!!! SEA!! HAWKSSS!!!! Go 12s! Legion of boom",
    "isImportant": true
}
        */