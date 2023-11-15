/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package maceraoyunu;

import characters.Archer;
import characters.MainCharacter;
import characters.Warrior;
import characters.Wizard;
import dataaccess.Access;
import equipments.Armor;
import equipments.Gun;
import java.util.InputMismatchException;
import java.util.Scanner;
import menu.Menu;
import places.Cave;
import shop.Shop;
import queries.Queries;

/**
 *
 * @author kaan
 */
public class MaceraOyunu {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner (System.in) ;
        
        System.out.println("DB host : ");
        
        String host = scanner.nextLine() ;
        
        System.out.println("DB username : ");
        
        String userName = scanner.nextLine() ;
        
        System.out.println("DB pass : ");
        
        String pass = scanner.nextLine() ;
        
        System.out.println("DB name : ");
        
        String dbName = scanner.nextLine() ;
        
        System.out.println("Port : ");
        
        int port = scanner.nextInt() ;
        
        Access.setDbInfo(userName, pass, dbName, host, 0);

        System.out.println("Macera oyununa hosgeldin.");

        String info = "Karakter Olusturma : 1\nAna karakter secimi : 2\nMagazaya git : 3\nKesife Cik : 4\nDinlen : 5\nPerk kullan : 6\nKarakter Bilgisi : 7\nCikis : Q-q", input = "";

        boolean exit = true;

