package com.jsoft.shoppingapp.utils

import android.util.Patterns

fun validateEmail(email: String): RegisterValidation {
    if (email.isEmpty()) return RegisterValidation.Failed("Email cannot be emty")
    if (!Patterns.EMAIL_ADDRESS.matcher(email)
            .matches()
    ) return RegisterValidation.Failed("Wrong email")

    return RegisterValidation.Success
}

fun validatePassword(password: String): RegisterValidation {
    if (password.isEmpty()) return RegisterValidation.Failed("Password cannot be emty")
    if (password.length < 6) return RegisterValidation.Failed("Password should be at least 6 characters")

    return RegisterValidation.Success


}