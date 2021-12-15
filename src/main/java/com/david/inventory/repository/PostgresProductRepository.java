package com.david.inventory.repository;

import com.david.inventory.domain.Product;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//@Repository
public class PostgresProductRepository implements ProductsRepository {

    public final DataSource dataSource;

    public PostgresProductRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public List<Product> findAll() {

        List<Product> products = new ArrayList<>();
        String sqlQuery = "select * from products";

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlQuery)
        ) {

            while (resultSet.next()) {
                String productId = resultSet.getString("id_product");
                String productName = resultSet.getString("name");
                String productCategory = resultSet.getString("category");
                String productDescription = resultSet.getString("description");

                Product product = new Product(productId, productName, productCategory, productDescription);

                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    @Override
    public Product findById(String id) {

        Product product = null;
        String sqlQuery = "select * from products where id_product = ?";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement ps = connection.prepareStatement(sqlQuery)
        ) {
            ps.setString(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                String productId = resultSet.getString("id_product");
                String productName = resultSet.getString("name");
                String productCategory = resultSet.getString("category");
                String productDescription = resultSet.getString("description");

                product = new Product(productId, productName, productCategory, productDescription);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }

    @Override
    public List<Product> findByCategory(String category) {

        List<Product> products = new ArrayList<>();
        String sqlQuery = "select * from products where category = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sqlQuery)
        ) {
            ps.setString(1, category);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                String productId = resultSet.getString("id_product");
                String productName = resultSet.getString("name");
                String productCategory = resultSet.getString("category");
                String productDescription = resultSet.getString("description");

                Product product = new Product(productId, productName, productCategory, productDescription);

                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    @Override
    public void create(Product product) {

        String sqlQuery = "insert into products(id_product, name, category, description) values(?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sqlQuery)
        ) {
            String productId = product.getId();
            String productName = product.getName();
            String productCategory = product.getCategory();
            String productDescription = product.getDescription();

            ps.setString(1, productId);
            ps.setString(2, productName);
            ps.setString(3, productCategory);
            ps.setString(4, productDescription);

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(String id, Product product) {

        String sqlQuery = "update persons set name = ?, category = ?, description = ? where id_number = ?";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement ps = connection.prepareStatement(sqlQuery)
        ) {
            String productId = product.getId();
            String productName = product.getName();
            String productCategory = product.getCategory();
            String productDescription = product.getDescription();

            ps.setString(1, productName);
            ps.setString(2, productCategory);
            ps.setString(3, productDescription);
            ps.setString(4, productId);

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(String id) {

        String sqlQuery = "delete from products where id_product = ?";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement ps = connection.prepareStatement(sqlQuery)
        ) {
            ps.setString(1, id);

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
