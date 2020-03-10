package com.hackware.mormont.shop.model.user

import com.hackware.mormont.shop.dto.user.UserDto
import javax.persistence.*

@Entity
@Table(name = "users")
data class User(
        @Column(nullable = false)
        var email: String,

        @Column(nullable = false)
        var password: String,

        var firstName: String,

        var lastName: String,

        var phone: String,

        @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
        @JoinTable(
                name = "user_role",
                joinColumns = [JoinColumn(name = "USER_ID", referencedColumnName = "ID")],
                inverseJoinColumns = [JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")])
        var roles: Set<Role>
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0L
}

fun User.mapToUserDto() = UserDto(
        this.email,
        this.password,
        this.firstName,
        this.lastName,
        this.phone,
        null,
        this.roles.map { it.mapToRoleDto().role }
)