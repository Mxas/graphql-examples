package lt.mxas.springbootgraphql.products.graphqlexample.graphql;

import graphql.kickstart.tools.GraphQLSubscriptionResolver;
import lombok.RequiredArgsConstructor;
import lt.mxas.springbootgraphql.products.graphqlexample.model.Product;
import lt.mxas.springbootgraphql.products.graphqlexample.service.ProductPublisherService;
import org.reactivestreams.FlowAdapters;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class ProductsChanges implements GraphQLSubscriptionResolver {

	private final ProductPublisherService productPublisherService;


	public Publisher<Product> productChanges() {
		return FlowAdapters.toPublisher(productPublisherService.getPublisher());
	}
}
