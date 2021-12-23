package com.david.inventory.repository;

import com.david.inventory.domain.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SpringJdbcProductRepository implements ProductsRepository {

    private final JdbcTemplate jdbcTemplate;

    public SpringJdbcProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Product> rowMapper = (resultSet, rowNum) -> {
        ProductId productId = ProductId.fromString(resultSet.getString("id_product"));
        ProductName productName = new ProductName(resultSet.getString("name"));
        ProductQuantity productQuantity = new ProductQuantity(resultSet.getInt("stock"));
        ProductCategory productCategory = new ProductCategory(resultSet.getString("category"));
        ProductDescription productDescription = new ProductDescription(resultSet.getString("description"));
        return new Product(
                productId,
                productName,
                productQuantity,
                productCategory,
                productDescription
        );
    };

    @Override
    public List<Product> findAll() {
        String sqlQuery = "select * from products ";
        return jdbcTemplate.query(sqlQuery, rowMapper);
    }

    @Override
    public Product findById(ProductId id) {
        String sqlQuery = "select * from products where id_product = ?";
        return jdbcTemplate.queryForObject(sqlQuery, rowMapper, id.toString());
    }

    @Override
    public List<Product> findByCategory(ProductCategory category) {
        String sqlQuery = "select * from products where category = ?";
        return jdbcTemplate.query(sqlQuery, rowMapper, category.toString());
    }

    @Override
    public void create(Product product) {
        String sqlQuery = "insert into products(id_product, name, stock, category, description) values(?, ?, ?, ?, ?)";
        jdbcTemplate.update(sqlQuery, ps -> {
            ps.setString(1, product.getProductId().toString());
            ps.setString(2, product.getProductName().toString());
            ps.setInt(3, product.getProductQuantity().asInteger());
            ps.setString(4, product.getProductCategory().toString());
            ps.setString(5, product.getProductDescription().toString());
        });
    }

    @Override
    public void update(ProductId id, Product product) {
        String sqlQuery = "update products set name = ?, stock = ?, category = ?, description = ? where id_product = ?";
        jdbcTemplate.update(sqlQuery, ps -> {
            ps.setString(1, product.getProductName().toString());
            ps.setInt(2, product.getProductQuantity().asInteger());
            ps.setString(3, product.getProductCategory().toString());
            ps.setString(4, product.getProductDescription().toString());
            ps.setString(5, id.toString());
        });
    }

    @Override
    public void delete(ProductId id) {
        String sqlQuery = "delete from products where id_product = ?";
        jdbcTemplate.update(sqlQuery, id.toString());
    }
}
