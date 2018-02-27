/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Device;
import bean.User;
import controller.util.DeviceUtil;
import controller.util.HashageUtil;
import controller.util.SessionUtil;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author CHAACHAI Youssef <youssef.chaachai@gmail.com>
 */
@Stateless
public class UserFacade extends AbstractFacade<User> {

    @PersistenceContext(unitName = "Pfe_FstgProjectPU")
    private EntityManager em;

    @EJB
    private DeviceFacade deviceFacade;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }

    @Override
    public User find(Object id) {
        try {
            User user = (User) em.createQuery("select u from User u where u.id ='" + id + "'").getSingleResult();
            if (user != null) {
                return user;
            }
        } catch (Exception e) {
            System.out.println("Makaynch had User");

        }
        return null;
    }

    public int signIn(User user) {
        System.out.println("========= Sign In ===========");
        if (user == null || user.getId() == null) {
            return -5; // You must type your login
        } else {
            User loadUser = find(user.getId());
            System.out.println("loaduser === " + loadUser);

            if (loadUser == null) {
                return -4; // there is no User here
            } else if (!loadUser.getPassword().equals(HashageUtil.sha256(user.getPassword()))) {
                //int nbrCnx = loadUser.getNbrCnx();

//                if (nbrCnx < 3) {
//                  //  System.out.println("loadEns.getNbrCnx == " + loadUser.getNbrCnx());
//                   // System.out.println(" This is Your Attempt number : " + nbrCnx);
//                    //loadUser.setNbrCnx(nbrCnx + 1);
//
//                } else if (nbrCnx == 3) {  // If the User try more than 3 attempts
//
//                    //System.out.println(" This is Your Attempt number == " + nbrCnx);
//              //      loadUser.setBlocked(true); // Blocked User
//                }
                // edit(loadUser);
                return -3; // Wrong Password
            } //            else if (loadUser.isBlocked() == true) {
            //              //  SessionUtil.setAttribute("deviceIssue", loadEns);
            //               // return -2; // Enseignant blocked
            //            }
            //            
            else {
                // loadUser.setNbrCnx(0);

                System.out.println("============= START Create Device ==============");
                Device device = DeviceUtil.getDevice(); // Create Device
                int res = deviceFacade.checkDevice(loadUser, device); // Check if Device is already Exists
                System.out.println("res for Device === " + res);
                if (res < 0) {
                    // sendEmailRed(user, device);
                    System.out.println("==== res = -6 === ");
                    System.out.println("Device === " + device);
                    System.out.println("Device Browser === " + device.getBrowser());
                    System.out.println("Device Category === " + device.getDeviceCategorie());
                    System.out.println("Device Operating System === " + device.getOperatingSystem());
                    //sendEmailUrgent(loadEns); // added 16/05/2017 
                    SessionUtil.setAttribute("deviceIssue", loadUser);
                    return -6; // this device is not included
                }
                System.out.println("=============  END Create Device ==============");
                SessionUtil.registerUser(loadUser);
                edit(loadUser);
                return 1;
            }
        }

    }

    public User cloneUser(User user) {
        User clone = new User();
        clone.setId(user.getId());
        clone.setNom(user.getNom());
        clone.setPrenom(user.getPrenom());
        clone.setPassword(user.getPassword());
        clone.setTelephone(user.getTelephone());
        clone.setCommandes(user.getCommandes());
        clone.setEntiteAdministrative(user.getEntiteAdministrative());
        clone.setExpressionBesoins(user.getExpressionBesoins());
        clone.setLivraisons(user.getLivraisons());
        clone.setReceptions(user.getReceptions());
        return clone;
    }

}
