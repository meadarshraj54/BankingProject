package repository;

import domain.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerRepo {
    private final Map<String , Customer> customerMap = new HashMap<>();


    public List<Customer> findAll() {
        return new ArrayList<>(customerMap.values());
    }

    public void save(Customer c) {
        customerMap.put(c.id(),c);
    }
}
