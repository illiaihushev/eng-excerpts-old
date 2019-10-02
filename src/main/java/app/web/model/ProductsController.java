package app.web.model;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductsController {
    @PostMapping
    public ResponseEntity<?> postProducts(@RequestBody Product product) {
        return ResponseEntity.ok(new Product(10, "prrresr2", 12, 112,12,13));
    }
}
