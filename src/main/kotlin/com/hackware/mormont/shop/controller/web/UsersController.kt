package com.hackware.mormont.shop.controller.web

import com.hackware.mormont.shop.dto.command.UserCommandForm
import com.hackware.mormont.shop.service.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView

@Controller
class UsersController {
    @Autowired
    private lateinit var userService: UserService

    @GetMapping("/users")
    fun getAllUsers(): ModelAndView {
        val modelAndView = ModelAndView("users")
        val userList = userService.getAllUsers()
        val auth: Authentication = SecurityContextHolder.getContext().authentication
        val userDto = userService.findUserByEmail(auth.name)!!

        val userForm = UserCommandForm(userList)

        modelAndView.addObject("userName", userDto.getFullName())
        modelAndView.addObject("userModel", userForm)
        return modelAndView
    }

    @GetMapping("/delete")
    fun deleteUser(@RequestParam("personId") userName: String): String {
        userService.deleteUserByName(userName)
        println(userName)
        return "redirect:/users"
    }
}