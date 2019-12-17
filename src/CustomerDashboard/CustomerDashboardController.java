
package CustomerDashboard;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerDashboardController implements ActionListener {
    
    // This class has to have a reference to all elements of the program
    CustomerDashboardModel model;
    CustomerDashboardView view;
    
    public CustomerDashboardController(){
        view = new CustomerDashboardView(this);
        model = new CustomerDashboardModel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
}
