package com.EmreAydin.ecommerceapi.Models.DAO;

import com.EmreAydin.ecommerceapi.Models.OnlineOrder;
import com.EmreAydin.ecommerceapi.Models.User;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface OnlineOrderDAO extends ListCrudRepository<OnlineOrder, Long> {

    List<OnlineOrder> findByUser(User user);

}
