package com.hackware.mormont.shop.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class ShopApplicationConfig {

    @Bean
    fun api(): Docket =
            Docket(DocumentationType.SWAGGER_2)
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("com.hackware.mormont.shop.controller.rest"))
                    .paths(PathSelectors.any())
                    .build().apiInfo(apiEndPointInfo())


    private fun apiEndPointInfo() =
            ApiInfoBuilder()
                    .title("Spring Boot Shop Application Api Documentation")
                    .description("Demo project for studying Spring Boot Framework")
                    .contact(Contact("LinDevHard", "github.com/lindevhard", "lindevhard@gmail.com"))
                    .license("Apache 2.0")
                    .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                    .version("1.0.0")
                    .build()

}