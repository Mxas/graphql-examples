package lt.mxas.springbootgraphql.products.graphqlexample.graphql;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lt.mxas.springbootgraphql.products.graphqlexample.config.Gr;
import lt.mxas.springbootgraphql.products.graphqlexample.model.Product;
import lt.mxas.springbootgraphql.products.graphqlexample.service.ProductService;


@Gr("findProducts")
@RequiredArgsConstructor
public class ProductsResolver implements DataFetcher<List<Product>> {

	private final ProductService productService;

	@Override
	public List<Product> get(DataFetchingEnvironment environment) {
		return productService.findProducts();
	}
}
