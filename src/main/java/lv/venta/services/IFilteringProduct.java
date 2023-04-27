package lv.venta.services;

import java.util.ArrayList;

import lv.venta.contoller.Product;

public interface IFilteringProduct {
	//filter product: price less than x
	public abstract ArrayList<Product> filterProductBySmallerPrice(float priceThreshold) throws Exception;
	
	//filter product: quantity less than x
	public abstract ArrayList<Product> filterProductBySmallerQuantity(int quantityThreshold) throws Exception;

	//TODO asc or dsc
	//filter product: sorting
	public abstract ArrayList<Product> sort();
	
}
