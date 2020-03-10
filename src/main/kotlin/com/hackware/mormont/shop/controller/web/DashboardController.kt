package com.hackware.mormont.shop.controller.web

import com.hackware.mormont.shop.dto.command.PasswordFormCommand
import com.hackware.mormont.shop.dto.command.ProfileFormCommand
import com.hackware.mormont.shop.service.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.ModelAndView
import javax.validation.Valid


@Controller
class DashboardController {

    @Autowired
    private lateinit var userService: UserService


    @GetMapping(value = ["/dashboard"])
    fun openDashboard(): ModelAndView {
        val modelAndView = ModelAndView("dashboard")
        val auth = SecurityContextHolder.getContext().authentication
        val user = userService.findUserByEmail(auth.name)
        modelAndView.addObject("currentUser", user)
        modelAndView.addObject("userName", user?.getFullName())
        return modelAndView
    }

    @GetMapping(value = ["/profile"])
    fun getUserProfile(): ModelAndView {
        val modelAndView = ModelAndView("profile")
        val auth: Authentication = SecurityContextHolder.getContext().authentication
        val userDto = userService.findUserByEmail(auth.name)!!

        val profileFormCommand = ProfileFormCommand(
                userDto.firstName,
                userDto.lastName,
                userDto.mobilePhone
        )

        val passwordFormCommand = PasswordFormCommand(
                userDto.email,
                userDto.password!!
        )

        modelAndView.addObject("profileForm", profileFormCommand)
        modelAndView.addObject("userName", userDto.getFullName())
        modelAndView.addObject("passwordForm", passwordFormCommand)
        return modelAndView
    }

    @PostMapping(value = ["/profile"])
    fun updateProfile(@Valid @ModelAttribute("profileForm") profileFormCommand: ProfileFormCommand, bindingResult: BindingResult): ModelAndView {
        val modelAndView = ModelAndView("profile")
        val auth = SecurityContextHolder.getContext().authentication
        val userDto = userService.findUserByEmail(auth.name)!!
        val passwordFormCommand = PasswordFormCommand(
                userDto.email,
                userDto.password!!)

        modelAndView.addObject("passwordForm", passwordFormCommand)
        modelAndView.addObject("userName", userDto.getFullName())
        if (!bindingResult.hasErrors()) {
            userDto.also { user ->
                user.firstName = profileFormCommand.firstName
                user.lastName = profileFormCommand.lastName
                user.mobilePhone = profileFormCommand.phone
            }
            userService.updateProfile(userDto)
            modelAndView.addObject("userName", userDto.getFullName())
        }
        return modelAndView
    }

    @PostMapping(value = ["/password"])
    fun changePassword(@Valid @ModelAttribute("passwordForm") passwordFormCommand: PasswordFormCommand, bindingResult: BindingResult): ModelAndView? {
        val auth = SecurityContextHolder.getContext().authentication
        val userDto = userService.findUserByEmail(auth.name)!!
        return if (bindingResult.hasErrors()) {
            val modelAndView = ModelAndView("profile")
            val profileFormCommand: ProfileFormCommand = ProfileFormCommand(
                    userDto.firstName,
                    userDto.lastName,
                    userDto.mobilePhone)

            modelAndView.addObject("profileForm", profileFormCommand)
            modelAndView.addObject("userName", userDto.getFullName())
            modelAndView
        } else {
            userService.changePassword(userDto, passwordFormCommand.password)
            ModelAndView("login")
        }
    }
}