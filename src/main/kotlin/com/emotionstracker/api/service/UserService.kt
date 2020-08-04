package com.emotionstracker.api.service

import com.emotionstracker.api.domain.User
import com.emotionstracker.api.exceptions.UserNotFoundException
import com.emotionstracker.api.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(val userRepository: UserRepository) {

    fun getByEmail(email: String): User = userRepository.findByEmail(email) ?: throw UserNotFoundException(email)
}
