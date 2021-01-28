package com.zzr.jetpacktest.entity.test

data class GlossEntry(
    val Abbrev: String,
    val Acronym: String,
    val GlossDef: GlossDef,
    val GlossSee: String,
    val GlossTerm: String,
    val ID: String,
    val SortAs: String
)