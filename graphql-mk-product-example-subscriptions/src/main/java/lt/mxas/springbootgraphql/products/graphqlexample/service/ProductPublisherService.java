package lt.mxas.springbootgraphql.products.graphqlexample.service;

import java.util.concurrent.Flow.Publisher;
import java.util.concurrent.SubmissionPublisher;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import lt.mxas.springbootgraphql.products.graphqlexample.model.Product;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProductPublisherService {
	private final SubmissionPublisher<Product> publisher = new SubmissionPublisher<>();

	public void submit(Product change) {
		try {
			publisher.submit(change);
		} catch (Exception e) {
			log.error("er", e);
		}
	}

	public Publisher<Product> getPublisher() {
		return publisher;
	}
}
