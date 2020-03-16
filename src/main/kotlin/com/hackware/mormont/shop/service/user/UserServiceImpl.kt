package com.hackware.mormont.shop.service.user

import com.hackware.mormont.shop.dto.user.UserDto
import com.hackware.mormont.shop.dto.user.UserRoles
import com.hackware.mormont.shop.exception.EntityType
import com.hackware.mormont.shop.exception.ExceptionType
import com.hackware.mormont.shop.model.cart.Cart
import com.hackware.mormont.shop.model.user.Role
import com.hackware.mormont.shop.model.user.User
import com.hackware.mormont.shop.model.user.mapToDTO
import com.hackware.mormont.shop.repository.CartRepository
import com.hackware.mormont.shop.repository.UserRepository
import com.hackware.mormont.shop.util.exception
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class UserServiceImpl : UserService {
    @Autowired
    private lateinit var bCryptPasswordEncoder: BCryptPasswordEncoder

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var cartRepository: CartRepository

    @Transactional
    override fun signup(userDto: UserDto): UserDto {
        val userRole: Role
        var userModel = userRepository.findUserByEmail(userDto.email)

        if (userModel.isEmpty) {
            userRole = if (userDto.isAdmin!!)
                Role(UserRoles.ADMIN.name)
            else
                Role(UserRoles.USER.name)

            val user = User(
                    userDto.email,
                    bCryptPasswordEncoder.encode(userDto.password),
                    userDto.firstName,
                    userDto.lastName,
                    userDto.mobilePhone,
                    setOf(userRole)
            )
            val savedUser = userRepository.save(user)

            val savedCart = cartRepository.save(Cart(owner = savedUser))
            savedUser.cart = savedCart

           return savedUser.mapToDTO()
        }
        throw exception(EntityType.USER, ExceptionType.DUPLICATE_ENTITY, userDto.email)
    }

    override fun findUserByEmail(email: String): UserDto? {
        val user = getUserByName(email)
        user.also { userModel ->
            return userModel.mapToDTO()
        }
    }

    @Transactional
    override fun updateProfile(userDto: UserDto): UserDto {
        getUserByName(userDto.email).also { userModel ->
            userModel.password = bCryptPasswordEncoder.encode(userDto.password)
            userRepository.save(userModel)
            return userModel.mapToDTO()
        }
    }

    override fun changePassword(userDto: UserDto, newPassword: String): UserDto {
        getUserByName(userDto.email).also {  userModel ->
            userModel.password = bCryptPasswordEncoder.encode(userDto.password)
            userRepository.save(userModel)
            return userModel.mapToDTO()
        }
    }

    override fun getUserByName(name: String): User {

        val user = userRepository.findUserByEmail(name)
        if (user.isEmpty)
            throw exception(EntityType.USER, ExceptionType.ENTITY_NOT_FOUND, name)

        return user.get()
    }

    override fun getAllUsers(): List<UserDto> {
        return userRepository.findAll().map { it.mapToDTO() }
    }

    @Transactional
    override fun deleteUserByName(name: String) {
        val user = getUserByName(name)
        userRepository.delete(user)
    }
}
