package com.interswitch;

import com.interswitch.wallet.model.Customer;
import com.interswitch.wallet.model.Product;
import com.interswitch.wallet.service.WalletService;
import com.interswitch.wallet.sql.CustomerQuery;
import com.interswitch.wallet.sql.ProductQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LaunchingWallet {

    public static void main(String[] args) throws Exception {

        WalletService walletService =  new WalletService();

//        Scanner ScanInput = new Scanner(System.in);
//            System.out.println("Welcome to an e-commerce website, Press 1 or 2 to continue to quit\n"
//            +"1.Add Products\n"
//            +"2.Register Customers");
//
//
//
//            int choice = ScanInput.nextInt();
//        List<Product> products = new ArrayList<>();
//        while (true) {
//            System.out.println("Add a product to cart[y/n]");
//                String answer = ScanInput.next();
//                if (answer.equalsIgnoreCase("n")){
//                    break;
//                }
//                System.out.println("Enter the name of the product:");
//                String name = ScanInput.next();
//                System.out.println("Enter the Description of the product");
//                String description = ScanInput.next();
//                System.out.println("Enter the unit price of the product:");
//                Double unitPrice = ScanInput.nextDouble();
//                System.out.println("Enter the quantity of the product:");
//                Integer quantityInStock = ScanInput.nextInt();
//                Product product = new Product(name,description,unitPrice,quantityInStock);
//
//                products.add(product);
//                walletService.populateProductCatalog(products);


//
//            try{
//            walletService.fundCustomerWallet("8259314687",40000.00);
//
//            }catch(Exception e){
//                e.printStackTrace();
//            }

            walletService.addProductToCart(560,2);
            walletService.checkOutProduct("8259314687");

            }

            }


