package lt.mxas.springbootgraphql.products.graphqlexample.graphql;

import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.kickstart.tools.GraphQLResolver;
import graphql.schema.DataFetchingEnvironment;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lt.mxas.springbootgraphql.products.graphqlexample.config.Gr;
import lt.mxas.springbootgraphql.products.graphqlexample.model.Product;
import lt.mxas.springbootgraphql.products.graphqlexample.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ProductsResolver implements GraphQLQueryResolver {

	@Autowired
	private ProductService productService;


	public List<Product> findProducts(DataFetchingEnvironment environment) {
		return productService.findProducts();
	}
}
