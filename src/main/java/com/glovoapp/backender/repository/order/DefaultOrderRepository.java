package com.glovoapp.backender.repository.order;

import com.glovoapp.backender.domain.Order;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Component
class DefaultOrderRepository implements OrderRepository {
    private static final String ORDERS_FILE = "/orders.json";
    private static final List<Order> orders;

    static {
        try (Reader reader = new InputStreamReader(DefaultOrderRepository.class.getResourceAsStream(ORDERS_FILE))) {
            Type type = new TypeToken<List<Order>>() {
            }.getType();
            orders = new Gson().fromJson(reader, type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Order> findAll() {
        return new ArrayList<>(orders);
    }
}
