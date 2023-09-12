package com.interswitch;

import com.interswitch.wallet.dto.Cart;
import com.interswitch.wallet.model.Customer;
import com.interswitch.wallet.model.Product;
import com.interswitch.wallet.service.WalletService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WalletLaunch {
    public static void main(String[] args) {
        System.out.println("Welcome to e-commerce website");
        System.out.println("Please register to use the website");

        WalletService walletService = new WalletService();

        String name;
        String phoneNumber;
        String address;
        String walletId;

        Scanner scanInput = new Scanner(System.in);

        try {
            System.out.println("Enter your name:");
            name = scanInput.next();
            System.out.println("Enter your phoneNumber:");
            phoneNumber = scanInput.next();
            System.out.println("Enter your address:");
            address = scanInput.next();
            walletId = walletService.registerCustomer(name, phoneNumber, address);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        if (walletId == null) {
            System.out.println("User cannot be added");
            return;
        }
        System.out.println("This is your username" + name + ", welcome once again");
        System.out.println("This is your walletId" + walletId + " ");


        printInstructions();

        while (true) {

            try {
                int answer = scanInput.nextInt();
                if (answer == 0) {
                    break;
                }
                scanInput.next();
                switch (answer) {
                    case 1:
                        System.out.println("________Add Products to Catalogue________");
                        addProduct(walletService);
                        System.out.println("________Done_______");
                        break;
                    case 2:
                        System.out.println("_____List Catalogue______");
                        listProducts(walletService);
                        System.out.println("_____Done____");
                        break;
                    case 3:
                        System.out.println("______RestockProducts______");
                        restockProducts(walletService);
                        System.out.println("______Done_____");
                        break;
                    case 4:
                        System.out.println("______Products____");
                        getProduct(walletService);
                        System.out.println("_____Done_____");
                        break;
                    case 5:
                        System.out.println("_____Add to Cart_____");
                        addToCart(walletService);
                        System.out.println("_____Done______");
                        break;
                    case 6:
                        System.out.println("_____Cart_____");
                        listCart(walletService);
                        System.out.println("_____Done_____");
                        break;
                    case 7:
                        System.out.println("____Check out_____");
                        checkOutProducts(walletService, walletId);
                        System.out.println("_____Done_____");
                        break;
                    case 8:
                        System.out.println("____Fund Wallet_____");
                        fundWallet(walletService,walletId);
                        System.out.println("______Done_______");
                        break;
                    case 9:
                        System.out.println("______Profile______");
                        getProfile(walletService, walletId);
                        System.out.println("______Done______");
                        break;
                    default:
                        System.out.println("Invalid answer, try again");
                }

            } catch (Exception e) {
                System.out.println("An error occured");
                e.printStackTrace();
                break;
            }
        }
        System.out.println("________Goodbye" + name + "_________");
    }

    public static void printInstructions() {
        System.out.println("======Instructions======");
        System.out.println("Use 1 to enter a new product");
        System.out.println("Use 2 to list all products");
        System.out.println("Use 3 to restock a product");
        System.out.println("Use 4 to inspect a product");
        System.out.println("Use 5 to add a product to your cart");
        System.out.println("Use 6 to get product cart");
        System.out.println("Use 7 to check out your cart");
        System.out.println("Use 8 to fund your wallet");
        System.out.println("Use 9 to get your profile details");
        System.out.println("Use 0 to quit");
    }

    public static void addProduct(WalletService walletService) {
        List<Product> products = new ArrayList<>();
        Scanner scanInput = new Scanner(System.in);


        while (true) {

            try {
                System.out.println("Enter your product name:");
                String name = scanInput.nextLine();

                System.out.println("Enter your product description");
                String description = scanInput.nextLine();

                System.out.println("Enter your product price:");
                Double unitPrice = scanInput.nextDouble();

                System.out.println("Enter quantity in stock of the product:");
                Integer quantityInStock = scanInput.nextInt();

                Product product = new Product(name, description, unitPrice, quantityInStock);
                products.add(product);

                System.out.println("Do you want to add another product[y/n]");
                String answer = scanInput.nextLine();
                if (answer.equalsIgnoreCase("n")) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Failed to add product:");
                e.printStackTrace();
            }
        }

    }

    public static void listProducts(WalletService walletService) {
        for (Product product : walletService.getProductCatalog()) {
            System.out.println("___________");
            System.out.println(product);

        }

    }

    public static void restockProducts(WalletService walletService) {
        Scanner scanInput = new Scanner(System.in);

        while (true) {
            try {
                System.out.print("Enter product id: ");
                Integer id = scanInput.nextInt();
                scanInput.nextLine();

                System.out.print("Enter quantity to add: ");
                Integer quantity = scanInput.nextInt();
                scanInput.nextLine();

                walletService.restockProduct(id, quantity);

                System.out.print("Add another product? (y/n)");
                String answer = scanInput.nextLine();
                if (answer.equalsIgnoreCase("n"))
                    break;

            } catch (Exception e) {
                System.out.println("Failed to restock product:");
                e.printStackTrace();
            }
        }

    }

    public static void getProduct(WalletService walletService) {
        Scanner scanInput = new Scanner(System.in);

        try {
            System.out.println("Enter your product ID:");
            Integer ID = scanInput.nextInt();
            walletService.getProductById(ID);
        } catch (Exception e) {
            System.out.println("Invalid ID provided");
            e.printStackTrace();
        }
    }

    public static void addToCart(WalletService walletService) {
        Scanner scanInput = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("Enter product Id:");
                Integer ID = scanInput.nextInt();

                System.out.println("Enter quantity to add:");
                Integer quantity = scanInput.nextInt();

                walletService.addProductToCart(ID, quantity);

                System.out.print("Add a new product to cart? (y/n)");
                String resp = scanInput.nextLine();
                if (resp.equalsIgnoreCase("n"))
                    break;
            } catch (Exception e) {
                System.out.println("Failed to add product to cart:");
                e.printStackTrace();

            }
        }
    }

    public static void listCart(WalletService walletService) {
        for (Cart cartItem : walletService.getMyProductCart()) {
            System.out.println("============");
            System.out.println(cartItem);
            System.out.println("============");
        }

    }

    public static void checkOutProducts(WalletService walletService, String walletId) {
        try {
            walletService.checkOutProduct(walletId);
        } catch (Exception e) {
            System.out.println("An Error occurred when checking out: ");
            e.printStackTrace();
        }
    }

    public static void fundWallet(WalletService walletService, String walletId) {
        Scanner scanInput = new Scanner(System.in);
        try {
            System.out.print("Input fund amount: ");
            double amount = scanInput.nextDouble();
            walletService.fundCustomerWallet(walletId, amount);
        } catch (Exception e) {
            System.out.println("Failed to fund wallet:");
            e.printStackTrace();
        }
    }
        public static void getProfile(WalletService walletService, String walletId){
            try {
                Customer customer = walletService.getCustomerByWallet(walletId);
                System.out.println(customer);
            } catch (Exception e) {
                System.out.println("Failed to Fetch profile:");
                e.printStackTrace();
            }


        }

    }







