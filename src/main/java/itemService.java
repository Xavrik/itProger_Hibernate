import java.util.List;

public class itemService {

    private static final itemDAO _itemDAO = new itemDAO();

    public  static  int add(item Item){
        return _itemDAO.add(Item);
    }
    public  static  void edit (int id,item Item){
       _itemDAO.edit(id, Item);
    }

    public  static  void delete (int id){
        _itemDAO.delete(id);
    }

    public  static List<item> getALL (){
        return _itemDAO.getALL();
    }

    public  static item getField (int id){
        return _itemDAO.getField(id);
    }







}
