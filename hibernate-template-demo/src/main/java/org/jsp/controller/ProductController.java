package org.jsp.controller;

import java.util.List;
import java.util.Scanner;

import org.jsp.dao.ProductDao;
import org.jsp.dto.Product;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProductController {
	static ProductDao dao;
	static Scanner sc = new Scanner(System.in);
	static {
		ApplicationContext context = new ClassPathXmlApplicationContext("product-cfg.xml");
		dao = context.getBean(ProductDao.class);
	}

	public static void main(String[] args) {
		while (true) {
			System.out.println("1.save Product\n2.Update Product\n3.Find Product By Id");
			System.out.println("4.Display the Products\n5.delete the Product");
			switch (sc.nextInt()) {
			case 1: {
				save();
				break;
			}
			case 2: {
				update();
				break;
			}
			case 3: {
				findId();
				break;
			}
			case 4: {
				allproducts();
				break;
			}
			case 5: {
				delete();
				break;
			}
			}

		}
	}

	static void save() {
		System.out.println("Enter Name,Brand,Category,Description & Cost to save the products");
		Product p = new Product();
		p.setName(sc.next());
		p.setBrand(sc.next());
		p.setCategory(sc.next());
		p.setDescription(sc.next());
		p.setCost(sc.nextDouble());
		p = dao.save(p);
		System.out.println("Product is saved with the Id:" + p.getId());
	}

	static void update() {
		System.out.println("Enter the Id to update");
		int id = sc.nextInt();
		System.out.println("Enter Name,Brand,Category,Description & Cost to update the products");
		Product p = new Product();
		p.setName(sc.next());
		p.setBrand(sc.next());
		p.setCategory(sc.next());
		p.setDescription(sc.next());
		p.setCost(sc.nextDouble());
		p.setId(id);
		p = dao.update(p);
		if (p != null) {
			System.out.println("Updated Successfully");
			System.out.println("Product Name:" + p.getName());
			System.out.println("Barand:" + p.getBrand());
			System.out.println("Category:" + p.getCategory());
			System.out.println("Description:" + p.getDescription());
			System.out.println("Cost:" + p.getCost());
		} else {
			System.err.println("Cannot Update Product As ID is invalid");
		}
	}

	static void findId() {
		System.out.println("Enter the Id to find The Products");
		int id = sc.nextInt();
		Product p = dao.findById(id);
		if (p != null) {
			System.out.println("Id is Found");
			System.out.println("Product Name:" + p.getName());
			System.out.println("Barand:" + p.getBrand());
			System.out.println("Category:" + p.getCategory());
			System.out.println("Description:" + p.getDescription());
			System.out.println("Cost:" + p.getCost());
		} else {
			System.err.println(" Invalid Id");
		}
	}

	static void allproducts() {
		List<Product> products = dao.findAll();
		for (Product p : products) {
			System.out.println("Product Name:" + p.getName());
			System.out.println("Barand:" + p.getBrand());
			System.out.println("Category:" + p.getCategory());
			System.out.println("Description:" + p.getDescription());
			System.out.println("Cost:" + p.getCost());
			System.out.println("-------------------------------------------");
		}
	}

	static void delete() {
		System.out.println("Enter the Id to delte the product");
		int id = sc.nextInt();
		boolean deleted = dao.delete(id);
		if (deleted) {
			System.out.println("Product deleted");
		} else {
			System.out.println("Cannot delete the product as id is Invalid");
		}
	}
}
