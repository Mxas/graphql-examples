package lt.mxas.springbootgraphql.products.graphqlexample.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

	private Long id;
	private String name;
	private String type;
}
