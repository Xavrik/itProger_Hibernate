import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {

    private static final EntityManagerFactory MANAGER_FACTORY = Persistence.createEntityManagerFactory("item");
    public static void main(String[] args){
        addItems("Diebold", 32000, "hardworker");


        MANAGER_FACTORY.close();
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
