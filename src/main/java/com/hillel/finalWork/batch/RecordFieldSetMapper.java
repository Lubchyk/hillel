package com.hillel.finalWork.batch;

import com.hillel.finalWork.model.Status;
import com.hillel.finalWork.model.Transaction;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class RecordFieldSetMapper implements RowMapper<Transaction> {

//    @Override
//    public Orders mapRow(ResultSet resultSet, int i) throws SQLException {
//        Orders order = new Orders();
//        order.setId(resultSet.getInt("id"));
//        order.setCreated(resultSet.getDate("created"));
//        order.setStatus(Status.valueOf(resultSet.getString("status")));
//        return order;
//    }

    @Override
    public Transaction mapRow(ResultSet resultSet, int i) throws SQLException {
        Transaction transaction = new Transaction();
        transaction.setName(resultSet.getString("name"));
        transaction.setPrice(resultSet.getBigDecimal("price"));
        transaction.setCreated(resultSet.getDate("created"));
        transaction.setStatus(Status.valueOf(resultSet.getString("status")));
        return transaction;
    }
}
