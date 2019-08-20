package org.v.v;

import java.util.Date;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.v.v.V2.V2Builder;

import lombok.AllArgsConstructor;

@SpringBootApplication
public class DemoApplication {

	private Map<String, V<Offer>> offerStore;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	public Offer cheapest(Date date, String od) {
		// find the vertex
		V<Offer> offer = find(date, od);
		return offer.eval().value();
	}

	private V<Offer> find(Date date, String od) {
		String address = od + date.toString();
		V<Offer> v = offerStore.get(address);
		if (v == null) {
			V2Builder<Date, String, Offer> builder = V2.builder();
			builder.f((Date d, String o_d) -> {
				return new Offer(0, d);
			});
			builder.h(0);
			builder.x1(date);
			builder.x2(od);
			v = builder.build();
		}
		return v.eval();
	}
}

class CheapestProduct {
	// public final Date date;
	
}

@AllArgsConstructor
class Offer {
	public final double price;
	public final Date date;
}

@AllArgsConstructor
class Product {
	public final String name;
	public final double rate;
	public final Date date;
}

@AllArgsConstructor
class Products {
	public final Product[] products;
}