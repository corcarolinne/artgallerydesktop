
package CustomerDashboard;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Login.LoginController;

public class CustomerDashboardController implements ActionListener {
    
    // This class has to have a reference to all elements of the program
    public CustomerDashboardModel model;
    CustomerDashboardView view;
    
    public CustomerDashboardController(){
        System.out.println("entrou no CONT");
        
        model = new CustomerDashboardModel();
        view = new CustomerDashboardView(this);
        
        
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        
    }
      
    
}
