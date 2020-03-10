package com.hackware.mormont.shop.model.user

import com.hackware.mormont.shop.dto.user.RoleDto
import javax.persistence.*


@Entity
@Table(name = "roles")
data class Role(
        var name: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    var id: Long = 0L

    @ManyToMany(mappedBy = "roles")
    private val users: List<User>? = null
}

fun Role.mapToRoleDto() =
        RoleDto(
                this.name
        )
