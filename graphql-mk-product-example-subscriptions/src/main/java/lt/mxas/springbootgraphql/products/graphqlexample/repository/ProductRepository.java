package lt.mxas.springbootgraphql.products.graphqlexample.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import javax.annotation.PostConstruct;
import lt.mxas.springbootgraphql.products.graphqlexample.model.Product;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {

	private List<Product> storage = new ArrayList<>();

	@PostConstruct
	void init() {
		IntStream.range(0, 10).forEach(i -> saveNew(Product.builder().name("Product #" + i).type("dummy").build()));
	}

	public void saveNew(Product product) {
		product.setId(this.storage.stream().mapToLong(Product::getId).max().orElse(0L) + 1L);
		this.storage.add(product);
	}

	public List<Product> finsAll() {
		return this.storage;
	}
}
