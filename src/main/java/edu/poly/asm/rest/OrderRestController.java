package edu.poly.asm.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.poly.asm.model.Orderdetails;
import edu.poly.asm.model.Orders;
import edu.poly.asm.repository.OrderdetailsRepository;
import edu.poly.asm.service.OrderService;

@CrossOrigin("*")
@RestController
@RequestMapping("admin")
public class OrderRestController {

    @Autowired
    OrderService orderService;
    @Autowired
    OrderdetailsRepository orderDetailsRepository;

    @GetMapping("orders")
    public Page<Orders> getAll(@RequestParam("page") int page, @RequestParam("size") int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return orderService.findAll(pageable);
    }

    @GetMapping("orders/details/{id}")
    public ResponseEntity<List<Orderdetails>> getOne(@PathVariable("id") Integer id) {
        Optional<Orders> orderOptional = orderService.findById(id);
        if (orderOptional.isPresent()) {
            return ResponseEntity.ok(orderDetailsRepository.findAllByOrderid_Orderid(id));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
