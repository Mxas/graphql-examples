package lt.mxas.springbootgraphql.products.graphqlexample.controller;

import static org.springframework.http.HttpStatus.CREATED;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lt.mxas.springbootgraphql.products.graphqlexample.model.Product;
import lt.mxas.springbootgraphql.products.graphqlexample.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("products")
public class ProductController {

	private final ProductService productService;

	@GetMapping
	public List<Product> all() {
		return productService.findProducts();
	}

	@PostMapping
	@ResponseStatus(CREATED)
	public Long create(@RequestBody Product product) {
		return productService.create(product);
	}

}
