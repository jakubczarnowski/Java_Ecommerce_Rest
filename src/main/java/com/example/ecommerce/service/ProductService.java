package com.example.ecommerce.service;

import com.example.ecommerce.Utils.ProductMapper;
import com.example.ecommerce.Utils.ProductSort;
import com.example.ecommerce.dto.product.ProductDto;
import com.example.ecommerce.dto.product.ProductEditDto;
import com.example.ecommerce.dto.product.ProductReviewsGetDto;
import com.example.ecommerce.dto.product.ProductsGetDto;
import com.example.ecommerce.exceptions.NotFoundException;
import com.example.ecommerce.model.Category;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.CartRepository;
import com.example.ecommerce.repository.CategoryRepository;
import com.example.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final CartRepository cartRepository;
    private final ProductMapper mapper;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, CartRepository cartRepository, ProductMapper mapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.cartRepository = cartRepository;
        this.mapper = mapper;
    }

    public Product createProductFromDto(ProductDto productDto, Category category) {
        Product updatedProduct = new Product();
        updatedProduct.setName(productDto.getName());
        updatedProduct.setDescription(productDto.getDescription());
        updatedProduct.setPrice(productDto.getPrice());
        updatedProduct.setImagesUrl(productDto.getImagesUrl());
        updatedProduct.setCategory(category);
        updatedProduct.generateSlug(productDto.getName());
        return updatedProduct;
    }

    // Get, {id}
    public Product getProductById(Integer id) {
        Optional<Product> tempProduct = productRepository.findById(id);
        if (tempProduct.isEmpty()) {
            throw new NotFoundException("Product with id " + id + " doesnt exist");
        }
        return tempProduct.get();
    }

    // Get all
    public List<ProductsGetDto> getProducts(int page, int size, String search, Integer categoryId, ProductSort sort) {
        List<Product> productsList = productRepository.findAll();
        List<ProductsGetDto> newProductList = new ArrayList<>();

        // New way to sort things, usefull later but for right now i want to move on
//        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Product_Jpa" );
//        EntityManager entitymanager = emfactory.createEntityManager( );
//        CriteriaBuilder criteriaBuilder = entitymanager.getCriteriaBuilder();
//        CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery();
//        Root<Product> from = criteriaQuery.from(Product.class);
//
//        //select all records
//        CriteriaQuery<Object> select = criteriaQuery.select(from);
//        TypedQuery<Object> typedQuery = entitymanager.createQuery(select);
//        List<Object> resultlist = typedQuery.getResultList();
//
//        Session session = ;
//        CriteriaBuilder cb = session.getCriteriaBuilder();
//        CriteriaQuery<Product> criteriaQuery = cb.createQuery(Product.class);
//        Root<Product> from = criteriaQuery.from(Product.class);
//        CriteriaQuery<Product> select = criteriaQuery.select(from);
//        criteriaQuery.orderBy(cb.asc(from.get("price")));

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        // my own pagination solution, don't try this
        // cant really figure it out the right way
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isEmpty()) {
            throw new NotFoundException("Category with id " + categoryId + " doesnt exist");
        }
        Set<Integer> categoriesId = getInnerCategoriesId(category.get(), new HashSet<Integer>());
        for (Product product : productsList) {
            Boolean isFavorite = product.getUserFavorite().stream().anyMatch(user -> Objects.equals(user.getUsername(), username));
            // category search
            if ((product.toString().contains(search.toLowerCase())) && (categoriesId.contains(product.getCategory().getId()))) {
                newProductList.add(new ProductsGetDto(product, isFavorite));
            }
        }
        int productsSize = newProductList.size();
        int listStart = (page - 1) * size;
        int listEnd = Math.min(page * size, productsSize); // productsSize < page * productsSize : returns products size
        return newProductList.subList(listStart, listEnd);
    }

    private Set<Integer> getInnerCategoriesId(Category category, Set<Integer> categories) {
        categories.add(category.getId());
        Set<Category> categoryChildren = category.getCategoryChildren();
        if (categoryChildren == null) {
            return categories;
        }
        for (Category cat : categoryChildren) {
            categories.add(cat.getId());
            Set<Integer> innerCategories = getInnerCategoriesId(cat, new HashSet<Integer>());
            categories.addAll(innerCategories);
        }
        return categories;
    }

    // Post, creating product
    public Product createProduct(ProductDto product) {
        Optional<Category> tempProduct = categoryRepository.findById(product.getCategoryId());

        if (tempProduct.isEmpty()) {
            throw new NotFoundException("Category with id " + product.getCategoryId() + " doesnt exist");
        }

        Product newProduct = createProductFromDto(product, tempProduct.get());
        return productRepository.save(newProduct);
    }

    // test
    // Delete, {id}
    public void removeProductById(Integer id) {
        Optional<Product> tempProduct = productRepository.findById(id);
        if (tempProduct.isEmpty()) {
            throw new NotFoundException("Product with id " + id + " doesnt exist");
        }
        cartRepository.deleteAllByProductId(tempProduct.get().getId());
        tempProduct.get().getUserFavorite().forEach(user -> user.deleteFavorite(tempProduct.get()));
        productRepository.deleteById(id);
    }

    public Product updateProduct(Integer id, ProductEditDto product) {
        Optional<Product> tempProduct = productRepository.findById(id);

        if (tempProduct.isEmpty()) {
            throw new NotFoundException("Product with id " + id + " doesnt exist");
        }
        mapper.updateProductFromDto(product, tempProduct.get());
        if (product.getCategoryId() != null) {
            Optional<Category> tempCategory = categoryRepository.findById(product.getCategoryId());
            if (tempCategory.isEmpty()) {
                throw new NotFoundException("Category with id " + id + " doesnt exist");
            }
            tempProduct.get().setCategory(tempCategory.get());
        }
        return productRepository.save(tempProduct.get());
    }

    public ProductReviewsGetDto getProductBySlug(String slug) {
        Optional<Product> tempProduct = productRepository.findBySlug(slug);
        if (tempProduct.isEmpty()) {
            throw new NotFoundException("Product with slug " + slug + " doesnt exist");
        }
        ProductReviewsGetDto product = new ProductReviewsGetDto(tempProduct.get(), false);
        return product;

    }
}
