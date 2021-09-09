import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class itemDAO {
    private static final EntityManagerFactory MANAGER_FACTORY = Persistence.createEntityManagerFactory("item");

    @PersistenceContext
    private EntityManager entityManager;
    public  int add(item Item){
        entityManager = MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        entityManager.persist(Item);
        entityTransaction.commit();

        return Item.getId();

    }

    public  void edit(int id,item Item){
        entityManager = MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        item newItem = entityManager.find(item.class, id);
        newItem.setPrice(Item.getPrice());
        newItem.setInfo(Item.getInfo());
        newItem.setName(Item.getName());
        entityManager.persist(newItem);
        entityTransaction.commit();

    }

    public  void delete(int id){
        entityManager = MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        item Item = entityManager.find(item.class, id);
        entityManager.remove(Item);
        entityTransaction.commit();

    }

    public List<item> getALL(){

        EntityManager entityManager = MANAGER_FACTORY.createEntityManager();

        String query = "SELECT i FROM item i ";
        TypedQuery<item> typedQuery = entityManager.createQuery(query, item.class);
        List<item> items = typedQuery.getResultList();

        return  items;

    }

    public item getField(int id){

        EntityManager entityManager = MANAGER_FACTORY.createEntityManager();

        String query = "SELECT i FROM item i WHERE i.id = :id1";
        TypedQuery<item> typedQuery = entityManager.createQuery(query, item.class);
        typedQuery.setParameter("id1",id);
        item items = typedQuery.getSingleResult();

        return  items;

    }

}
