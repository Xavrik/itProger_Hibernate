import javax.persistence.*;
import java.util.List;

public class Main {

    private static final EntityManagerFactory MANAGER_FACTORY = Persistence.createEntityManagerFactory("item");
    public static void main(String[] args){
        //addItems("Diebold C2070", 32000, "CashIn");
//        addItems("Diebold DN200", 36000, "FrontLoad");
//        addItems("Diebold DN450H", 38000, "RearLoad");
//        addItems("Diebold C4560", 28000, "hardworker");
        //getItems();
        //getItem( 3);
       // deleteItem(3);

        editItem(3, 34000, "RearLoad Discount");


        MANAGER_FACTORY.close();
    }

    private static void editItem(int id, int price, String info) {
        EntityManager entityManager = MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = null;
        item Item = null;
        try {
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();

            Item = entityManager.find(item.class, id);
            Item.setInfo(info);
            Item.setPrice(price);

            entityManager.persist(Item);
            entityTransaction.commit();

        } catch (Exception e) {
            if(entityTransaction != null)
                entityTransaction.rollback();
            e.printStackTrace();
        }finally {
            entityManager.close();
        }

    }

    private static void deleteItem(int id) {
        EntityManager entityManager = MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = null;

        item Item = null;
        try {
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();

            Item = entityManager.find(item.class, 1);
            entityManager.remove(Item);
            entityTransaction.commit();

        } catch (Exception e) {
            if(entityTransaction != null)
                entityTransaction.rollback();
            e.printStackTrace();
        }finally {
            entityManager.close();
        }
    }

    private static void getItem(int id) {
        EntityManager entityManager = MANAGER_FACTORY.createEntityManager();

        String query = "SELECT i FROM item i WHERE i.id > :id1";
        TypedQuery<item> typedQuery = entityManager.createQuery(query, item.class);
        typedQuery.setParameter("id1",id);
        item items = null;

        try{
            items = typedQuery.getSingleResult();
                System.out.println("Name: " + items.getName() +
                        ", price: " + items.getPrice() +
                        ", info: " + items.getInfo());

        } catch (Exception e) {

            e.printStackTrace();
        }finally {
            entityManager.close();
        }
    }


    private static void getItems() {
        EntityManager entityManager = MANAGER_FACTORY.createEntityManager();

        String query = "SELECT i FROM item i WHERE i.id > :id1 AND i.id < :id2";
        TypedQuery<item> typedQuery = entityManager.createQuery(query, item.class);
        typedQuery.setParameter("id1",1);
        typedQuery.setParameter("id2", 4);
        List<item> items;

        try{
            items = typedQuery.getResultList();
            for (item Item: items)
                System.out.println("Name: " + Item.getName() +
                        ", price: " + Item.getPrice() +
                        ", info: " + Item.getInfo());

        } catch (Exception e) {

            e.printStackTrace();
        }finally {
            entityManager.close();
        }
    }

    public static void addItems (String name, int price, String info){
            EntityManager entityManager = MANAGER_FACTORY.createEntityManager();

            EntityTransaction entityTransaction = null;

            try{
                entityTransaction = entityManager.getTransaction();
                entityTransaction.begin();

                item  Item = new item(name, price, info);
                entityManager.persist(Item);
                entityTransaction.commit();
            } catch (Exception e) {
                if(entityTransaction != null)
                    entityTransaction.rollback();
                e.printStackTrace();
            } finally {
                entityManager.close();
            }

        }


}
