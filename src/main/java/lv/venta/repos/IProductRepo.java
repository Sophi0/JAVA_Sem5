package lv.venta.repos;

import org.springframework.data.repository.CrudRepository;

import lv.venta.contoller.Product;

public interface IProductRepo extends CrudRepository<Product, Long> {	//jo long ir datu tips ID, bet jabut referencu tips, tapec no liela burta
	//it will generate query with where title = XXX and description = XXX, and price = XXX
	//it will verify if the product is already in DB
	boolean existsByTitleAndDescriptionAndPrice(String inputTitle, String inputDescription, float inputPrice);
	
	//it will return the product from DB
	Product findByTitleAndDescriptionAndPrice(String inputTitle, String inputDescription, float inputPrice);
	
}
