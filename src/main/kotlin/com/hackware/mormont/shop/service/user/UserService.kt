package com.hackware.mormont.shop.service.user

import com.hackware.mormont.shop.dto.user.UserDto


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

}