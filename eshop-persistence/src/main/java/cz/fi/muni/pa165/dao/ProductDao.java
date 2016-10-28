package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entity.Product;
import cz.fi.muni.pa165.entity.User;

import java.util.Date;
import java.util.List;

public interface ProductDao
{
	public void create(Product product);
	public void delete(Product o)  throws IllegalArgumentException;
	public List<Product> findAll();
	public Product findById(Long id);

}
