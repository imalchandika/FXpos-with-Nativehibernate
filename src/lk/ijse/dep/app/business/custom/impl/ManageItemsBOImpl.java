package lk.ijse.dep.app.business.custom.impl;

import lk.ijse.dep.app.business.Converter;
import lk.ijse.dep.app.business.custom.ManageItemsBO;
import lk.ijse.dep.app.dao.DAOFactory;

import lk.ijse.dep.app.dao.custom.ItemDAO;
import lk.ijse.dep.app.dto.CustomerDTO;
import lk.ijse.dep.app.dto.ItemDTO;
import lk.ijse.dep.app.entity.Item;
import lk.ijse.dep.app.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class ManageItemsBOImpl implements ManageItemsBO {
    ItemDAO itemDAO;
    public ManageItemsBOImpl() {
        itemDAO= DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ITEM);
    }

    @Override
    public List<ItemDTO> getItems() throws Exception {
        Session mySession = HibernateUtil.getSessionFactory().openSession();
        try(Session session = mySession){
            itemDAO.setSession(session);

            session.beginTransaction();
            List<ItemDTO> itemDTOS = itemDAO.findAll().map(Converter::<ItemDTO>getDTOList).get();
            session.getTransaction().commit();
            return itemDTOS;
        }catch(Exception ex){
            mySession.getTransaction().rollback();
            throw ex;
        }
}

    @Override
    public void createItem(ItemDTO dto) throws Exception {
        Session mySession = HibernateUtil.getSessionFactory().openSession();
        try(Session session = mySession){
            itemDAO.setSession(session);
            session.beginTransaction();
            itemDAO.save(Converter.getEntity(dto));
            session.getTransaction().commit();
        }catch(Exception ex){
            mySession.getTransaction().rollback();
            throw ex;
        }
    }

    @Override
    public void updateItem(ItemDTO dto) throws Exception {

    }

    @Override
    public void deleteItem(String code) throws Exception {
        Session mySession = HibernateUtil.getSessionFactory().openSession();
        try(Session session = mySession){
            itemDAO.setSession(session);
            session.beginTransaction();
            itemDAO.delete(code);
            session.getTransaction().commit();
        }catch(Exception ex){
            mySession.getTransaction().rollback();
            throw ex;
        }
    }

    @Override
    public ItemDTO findItem(String itemCode) throws Exception {
     Session mySession = HibernateUtil.getSessionFactory().openSession();
        try(Session session = mySession){
            itemDAO.setSession(session);
            session.beginTransaction();
             ItemDTO itemDTO = itemDAO.find(itemCode).map(Converter::<ItemDTO>getDTO).orElse(null);
            session.getTransaction().commit();
            return itemDTO;
        }catch(Exception ex){
            mySession.getTransaction().rollback();
            throw ex;
        }
    }

//    private ItemDAO itemDAO;
//
//    public ManageItemsBOImpl() {
//        itemDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ITEM);
//    }
//
//    public List<ItemDTO> getItems() throws Exception {
//        return itemDAO.findAll().map(Converter::<ItemDTO>getDTOList).get();
//    }
//
//    public boolean createItem(ItemDTO dto) throws Exception {
//        return itemDAO.save(Converter.getEntity(dto));
//    }
//
//    public boolean updateItem(ItemDTO dto) throws Exception {
//        return itemDAO.update(Converter.getEntity(dto));
//    }
//
//    public boolean deleteItem(String code) throws Exception {
//        return itemDAO.delete(code);
//
//    }
//
//    public ItemDTO findItem(String itemCode) throws Exception {
//        return itemDAO.find(itemCode).map(Converter::<ItemDTO>getDTO).orElse(null);
//    }
}
