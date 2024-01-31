package org.jsp.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.jsp.dto.Product;
import org.springframework.orm.hibernate5.HibernateTemplate;

public class ProductDao {
	private HibernateTemplate template;

	public HibernateTemplate getTemplate() {
		return template;
	}

	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}

	@Transactional
	public Product save(Product p) {
		template.save(p);
		return p;
	}

	@Transactional
	public Product update(Product p) {
		Product db = template.get(Product.class, p.getId());
		if (db != null) {
			db.setName(p.getName());
			db.setBrand(p.getBrand());
			db.setCategory(p.getCategory());
			db.setDescription(p.getDescription());
			db.setCost(p.getCost());
			template.update(db);
			return db;
		}
		return null;
	}

	public Product findById(int id) {
		return template.get(Product.class, id);
	}

	public List<Product> findAll() {
		return template.loadAll(Product.class);
	}

	@Transactional
	public boolean delete(int id) {
		Product p = findById(id);
		if (p != null) {
			template.delete(p);
			return true;
		}
		return false;

	}
}
