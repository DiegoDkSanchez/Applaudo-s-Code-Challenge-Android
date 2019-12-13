package com.example.challengeaplaudo.models

import java.io.Serializable

data class Product (
    var id : String,
    var type : String,
    var attributes: Attributes
): Serializable