package lt.mxas.springbootgraphql.products.graphqlexample.service;

import java.util.List;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lt.mxas.springbootgraphql.products.graphqlexample.model.Product;
import lt.mxas.springbootgraphql.products.graphqlexample.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;
	private final ProductPublisherService publisherService;

	public List<Product> findProducts() {
		return productRepository.finsAll();
	}

	public Long create(Product product) {
		Assert.notNull(product, "Product not provided");
		Assert.notNull(product.getName(), "Product 'name' not provided");
		productRepository.saveNew(product);
		publisherService.submit(product);
		return product.getId();
	}


	@PostConstruct
	void testData() {
		new Thread(() -> {
			while (true) {

				create(Product.builder().type("test").name("message-" + Math.random()).build());

				try {
					Thread.sleep(500L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		})
				.start();
	}


}