        while ((!(input.equals("q")) && !(input.equals("Q"))) && exit) {

            System.out.println(info);

            input = scanner.nextLine();

            switch (input) {

                case "1": {

                    Menu menu = new Menu();

                    String characterInput = "";

                    while (!(characterInput.equals("Q")) && !(characterInput.equals("q"))) {

                        System.out.println("For Archer : 1\nFor Warrior : 2\nFor Wizard : 3\nQuitting Character creating menu : Q-q");

                        characterInput = scanner.nextLine();

                        if (characterInput.equals("1")) {

                            System.out.println("Name of your character : ");

                            String nameInput = scanner.nextLine();

                            Archer archer = new Archer();

                            Archer character = null;

                            try {

                                character = menu.createCharacter(archer, nameInput);

                            } catch (NullPointerException ex) {

                                System.out.println("Karakter olusturulamadi.");
                                archer = null;
                                continue;

                            }

                            Queries query = new Queries();

                            if (query.addCharacter(character)) {

                                System.out.println(character.getName() + " added to the db.");

                            } else {

                                System.out.println(character.getName() + " could not add to the db.");

                            }

                            query = null;
                            archer = null;

                        } else if (characterInput.equals("2")) {

                            System.out.println("Name of your character : ");

                            String nameInput = scanner.nextLine();

                            Warrior warrior = new Warrior();

                            Warrior character = null;

                            try {

                                character = menu.createCharacter(warrior, nameInput);

                            } catch (NullPointerException ex) {

                                System.out.println("Karakter olusuturulamadi.");
                                warrior = null;
                                continue;

                            }

                            Queries query = new Queries();

                            if (query.addCharacter(character)) {

                                System.out.println(character.getName() + " added to the db.");

                            } else {

                                System.out.println(character.getName() + " could not add to the db.");

                            }

                            query = null;
                            warrior = null;

                        } else if (characterInput.equals("3")) {

                            System.out.println("Name of your character : ");

                            String nameInput = scanner.nextLine();

                            Wizard wizard = new Wizard();

                            Wizard character = null;

                            try {

                                character = menu.createCharacter(wizard, nameInput);

                            } catch (NullPointerException ex) {

                                System.out.println("Karakter olusuturulamadi");
                                wizard = null;
                                continue;

                            }

                            Queries query = new Queries();

                            if (query.addCharacter(character)) {

                                System.out.println(character.getName() + " added to the db.");

                            } else {

                                System.out.println(character.getName() + " could not add to the db.");

                            }

                            query = null;
                            wizard = null;

                        } else if (characterInput.equals("Q") || characterInput.equals("q")) {
                            System.out.println("Redirecting to main menu...");
                        } else {
                            System.out.println("Invalid input.");
                        }

                    }

                    menu = null;

                    break;

                }

                case "2": {

                    String idOrNameInput = "";

                    MainCharacter main = new MainCharacter();

                    while (!(idOrNameInput.equals("Q")) && !(idOrNameInput.equals("q"))) {

                        System.out.println("ID numarasi ile ana karakter secmek icin : 1\n"
                                + "Isim ile ana karakter secmek icin : 2\n"
                                + "Ana karakter menusunden cikmak icin : Q-q");

                        idOrNameInput = scanner.nextLine();

                        if (idOrNameInput.equals("1")) {

                            System.out.println("Id : ");

                            int id = 0;

                            try {

                                id = scanner.nextInt();

                            } catch (InputMismatchException ex) {

                                System.out.println("Lutfen gecerli bir sayi giriniz.");

                                continue;

                            }

                            Queries query = new Queries();

                            if (!(main.setMainCharacter(id) || query.chooseCharacter(id))) {
                                System.out.println(id + " ID numarali karakter bulunamadi.");

                            }

                            query = null;

                        } else if (idOrNameInput.equals("2")) {

                            System.out.println("Name : ");

                            String name = scanner.nextLine();

                            Queries query = new Queries();

                            if (!(main.setMainCharacter(name) || query.chooseCharacter(name))) {
                                System.out.println(name + " isimli karakter bulunamadi.");
                            }

                            query = null;

                        } else if (idOrNameInput.equals("Q") || idOrNameInput.equals("q")) {
                            System.out.println("You are redirecting to main menu...");
                        } else {
                            System.out.println("Invalid input.");
                        }

                    }

                    main = null;

                    break;

                }

                case "3": {

                    String weaponInfo = "Iron Sword : 1 (5 gold)\nSteel Sword : 2 (9 gold)\nSilver Bow : 3 (7 gold)\nElder Bow : 4 (11 gold)\nMagical Stick : 5 (8 gold)\nSuper Magical Stick : 6 (13 gold)";

                    String armorInfo = "Iron Armor : 7 (10 gold)\nSteel Armor : 8 (15 gold)";

                    String shopInput = "";

                    Shop shop = new Shop();

                    while (!(shopInput.equals("q")) && !(shopInput.equals("Q"))) {

                        System.out.println(weaponInfo);

                        System.out.println(armorInfo);

                        shopInput = scanner.nextLine();

                        if (shopInput.equals("1")) {

                            shop.buyWeapon(Gun.createIronSword());

                        } else if (shopInput.equals("2")) {

                            shop.buyWeapon(Gun.createSteelSword());

                        } else if (shopInput.equals("3")) {

                            shop.buyWeapon(Gun.createSilverBow());

                        } else if (shopInput.equals("4")) {

                            shop.buyWeapon(Gun.createElderBow());

                        } else if (shopInput.equals("5")) {

                            shop.buyWeapon(Gun.createMagicalStick());

                        } else if (shopInput.equals("6")) {

                            shop.buyWeapon(Gun.createSuperMagicalStick());

                        } else if (shopInput.equals("7")) {

                            shop.buyArmor(Armor.createIronArmor());

                        } else if (shopInput.equals("8")) {

                            shop.buyArmor(Armor.createSteelArmor());

                        } else if (shopInput.equals("Q") || shopInput.equals("q")) {
                            System.out.println("You are redirecting to main menu...");
                        } else {
                            System.out.println("Invalid input.");
                        }

                    }

                    shop = null;

                    break;

                }

                case "4": {

                    String exploringInfo = "Cave : 1\nJungle : 2\nGrave Yard : 3\nQuit to main menu : Q-q";
                    String exploringInput = "";

                    Menu menu = new Menu();

                    while (!(exploringInput.equals("q")) && !(exploringInput.equals("Q"))) {

                        System.out.println(exploringInfo);

                        exploringInput = scanner.nextLine();

                        if (exploringInput.equals("1")) {

                            boolean isMainCharacterDead = false;

                            try {

                                isMainCharacterDead = menu.war(Cave.class);

                            } catch (NullPointerException ex) {

                                System.out.println("Once ana karakter seciniz.");
                                break;

                            }

                            if (isMainCharacterDead) {

                                System.out.println("Main character is dead.You are redirecting to main menu...");
                                break;

                            } else {

                                System.out.println("You are redirecting to main menu.");

                            }

                        } else if (exploringInput.equals("2")) {

                            boolean isMainCharacterDead = false;

                            try {

                                isMainCharacterDead = menu.war(Cave.class);

                            } catch (NullPointerException ex) {

                                System.out.println("Once ana karakter seciniz.");

                            }

                            if (isMainCharacterDead) {

                                System.out.println("Main character is dead.You are redirecting to main menu...");
                                break;

                            } else {

                                System.out.println("You are redirecting to main menu.");
                                break;

                            }

                        } else if (exploringInput.equals("3")) {

                            boolean isMainCharacterDead = false;

                            try {

                                isMainCharacterDead = menu.war(Cave.class);

                            } catch (NullPointerException ex) {

                                System.out.println("Once ana karakter seciniz.");

                            }

                            if (isMainCharacterDead) {

                                System.out.println("Main character is dead.You are redirecting to main menu...");
                                break;

                            } else {

                                System.out.println("You are redirecting to main menu.");
                                break;

                            }

                        } else if (exploringInput.equals("q") || exploringInput.equals("Q")) {

                            System.out.println("You are redirecting to main menu...");

                        } else {
                            System.out.println("Invalid input.");
                        }

                    }

                    menu = null;
                    break;

                }

                case "5": {

                    try {

                        MainCharacter.mainCharacter.refreshHp();

                    } catch (NullPointerException ex) {

                        System.out.println("Main Character has not selected.");

                    }

                    break;

                }

                case "6": {

                    if (MainCharacter.mainCharacter == null) {

                        System.out.println("You have to choose a main character first.");

                        break;

                    }

                    MainCharacter.mainCharacter.getLevel().usePerk();

                    break;

                }

                case "7": {

                    if (MainCharacter.mainCharacter == null) {

                        System.out.println("You have to choose a main character first.");

                        break;

                    }

                    MainCharacter.mainCharacter.display();
                    break;

                }

                case "Q": {

                    exit = false;
                    System.out.println("Exiting from game...");
                    try {

                        Thread.sleep(1000);

                    } catch (InterruptedException ex) {

                        ex.printStackTrace();

                    }
                    break;

                }

                case "q": {

                    exit = false;
                    System.out.println("Exiting from game...");
                    try {

                        Thread.sleep(1000);

                    } catch (InterruptedException ex) {

                        ex.printStackTrace();

                    }
                    break;

                }
                default: {

                    System.out.println("Invalid Input.");
                    break;

                }

            }

        }

    }
    
    

}
