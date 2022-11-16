package br.com.alura.clientelo;

import br.com.alura.clientelo.util.StoreDatabaseUtil;

public class Main {

    public static void main(String[] args) {
//        Director director = new Director();
//        ReportBuilder builder = new ReportBuilder();
//
//        director.constructGeneralReport(builder);
//        builder.buildAndExecute();
//
//        director.constructLoyalCustomers(builder);
//        builder.buildAndExecute();
//
//        director.constructMostExpensveProductsPerCategory(builder);
//        builder.buildAndExecute();
//
//        director.constructMostProfitableCustomers(builder);
//        builder.buildAndExecute();
//
//        director.constructSalesPerCategory(builder);
//        builder.buildAndExecute();
//
//        director.constructTopSellingProducs(builder);
//        builder.buildAndExecute();

//        EntityManager em = JPAUtil.getEntityManager();
//        Category category = new Category("TECH", true);
//
//
//        em.getTransaction().begin();
//        em.persist(category);
//
//        Category cat = em.find(Category.class, 1);
//        Product product = new Product(
//                "nome", BigDecimal.ZERO, "desc", 10, cat
//        );
//        em.persist(product);
//        em.getTransaction().commit();
//        em.close();

        StoreDatabaseUtil db = new StoreDatabaseUtil();
//        db.generateBasicStore();
        db.testReports();
    }
}