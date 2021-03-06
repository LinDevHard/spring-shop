package com.hackware.mormont.shop.repository

import com.hackware.mormont.shop.model.user.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<User, Long> {

    fun findUserByEmail(email: String): Optional<User>

}