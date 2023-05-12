package lv.venta.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.contoller.Product;
import lv.venta.repos.IProductRepo;
import lv.venta.services.ICRUDProductService;

@Service
public class CRUDproductServiceImpl implements ICRUDProductService{
	
	@Autowired
	private IProductRepo productRepo;
	
	@Override
	public void addNewProduct(String title, String description, float price, int quantity) throws Exception {
		if (productRepo.existsByTitleAndDescriptionAndPrice(title, description, price)) {
			Product temp = productRepo.findByTitleAndDescriptionAndPrice(title, description, price);
			productRepo.save(temp);
			temp.setQuantity(temp.getQuantity() + quantity);

		} else {
			Product newProduct = new Product(title, description, price, quantity);
			productRepo.save(newProduct); // in this case - new product will be saved DB
		}
	}

	@Override
	public ArrayList<Product> retrieveAllProducts() {
		return (ArrayList<Product>) productRepo.findAll();
	}

	@Override
	public Product retrieveProductById(long id) throws Exception {
		if(id > 0) {	
			if(productRepo.existsById(id)) {
				Product temp = productRepo.findById(id).get();
				return temp;
			}
			else {
				throw (new Exception("There is not product with this ID"));
			}
		}
		else {
			throw new Exception("ID need to be positive");
		}
	}

	@Override
	public void updateById(long id, String title, String description, float price, int quantity) throws Exception {
		if(id > 0) {
			if (productRepo.existsByTitleAndDescriptionAndPrice(title, description, price)) {
				if(productRepo.existsById(id)) {
					Product temp = productRepo.findById(id).get();
					temp.setTitle(title);
					temp.setDescription(description);
					temp.setPrice(price);
					temp.setQuantity(quantity);

					productRepo.save(temp);
				}
				else {
					throw new Exception("There is no product with this ID");
				}
			}
				else {
					throw new Exception("ID nees to be positive");
				}	
			}
	}



	@Override
	public void deleteProductById(long id) throws Exception {
		//vajag uztaisit parbaudi vai vispar tads produkts eksiste
		if(productRepo.existsById(id)) {
			productRepo.deleteById(id);
		}
		else {
			throw new Exception("There is no product with this ID");
		}
	}


	
}
