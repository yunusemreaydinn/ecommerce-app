package com.EmreAydin.ecommerceapi.Service;

import com.EmreAydin.ecommerceapi.Models.DAO.OnlineOrderDAO;
import com.EmreAydin.ecommerceapi.Models.OnlineOrder;
import com.EmreAydin.ecommerceapi.Models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private OnlineOrderDAO onlineOrderDAO;


    public OrderService(OnlineOrderDAO onlineOrderDAO) {
        this.onlineOrderDAO = onlineOrderDAO;
    }

    public List<OnlineOrder> getOrders(User user) {
        return onlineOrderDAO.findByUser(user);
    }

}
