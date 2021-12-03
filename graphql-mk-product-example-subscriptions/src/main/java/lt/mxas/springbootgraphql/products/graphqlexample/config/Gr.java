package lt.mxas.springbootgraphql.products.graphqlexample.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.stereotype.Component;

@Component
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Gr {

	Type type() default Type.Query; // "Mutation", "Subscription"

	String value();

	enum Type {
		Query, Mutation, Subscription
	}
}
