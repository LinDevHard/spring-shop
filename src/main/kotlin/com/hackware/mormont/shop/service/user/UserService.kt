package com.hackware.mormont.shop.service.user

import com.hackware.mormont.shop.dto.user.UserDto
import com.hackware.mormont.shop.model.user.User


interface UserService {

    /**
     * Register a new user
     *
     * @param userDto
     * @return
     */
    fun signup(userDto: UserDto): UserDto

    /**
     * Search an existing user
     *
     * @param email
     * @return
     */
    fun findUserByEmail(email: String): UserDto?

    /**
     * Update profile of the user
     *
     * @param userDto
     * @return
     */
    fun updateProfile(userDto: UserDto): UserDto

    /**
     * Update password
     *
     * @param newPassword
     * @return
     */
    fun changePassword(userDto: UserDto, newPassword: String): UserDto

    /**
     * Get user by email
     *
     * @param name
     * @return
     */
    fun getUserByName(name: String): User

    /**
     * Get all users
     *
     * @return
     */
    fun getAllUsers(): List<UserDto>


    /**
     * Delete user by user name(email)
     *
     * @return
     */
    fun deleteUserByName(name: String)
}