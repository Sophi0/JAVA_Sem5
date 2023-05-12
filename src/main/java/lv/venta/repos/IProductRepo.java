package lv.venta.repos;

import org.springframework.data.repository.CrudRepository;

import lv.venta.contoller.Product;

public interface IProductRepo extends CrudRepository<Product, Long> {	//jo long ir datu tips ID, bet jabut referencu tips, tapec no liela burta

}
