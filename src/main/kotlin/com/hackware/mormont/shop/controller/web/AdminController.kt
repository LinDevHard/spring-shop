package com.hackware.mormont.shop.controller.web

import com.hackware.mormont.shop.dto.command.AdminSignupFormCommand
import com.hackware.mormont.shop.dto.user.UserDto
import com.hackware.mormont.shop.dto.user.UserRoles
import com.hackware.mormont.shop.service.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.ModelAndView
import javax.validation.Valid


@Controller
class AdminController {
    @Autowired
    private lateinit var userService: UserService


    @GetMapping(value = ["/", "/login"])
    fun login(): ModelAndView {
        return ModelAndView("login")
    }


    @GetMapping(value = ["/logout"])
    fun logout(): String {
        SecurityContextHolder.getContext().authentication = null
        return "redirect:login"
    }

    @GetMapping(value = ["/signup"])
    fun signup(): ModelAndView? {
        val modelAndView = ModelAndView("signup")
        modelAndView.addObject("adminSignupFormData", AdminSignupFormCommand())
        return modelAndView
    }

    @PostMapping(value = ["/signup"])
    fun createNewAdmin(@Valid @ModelAttribute("adminSignupFormData") adminSignupFormCommand: AdminSignupFormCommand, bindingResult: BindingResult): ModelAndView? {
        val modelAndView = ModelAndView("signup")
        if (bindingResult.hasErrors()) {
            return modelAndView
        } else {
            try {
                registerAdmin(adminSignupFormCommand)
            } catch (exception: Exception) {
                bindingResult.rejectValue("email", "error.adminSignupFormCommand", exception.message!!)
                return modelAndView
            }
        }
        return ModelAndView("login")
    }

    private fun registerAdmin(@Valid adminSignupFormCommand: AdminSignupFormCommand) {
        val userDto = UserDto(
                adminSignupFormCommand.email!!,
                adminSignupFormCommand.password!!,
                adminSignupFormCommand.firstName!!,
                adminSignupFormCommand.lastName!!,
                adminSignupFormCommand.phone!!,
                true,
                listOf(UserRoles.ADMIN.name)
        )

        userService.signup(userDto)
    }

}