package lv.venta.services;

import java.util.ArrayList;

import lv.venta.contoller.Product;

public interface ICRUDProductService {
	//interfeisa vienmer bus public abstract funkcijas
	
	//CRUD
	
	//C - create
	public abstract void addNewProduct(String title, String description, float price, int quantity) throws Exception;
	
	//R - retrieve - all
	public abstract ArrayList<Product> retrieveAllProducts();
	
	//R - retrieve - by id
	public abstract Product retrieveProductById(long id) throws Exception;
	
	//U - update
	public abstract void updateById(long id, String title, String description, float price, int quantity) throws Exception;
	
	//D - delete
	public abstract void deleteProductById(long id) throws Exception;
	
	
}
