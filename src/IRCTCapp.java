import java.security.spec.RSAOtherPrimeInfo;
import java.util.List;
import java.util.Scanner;

public class IRCTCapp {

    private final Scanner scanner=new Scanner(System.in);
    private final userservice userservice=new userservice();
    private final bookingservice bookingservice=new bookingservice();

    public static void main(String[] args) {
    new IRCTCapp().start();
    }

    public void start()
    {
        while(true)
        {
            System.out.println("\n-----Welcome to IRCTC APP-----");
            if(!userservice.isLoggin())
            {
                System.out.println("1. Register:");
                System.out.println("2. Login:");
                System.out.println("3. Exit:");
                System.out.println("Enter your choice:");
                int choice= scanner.nextInt();

                switch (choice)
                {
                    case 1 -> register();
                    case 2 -> login();
                    case 3 -> exitApp();
                    default -> System.out.println("Invalid Choice.");
                }

            }else{
                showUserMenu();
            }

        }
    }
    public void register(){
        System.out.println("Enter username:");
        String username=scanner.next();
        System.out.println("Enter password:");
        String password=scanner.next();
        System.out.println("Enter full name:");
        scanner.nextLine();
        String fullname=scanner.nextLine();
        System.out.println("Enter contact:");
        String contact =scanner.next();

        userservice.registerUser(username,password,fullname,contact);
    }

    public void login()
    {
        System.out.println("Enter username:");
        String username=scanner.next();
        System.out.println("Enter password:");
        String password=scanner.next();
        userservice.loginUser(username,password);
    }

    private void showUserMenu()
    {
        while(userservice.isLoggin()){
            System.out.println("\n-----USER MENU-----");
            System.out.println("1. Search Trains:");
            System.out.println("2. Book Ticket:");
            System.out.println("3. View my tickets:");
            System.out.println("4. Cancel ticket:");
            System.out.println("5. View all trains:");
            System.out.println("6. Logout:");
            System.out.println("Enter choice:");
            int choice= scanner.nextInt();
            switch (choice){
                case 1 -> searchTrain();
                case 2 -> bookTicket();
                case 3 -> viewMyTicket();
                case 4 -> cancelTicket();
                case 5 -> bookingservice.listAllTrains();
                case 6 -> userservice.logoutuser();
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void searchTrain()
    {
        System.out.println("Enter source station");
        String source=scanner.next();
        System.out.println("Enter destination station");
        String destination=scanner.next();

        List<train> trains= bookingservice.searchTrain(source,destination);
        if(trains.isEmpty()){
            System.out.println("No trains found between"+ source + "and"+ destination);
            return;
        }
        System.out.println("Trains Found:");
        for(train train:trains){
            System.out.println(train);
        }

        System.out.println("Do you want to book ticket ? (yes/no):");
        String choice=scanner.next();
        if(choice.equalsIgnoreCase("yes")){
            System.out.println("Enter train ID to book:");
            int trainId= scanner.nextInt();
            System.out.println("Enter number of seats to book:");
            int seats= scanner.nextInt();

            ticket ticket= bookingservice.bookTicket(userservice.getCurrentUser(),trainId,seats);
            if(ticket!=null){
                System.out.println("Booking successfull!");
                System.out.println(ticket);
            }
        }else{
            System.out.println("Returning to user menu...");
        }
    }

    private void bookTicket(){
        System.out.println("Enter source station");
        String source=scanner.next();
        System.out.println("Enter destination station");
        String destination=scanner.next();

        List<train> trains= bookingservice.searchTrain(source,destination);
        if(trains.isEmpty()){
            System.out.println("No trains available for booking");
            return;
        }
        System.out.println("Available Trains:");
        for(train train:trains){
            System.out.println(train);
        }

        System.out.println("Enter train ID to book:");
        int trainId= scanner.nextInt();
        System.out.println("Enter number of seats to book:");
        int seats= scanner.nextInt();

        ticket ticket= bookingservice.bookTicket(userservice.getCurrentUser(),trainId,seats);
        if(ticket!=null){
            System.out.println("Booking successfull!");
            System.out.println(ticket);
        }
    }

    private void viewMyTicket(){
        List<ticket> ticketByUser= bookingservice.getTicketByUser(userservice.getCurrentUser());
        if(ticketByUser.isEmpty()){
            System.out.println("No ticket booked yet");
        }
        else{
            System.out.println("Your tickets:");
            for(ticket ticket: ticketByUser){
                System.out.println(ticket);
            }
        }

    }

    private void cancelTicket(){
        System.out.println("Enter ticket ID to cancel:");
        int ticketId= scanner.nextInt();
        bookingservice.cancelTicket(ticketId,userservice.getCurrentUser());
    }

    private void exitApp(){
        System.out.println("Thank you for using IRCTC APP");
        System.exit(0);
    }
}
