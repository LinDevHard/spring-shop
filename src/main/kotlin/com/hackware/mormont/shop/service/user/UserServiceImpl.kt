package com.hackware.mormont.shop.service.user

import com.hackware.mormont.shop.dto.user.UserDto
import com.hackware.mormont.shop.dto.user.UserRoles
import com.hackware.mormont.shop.exception.EntityType
import com.hackware.mormont.shop.exception.ExceptionType
import com.hackware.mormont.shop.model.user.Role
import com.hackware.mormont.shop.model.user.User
import com.hackware.mormont.shop.model.user.mapToUserDto
import com.hackware.mormont.shop.repository.RoleRepository
import com.hackware.mormont.shop.repository.UserRepository
import com.hackware.mormont.shop.util.exception
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service


@Service
class UserServiceImpl : UserService {
    @Autowired
    private lateinit var bCryptPasswordEncoder: BCryptPasswordEncoder

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var roleRepository: RoleRepository

    override fun signup(userDto: UserDto): UserDto {
        val userRole: Role
        var userModel = userRepository.findUserByEmail(userDto.email)

        if (userModel == null) {
            userRole = if (userDto.isAdmin!!)
                Role(UserRoles.ADMIN.name)
            else
                Role(UserRoles.USER.name)

            userModel = User(
                    userDto.email,
                    bCryptPasswordEncoder.encode(userDto.password),
                    userDto.firstName,
                    userDto.lastName,
                    userDto.mobilePhone,
                    setOf(userRole)
            )
            userRepository.save(userModel)
            return userModel.mapToUserDto()
        }
        throw exception(EntityType.USER, ExceptionType.DUPLICATE_ENTITY, userDto.email)
    }

    override fun findUserByEmail(email: String): UserDto? {
        val user = userRepository.findUserByEmail(email)
        user?.let { userModel ->
            return userModel.mapToUserDto()
        }
        throw exception(EntityType.USER, ExceptionType.ENTITY_NOT_FOUND, email)
    }

    override fun updateProfile(userDto: UserDto): UserDto {
        getUserByEmail(userDto.email)?.let { userModel ->
            userModel.password = bCryptPasswordEncoder.encode(userDto.password)
            userRepository.save(userModel)
            return userModel.mapToUserDto()
        }
        throw exception(EntityType.USER, ExceptionType.ENTITY_NOT_FOUND, userDto.email)
    }

    override fun changePassword(userDto: UserDto, newPassword: String): UserDto {
        getUserByEmail(userDto.email)?.let { userModel ->
            userModel.password = bCryptPasswordEncoder.encode(userDto.password)
            userRepository.save(userModel)
            return userModel.mapToUserDto()
        }
        throw exception(EntityType.USER, ExceptionType.ENTITY_NOT_FOUND, userDto.email)
    }

    private fun getUserByEmail(email: String) =
            userRepository.findUserByEmail(email)

}
