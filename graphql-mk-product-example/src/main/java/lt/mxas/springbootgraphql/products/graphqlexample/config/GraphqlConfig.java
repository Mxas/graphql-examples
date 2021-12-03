package lt.mxas.springbootgraphql.products.graphqlexample.config;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

import graphql.GraphQL;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import graphql.schema.idl.TypeRuntimeWiring.Builder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.io.Resource;

@Configuration
public class GraphqlConfig {

	@Autowired
	private ApplicationContext applicationContext;

	@Value("classpath:graphql/products.graphqls")
	private Resource products;

	@Bean
	public GraphQL graphQL() throws IOException {
		GraphQLSchema graphQLSchema = buildSchema();
		return GraphQL
				.newGraphQL(graphQLSchema)
				.build();
	}

	private GraphQLSchema buildSchema() throws IOException {
		SchemaParser parser = new SchemaParser();
		TypeDefinitionRegistry typeRegistry = new TypeDefinitionRegistry();
		typeRegistry.merge(parser.parse(products.getFile()));

		RuntimeWiring runtimeWiring = buildWiring();
		SchemaGenerator schemaGenerator = new SchemaGenerator();
		return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
	}
	private RuntimeWiring buildWiring() {
		RuntimeWiring.Builder builder = RuntimeWiring.newRuntimeWiring();
		getTypeBuilders().forEach(typeBuilder -> {
			builder.type(typeBuilder);
		});
		return builder.build();
	}

	private List<Builder> getTypeBuilders() {
		Map<String, Builder> wiring = new HashMap<>();

		applicationContext.getBeansWithAnnotation(Gr.class).forEach((key, value) -> {
			Gr g = AnnotationUtils.getAnnotation(value.getClass(), Gr.class);
			Builder typeWiring = wiring.get(g.type().name());
			if (typeWiring == null) {
				typeWiring = newTypeWiring(g.type().name());
				wiring.put(g.type().name(), typeWiring);
			}

			typeWiring.dataFetcher(g.value(), (DataFetcher<?>) value);
		});
		return new ArrayList<>(wiring.values());
	}

}
