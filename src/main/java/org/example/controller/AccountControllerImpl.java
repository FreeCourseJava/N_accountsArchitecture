package org.example.controller;

import org.example.service.MakeTransactionServiceImp;

import java.io.FileNotFoundException;

public class AccountControllerImpl implements AccountController {
    @Override
    public void receiveCommand() throws FileNotFoundException {
//
//        Scanner sc = new Scanner(System.in );
//
//        System.out.println("Input name of source account");
//        String nameOfSourceAccount = sc.next();
//
//        System.out.println("Input name of destination account");
//        String nameOfDestinationAccount = sc.next();
//
//        System.out.println("Input quantity of currency transaction");
//        double quantity = sc.nextDouble();
//
//        sc.close();

        MakeTransactionServiceImp makeTransactionServiceImp = new MakeTransactionServiceImp();
        makeTransactionServiceImp.makeTransaction("Vasja", "Alexey", 100d);

    }
}
