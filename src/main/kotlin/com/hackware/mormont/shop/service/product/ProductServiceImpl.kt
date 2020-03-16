package com.hackware.mormont.shop.service.product

import com.hackware.mormont.shop.dto.product.ProductDetailDto
import com.hackware.mormont.shop.dto.product.ProductDto
import com.hackware.mormont.shop.dto.request.NewProductRequest
import com.hackware.mormont.shop.dto.request.PagingDto
import com.hackware.mormont.shop.dto.response.PageInfo
import com.hackware.mormont.shop.dto.response.PagingList
import com.hackware.mormont.shop.exception.EntityType
import com.hackware.mormont.shop.exception.ExceptionType
import com.hackware.mormont.shop.model.product.Product
import com.hackware.mormont.shop.model.product.mapToProductDetailDto
import com.hackware.mormont.shop.model.product.mapToProductDto
import com.hackware.mormont.shop.repository.BrandRepository
import com.hackware.mormont.shop.repository.CurrencyRepository
import com.hackware.mormont.shop.repository.ProductRepository
import com.hackware.mormont.shop.util.exception
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class ProductServiceImpl : ProductService {
    @Autowired
    private lateinit var productRepository: ProductRepository

    @Autowired
    private lateinit var currencyRepository: CurrencyRepository

    @Autowired
    private lateinit var brandRepository: BrandRepository


    override fun getAllProduct(pagingDto: PagingDto): PagingList<ProductDto> {
        val paging = PageRequest.of(pagingDto.page - 1, pagingDto.limit)
        val page = productRepository.findAll(paging)
        val pageInfo = PageInfo(
                page.totalElements,
                page.size,
                page.pageable.pageNumber + 2,
                page.hasNext()
        )

        return PagingList<ProductDto>(
                page.content.map { product ->
                    product.mapToProductDto()
                },
                pageInfo
        )
    }


    override fun addNewProduct(productDto: NewProductRequest) {
        val currency = currencyRepository.findById(productDto.currency)
        val brand = brandRepository.findById(productDto.productBrand)

        if (currency.isEmpty)
            throw exception(EntityType.CURRENCY, ExceptionType.ENTITY_NOT_FOUND, productDto.currency.toString())
        if (brand.isEmpty)
            throw exception(EntityType.BRAND, ExceptionType.ENTITY_NOT_FOUND, productDto.productBrand.toString())

        val product = Product(
                productDto.name,
                productDto.description,
                productDto.productFeatures,
                productDto.productPrice,
                currency.get(),
                brand.get(),
                productDto.productModel
        )

        productRepository.save(product)
    }

    override fun getProductDetail(productId: Long): ProductDetailDto {
       return findProductById(productId).mapToProductDetailDto()
    }

    override fun findProductById(productId: Long): Product {
        val product = productRepository.findById(productId)
        if (product.isEmpty)
            throw exception(EntityType.PRODUCT, ExceptionType.ENTITY_NOT_FOUND, productId.toString())

        return product.get()
    }
}