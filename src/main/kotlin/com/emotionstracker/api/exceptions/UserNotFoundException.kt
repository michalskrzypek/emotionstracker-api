package com.emotionstracker.api.exceptions

class UserNotFoundException(email: String) : RuntimeException("User with email: $email not found!")
