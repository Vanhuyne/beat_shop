package beatalbumshop.dao;

import beatalbumshop.componment.MyDialog;
import beatalbumshop.config.Account;
import beatalbumshop.model.AddressBook;
import beatalbumshop.model.Customer;
import beatalbumshop.model.User;
import beatalbumshop.utils.TextHelper;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerDAOImpl implements CustomerDAO {
    String projectId = Account.FIREBASE_PROJECT_ID;

    @Override
    public boolean add(Customer customer) {
        Firestore db = Firebase.getFirestore(projectId);
        CollectionReference colRef = db.collection("customers");

        try {
            // Add
            ApiFuture<WriteResult> result = colRef.document(customer.getID()+ "").set(customer);

            System.out.println("Update time : " + result.get().getUpdateTime());
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return false;
    }

    
    
    @Override
    public boolean updateByID(Customer customer) {
        try {
            Firestore db = (Firestore) Firebase.getFirestore(projectId);
            CollectionReference colRef = db.collection("customers");
            DocumentReference docRef = colRef.document(customer.getID() + "");

            // Create a map with the fields to update
            Map<String, Object> updates = new HashMap<>();
            updates.put("email", customer.getEmail());
            updates.put("password", customer.getPassword());

            // (async) Update the document with the new field values
            ApiFuture<WriteResult> result = docRef.update(updates);
            System.out.println("Update time: " + result.get().getUpdateTime());
            
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return false;
    }

    
    
    @Override
    public boolean deleteByID(String customerID) {
        Firestore db = (Firestore) Firebase.getFirestore(projectId);
        CollectionReference colRef = db.collection("customers");

        try {
            ApiFuture<WriteResult> writeResult = colRef.document(customerID).delete();
            System.out.println("Update time : " + writeResult.get().getUpdateTime());
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return false;
    }

    
    
    @Override
    public Customer getByID(String entityID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    

    @Override
    public List<Customer> getAll() {
        Firestore db = (Firestore) Firebase.getFirestore(projectId);
        CollectionReference colRef = db.collection("customers");
        
        ApiFuture<QuerySnapshot> query = colRef.get();
        try {
            QuerySnapshot querySnapshot = query.get();
            List<QueryDocumentSnapshot> customers = querySnapshot.getDocuments();

            List<Customer> lCustomer = new ArrayList<>();
            
            for (QueryDocumentSnapshot customer : customers) {
                DocumentSnapshot customerSnapshot = customer;
                // Convert the document snapshot to a custom class
                Customer customerData = customerSnapshot.toObject(Customer.class);
                // Extract the lAddressBook field from the custom class
                ArrayList<AddressBook> lAddressBook = customerData.getlAddressBook();

                Customer c = new Customer(
                    lAddressBook,
                    customerData.getID(),
                    customerData.getEmail(),
                    customerData.getPassword()
                );

                lCustomer.add(c);
            }
            
            return lCustomer;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return null;
    }

    
    
    @Override
    public boolean checkExitByEmail(String email) {
        Firestore db = (Firestore) Firebase.getFirestore(projectId);
        CollectionReference colRef = db.collection("customers");
        
        ApiFuture<QuerySnapshot> query = colRef.get();
        try {
            QuerySnapshot querySnapshot = query.get();
            List<QueryDocumentSnapshot> customers = querySnapshot.getDocuments();
            
            for (QueryDocumentSnapshot customer : customers) {
                if(customer.getString("email").equalsIgnoreCase(email)) {
                    return false;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return true;
    }
    
    
    
    @Override
    public Customer authentication(String email, String password) {
        Firestore db = (Firestore) Firebase.getFirestore(projectId);
        CollectionReference colRef = db.collection("customers");
        
        ApiFuture<QuerySnapshot> query = colRef.get();
        try {
            QuerySnapshot querySnapshot = query.get();
            List<QueryDocumentSnapshot> customers = querySnapshot.getDocuments();
            
            for (QueryDocumentSnapshot customer : customers) {
                if(customer.getString("email").equalsIgnoreCase(email) && TextHelper.authenticationPasswordHash(password, customer.getString("password"))) {
                    DocumentSnapshot albumSnapshot = customer;
                    // Convert the document snapshot to a custom class
                    Customer albumData = albumSnapshot.toObject(Customer.class);
                    // Extract the lTrack field from the custom class
                    ArrayList<AddressBook> lAddressBook = albumData.getlAddressBook();
                    
                    return new Customer(
                        lAddressBook,
                        customer.getLong("id"),
                        customer.getString("email"),
                        customer.getString("password")
                    );
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return null;
    }

}
