package com.useruser.followay.domain.models

data class Account(
    var name: String,
    var email: String,
    var userType: Int = USER_TYPE_PARENT,
    var password: String = ""
)