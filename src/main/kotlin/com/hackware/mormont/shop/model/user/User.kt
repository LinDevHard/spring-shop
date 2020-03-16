package com.hackware.mormont.shop.model.user

import com.hackware.mormont.shop.dto.user.UserDto
import com.hackware.mormont.shop.model.cart.Cart
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
                joinColumns = [JoinColumn(name = "uid", referencedColumnName = "uid")],
                inverseJoinColumns = [JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")])
        var roles: Set<Role>,

        @OneToOne(mappedBy = "owner", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
        var cart: Cart? = null,

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var uid: Long = 0L
){
    override fun toString(): String {
        return "User(" +
                "id=$uid " +
                "name=$firstName  $lastName, " +
                "email=$email, " +
                "phone=$phone, " +
                "password=$password," +
                "roles=$roles"
    }
}

fun User.mapToDTO() = UserDto(
        this.email,
        this.password,
        this.firstName,
        this.lastName,
        this.phone,
        null,
        this.roles.map { it.mapToRoleDto().role }
)