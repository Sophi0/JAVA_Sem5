package lv.venta.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import lv.venta.contoller.Product;
import lv.venta.services.ICRUDProductService;

@Service
public class CRUDproductServiceImpl implements ICRUDProductService{

	private ArrayList<Product> products = new ArrayList<>(List.of(
			new Product("Orange", "fresh", 3.4f, 10), 
			new Product("Kiwi", "green", 5.5f, 3), 
			new Product("Pear", "yellow", 8.3f, 7)));
	
	@Override
	public void addNewProduct(String title, String description, float price, int quantity) throws Exception {
		//TODO verification with regex title and description
		if(title != null && description != null && price > 0 && price < 10000 && quantity > 0 && quantity < 10000) {	//obligati taisam parbaudi!
			boolean isFound = false;
			for(Product temp : products) {
				if(temp.getTitle().equals(title) && temp.getDescription().equals(description) && temp.getPrice() == price) {
					temp.setQuantity(temp.getQuantity() + quantity);
					isFound = true;
					break;
				}
			}
			if(!isFound) {
				Product newProduct = new Product(title, description, price, quantity);
				products.add(newProduct);
			}
		}
		else {
			throw (new Exception("Wrong parameters"));
		}
		
	}

	@Override
	public ArrayList<Product> retrieveAllProducts() {
		return products;	//vienkarsi atgriezam visu listu ar produktiem
	}

	@Override
	public Product retrieveProductById(long id) throws Exception {
		if(id > 0) {	//obligati taisam parbaudi!
			for(Product temp : products) {
				if(temp.getId() == id) {
					return temp;
				}
			}
			throw (new Exception("There is not product with this ID"));
		}
		else {
			throw (new Exception("ID need to be positive"));
		}
	}

	@Override
	public void updateById(long id, String title, String description, float price, int quantity) throws Exception {
		if(id > 0) {	//obligati taisam parbaudi!
			if(title != null && description != null && price > 0 && price < 10000 && quantity > 0 && quantity < 10000) {	//obligati taisam parbaudi!
				boolean isFound = false;
				for(Product temp : products) {
					if(temp.getId() == id) {
						temp.setTitle(title);
						temp.setDescription(description);
						temp.setPrice(price);
						temp.setQuantity(quantity);
						isFound = true;
						break;
					}
				}
				if(!isFound) {
					throw (new Exception("There is not product with this ID"));
				}
			}
			else {
				throw (new Exception("Incorrect params"));
			}
		}
		else {
			throw (new Exception("There is no product with this ID"));
		}
	}

	@Override
	public void deleteProductById(long id) throws Exception {
		Product deletedProduct = retrieveProductById(id);
		products.remove(deletedProduct);		
	}


	
}
