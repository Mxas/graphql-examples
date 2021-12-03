package lt.mxas.springbootgraphql.products.graphqlexample.graphql;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lt.mxas.springbootgraphql.products.graphqlexample.config.Gr;
import lt.mxas.springbootgraphql.products.graphqlexample.config.Gr.Type;
import lt.mxas.springbootgraphql.products.graphqlexample.model.Product;
import lt.mxas.springbootgraphql.products.graphqlexample.service.ProductService;


@Gr(value = "createProduct", type = Type.Mutation)
@RequiredArgsConstructor
public class ProductsCreation implements DataFetcher<Long> {

	private final ProductService productService;

	@Override
	public Long get(DataFetchingEnvironment environment) {
		Map<String, Object> product = environment.getArgument("product");
		return productService.create(Product.builder().name((String) product.get("name")).type((String) product.get("type")).build());
	}
}
