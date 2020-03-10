package com.hackware.mormont.shop.repository

import com.hackware.mormont.shop.model.user.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RoleRepository : JpaRepository<Role, Long> {

    fun findByName(role: String): Role?

}