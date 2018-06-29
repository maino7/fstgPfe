/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Device;
import bean.EntiteAdministrative;
import bean.UserStock;
import controller.util.DateUtil;
import controller.util.DeviceUtil;
import controller.util.HashageUtil;
import controller.util.SearchUtil;
import controller.util.SessionUtil;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author CHAACHAI Youssef <youssef.chaachai@gmail.com>
 */
@Stateless
public class UserStockFacade extends AbstractFacade<UserStock> {

    @PersistenceContext(unitName = "Pfe_FstgProjectPU")
    private EntityManager em;

    @EJB
    private DeviceFacade deviceFacade;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserStockFacade() {
        super(UserStock.class);
    }

    @Override
    public UserStock find(Object id) {
        try {
            UserStock userStock = (UserStock) em.createQuery("select u from UserStock u where u.id ='" + id + "'").getSingleResult();
            if (userStock != null) {
                return userStock;
            }
        } catch (Exception e) {
            System.out.println("Makaynch had UserStock");
        }
        return null;
    }

    public int signIn(UserStock userStock) {
        System.out.println("========= Sign In ===========");
        if (userStock == null || userStock.getId() == null) {
            return -5; // You must type your login
        } else {
            UserStock loadUsr = find(userStock.getId());
            System.out.println("loadUsr === " + loadUsr);

            if (loadUsr == null) {
                return -4; // there is no User here
            } else if (!loadUsr.getPassword().equals(HashageUtil.sha256(userStock.getPassword()))) {
                int nbrCnx = loadUsr.getNbrCnx();

                if (nbrCnx < 3) {
                    System.out.println("loadUsr.getNbrCnx == " + loadUsr.getNbrCnx());
                    System.out.println(" This is Your Attempt number : " + nbrCnx);
                    loadUsr.setNbrCnx(nbrCnx + 1);

                } else if (nbrCnx == 3) {  // If the User try more than 3 attempts

                    System.out.println(" This is Your Attempt number == " + nbrCnx);
                    loadUsr.setBlocked(true); // Blocked User
                }

                edit(loadUsr);
                return -3; // Wrong Password
            } else if (loadUsr.isBlocked() == true) {
                SessionUtil.setAttribute("deviceIssue", loadUsr);
                return -2; // UserStock blocked
            } else {
                loadUsr.setNbrCnx(0);

                System.out.println("============= START Create Device ==============");
                Device device = DeviceUtil.getDevice(); // Create Device
                int res = deviceFacade.checkDevice(loadUsr, device); // Check if Device is already Exists
                System.out.println("res for Device === " + res);
                if (res < 0) {
                    // sendEmailRed(user, device);
                    System.out.println("==== res = -6 === ");
                    System.out.println("Device === " + device);
                    System.out.println("Device Browser === " + device.getBrowser());
                    System.out.println("Device Category === " + device.getDeviceCategorie());
                    System.out.println("Device Operating System === " + device.getOperatingSystem());
                    //sendEmailUrgent(loadUsr); // added 16/05/2017 
                    SessionUtil.setAttribute("deviceIssue", loadUsr);
                    return -6; // this device is not included
                }
                System.out.println("=============  END Create Device ==============");
                SessionUtil.registerUser(loadUsr);
                edit(loadUsr);

                return 1;
            }
        }
    }

    public UserStock cloneUserStock(UserStock userStock) {
        UserStock clone = new UserStock();
        clone.setAdmine(userStock.isAdmine());
        clone.setBlocked(userStock.isBlocked());
        clone.setId(userStock.getId());
        clone.setDateNaissance(userStock.getDateNaissance());
        clone.setDevices(userStock.getDevices());
        clone.setEmail(userStock.getEmail());
        clone.setGender(userStock.getGender());
        clone.setMdpChanged(userStock.isMdpChanged());
        clone.setNbrCnx(userStock.getNbrCnx());
        clone.setNom(userStock.getNom());
        clone.setPassword(userStock.getPassword());
        clone.setPrenom(userStock.getPrenom());
        clone.setTelephone(userStock.getTelephone());
        clone.setEntiteAdministrative(userStock.getEntiteAdministrative());
        return clone;
    }

    public List<UserStock> findByEntite(EntiteAdministrative entiteAdministrative) {
        return em.createQuery("SELECT u FROM UserStock u WHERE u.entiteAdministrative.id ='" + entiteAdministrative.getId() + "'").getResultList();
    }

    public int bloquerUser(UserStock userStock) {
        if (userStock != null) {
            if (userStock.isBlocked() == true) {
                userStock.setBlocked(false);
            } else {
                userStock.setBlocked(true);
            }
            return 1;
        }
        return -1;
    }
    //======Methode======//
    public int adminSignUp(UserStock userStock){
        System.out.println("========= Sign In ===========");
        if (userStock == null || userStock.getId() == null) {
            return -5; // You must type your login
        } else {
            UserStock loadUsr = find(userStock.getId());
            System.out.println("loadUsr === " + loadUsr);

            if (loadUsr == null) {
                return -4; // there is no User here
            } else if (!loadUsr.getPassword().equals(HashageUtil.sha256(userStock.getPassword()))) {
                int nbrCnx = loadUsr.getNbrCnx();

                if (nbrCnx < 3) {
                    System.out.println("loadUsr.getNbrCnx == " + loadUsr.getNbrCnx());
                    System.out.println(" This is Your Attempt number : " + nbrCnx);
                    loadUsr.setNbrCnx(nbrCnx + 1);

                } else if (nbrCnx == 3) {  // If the User try more than 3 attempts

                    System.out.println(" This is Your Attempt number == " + nbrCnx);
                    
                }

                edit(loadUsr);
                return -3; // Wrong Password
            } else {
                loadUsr.setNbrCnx(0);

                SessionUtil.registerUser(loadUsr);
                edit(loadUsr);

                return 1;
            }
        }
    }
    
    
    //======test======//
}
