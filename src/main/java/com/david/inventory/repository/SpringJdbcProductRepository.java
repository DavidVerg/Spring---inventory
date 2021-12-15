package com.david.inventory.repository;

import com.david.inventory.domain.Product;
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
        String productId = resultSet.getString("id_product");
        String productName = resultSet.getString("name");
        String productCategory = resultSet.getString("category");
        String productDescription = resultSet.getString("description");
        return new Product(
                productId,
                productName,
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
    public Product findById(String id) {
        String sqlQuery = "select * from products where id_product = ?";
        return jdbcTemplate.queryForObject(sqlQuery, rowMapper, id);
    }

    @Override
    public List<Product> findByCategory(String category) {
        String sqlQuery = "select * from products where category = ?";
        return jdbcTemplate.query(sqlQuery, rowMapper, category);
    }

    @Override
    public void create(Product product) {
        String sqlQuery = "insert into products(id_product, name, category, description) values(?, ?, ?, ?)";
        jdbcTemplate.update(sqlQuery, ps -> {
            ps.setString(1, product.getId());
            ps.setString(2, product.getName());
            ps.setString(3, product.getCategory());
            ps.setString(4, product.getDescription());
        });
    }

    @Override
    public void update(String id, Product product) {
        String sqlQuery = "update products set name = ?, category = ?, description = ? where id_product = ?";
        jdbcTemplate.update(sqlQuery, ps -> {
            ps.setString(1, product.getName());
            ps.setString(2, product.getCategory());
            ps.setString(3, product.getDescription());
            ps.setString(4, id);
        });
    }

    @Override
    public void delete(String id) {
        String sqlQuery = "delete from products where id_product = ?";
        jdbcTemplate.update(sqlQuery, id);
    }
}
