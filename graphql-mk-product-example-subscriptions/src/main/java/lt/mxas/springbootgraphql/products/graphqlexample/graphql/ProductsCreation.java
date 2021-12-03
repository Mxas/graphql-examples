package lt.mxas.springbootgraphql.products.graphqlexample.graphql;

import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lt.mxas.springbootgraphql.products.graphqlexample.config.Gr;
import lt.mxas.springbootgraphql.products.graphqlexample.config.Gr.Type;
import lt.mxas.springbootgraphql.products.graphqlexample.model.Product;
import lt.mxas.springbootgraphql.products.graphqlexample.service.ProductService;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class ProductsCreation implements GraphQLMutationResolver {

	private final ProductService productService;


	public String createProduct(Product product) {
		return String.valueOf(productService.create(product));
	}
}
