import controller.AccountController;
import controller.AccountControllerImpl;
import entity.Account;
import repository.EntityRepository;
import repository.EntityRepositoryImpl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        AccountController accountController = new AccountControllerImpl();
        accountController.receiveCommand();

    }
}
