package com.xadmin.DepartmentalStore.service;

import com.xadmin.DepartmentalStore.bean.BackOrder;
import com.xadmin.DepartmentalStore.bean.Customer;
import com.xadmin.DepartmentalStore.bean.Order;
import com.xadmin.DepartmentalStore.bean.Product;
import com.xadmin.DepartmentalStore.repository.CustomerRepository;
import com.xadmin.DepartmentalStore.repository.OrderRepository;
import com.xadmin.DepartmentalStore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService {
    @Autowired
    public OrderRepository orderRepo;
    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private CustomerRepository custRepo;

    @Autowired
    private BackOrderService backServe;

//    HashMap<Long, List<Order>> mp = new HashMap<>();

    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        orderRepo.findAll();
        return orders;
    }

    public void updateDetails(Order order) {
        Customer cust = custRepo.findById(order.getCustomer().getCustomerId()).orElse(null);
        Product pro = productRepo.findById(order.getProduct().getProductId()).orElse(null);

        order.setCustomer(cust);
        order.setProduct(pro);
    }

    public void updateDiscountedPrice(Order order)
    {
        Product product = order.getProduct();
        double totalPrice = product.getPrice() * order.getQuantity();
        System.out.println(totalPrice);
        double discountedPrice = totalPrice - (totalPrice * (order.getDiscount())/100.0);
        System.out.println(discountedPrice);

        order.setDiscountPrice(discountedPrice);
        orderRepo.save(order);

    }

    public void isAvailable(Order order) {

        Product product = order.getProduct();

        Optional<Product> product1 =  productRepo.findById(product.getProductId());


        if (product1.get().getCount() >= order.getQuantity() && product1.get().isAvailability())
        {
            orderRepo.save(order);
            product.setCount(product.getCount() - order.getQuantity());
            productRepo.save(product);

        } else {
            BackOrder backOrder = new BackOrder();
            backOrder.setOrder(order);
            backServe.createBackOrder(backOrder);
            throw new IllegalStateException("Order placed successfully but out of stock. We will notify you once it is in stock");
        }
    }

    public void addOrder(Order order) {

        orderRepo.save(order);
        updateDetails(order);

//        it checks the count and availability of the order
        isAvailable(order);
//        update other fields when you order something as other fields might go null due to not entering entire details in JSON data
        updateDiscountedPrice(order);





    }


    public void updateOrder(Long id, Order order) {
        orderRepo.save(order);
    }

    public void deleteOrder(Long id) {
        orderRepo.deleteById(id);
    }


}
//    code where backorder was stored in hashmaps

//        Customer customer = order.getCustomer();
//
//        List<Order> l1 = List.of(order);
//        customer.setOrders(l1);
//        customerRepository.save(customer);
//
//        List<Order> orders = order.getCustomer().getOrders();
//
//        Order orderObj = orders.get(orders.size()-1);
//        int q1 = orderObj.getQuantity();   //customer wants
//        Long orderId = orderObj.getOrderId();
//        long productId = orderObj.getProductId();
//
//        Optional<Product> prod = productService.getProductById(productId);
//        Boolean avail = prod.get().isAvailability();
//        int q2 = prod.get().getCount();  //that we have in our inventory
//
//        if(avail && q2<q1)
//        {
//            List<Order> check =  mp.get(productId);
//
//            if (check==null) {
//                List<Order> backorderList = new ArrayList<>();
//                backorderList.add(order);
//                mp.put(productId, backorderList);
//            } else {
//                mp.get(productId).add(order);
//            }
//
//            return true;
//        }
//
//        else
//        {
//            orderRepo.save(order);
//            return false;
//        }




//    public HashMap<Long, List<Order>> BackOrders(){
//        return mp;
//    }
