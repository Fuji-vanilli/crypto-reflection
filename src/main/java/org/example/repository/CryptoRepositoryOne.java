package org.example.repository;

import org.example.model.Crypto;
import org.example.persistance.SingletonConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CryptoRepositoryOne implements CryptoRepository, AutoCloseable{
    private final static Connection connection= SingletonConnection.getConnection();
    public static long NUMBER_ELEMENTS= 0;

    static {
        try (PreparedStatement preparedStatement= connection.prepareStatement(
                "SELECT COUNT(*) FROM crypto"
        )) {
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                NUMBER_ELEMENTS= resultSet.getInt(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public Crypto save(Crypto entity) {
        String id= "";
        try (PreparedStatement preparedStatement=
                     connection.prepareStatement("INSERT INTO CRYPTO values(?, ?, ?) ")
        ) {
            preparedStatement.setString(1, id= "CPT-"+(++NUMBER_ELEMENTS));
            preparedStatement.setString(2, entity.getName());
            preparedStatement.setBigDecimal(3, entity.getCurrentValue());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("crypto saved successfully!");

        entity.setId(id);
        return entity;
    }

    @Override
    public Crypto update(Crypto entity) {
        try (PreparedStatement preparedStatement= connection.prepareStatement(
                ""
        )) {

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Optional<Crypto> findById(String id) {
        Crypto crypto= new Crypto();
        try (PreparedStatement preparedStatement= connection.prepareStatement(
                "SELECT * FROM crypto WHERE id= ?"
        )) {
            preparedStatement.setString(1, id);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                crypto.setId(resultSet.getString("ID"));
                crypto.setName(resultSet.getString("NAME"));
                crypto.setCurrentValue(resultSet.getBigDecimal("CURRENT_VALUE"));

            }
            resultSet.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(crypto);
    }

    @Override
    public List<Crypto> findAll() {
        List<Crypto> cryptos= new ArrayList<>();
        try (PreparedStatement preparedStatement= connection.prepareStatement(
                "SELECT * FROM crypto"
        )) {
            ResultSet resultSet= preparedStatement.executeQuery();

            while (resultSet.next()) {
                Crypto crypto= new Crypto(
                        resultSet.getString("NAME"),
                        resultSet.getBigDecimal("CURRENT_VALUE"));
                crypto.setId(resultSet.getString("ID"));
                cryptos.add(crypto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cryptos;
    }

    @Override
    public void delete(String id) {
        try (PreparedStatement preparedStatement= connection.prepareStatement(
                "DELETE FROM crypto WHERE id= ?"
        )) {
            preparedStatement.setString(1, id);
            final int rowCount = preparedStatement.executeUpdate();
            if (rowCount> 0) {
                System.out.println("crypto with the id: "+id+" deleted successfully!");
            } else {
                System.out.println("sorry no crypto with the id: "+id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
