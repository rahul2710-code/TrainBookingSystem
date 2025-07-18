import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class bookingservice {
    private List<train> trainList = new ArrayList<>();

    private List<ticket> ticketList = new ArrayList<>();

    public bookingservice(){

        trainList.add(new train(101,"Rajdhani express","Delhi","Pune",100));
        trainList.add(new train(102,"Duranto express","Pune","Nagpur",60));
        trainList.add(new train(103,"Maharastra express","Gondia","Kolhapur",70));
        trainList.add(new train(104,"BSP express","Bilaspur","Nagpur",90));
        trainList.add(new train(105,"Gitanjli express","Nagpur","Mumbai",80));

    }

    public List<train> searchTrain(String source,String destination){
        List<train> res = new ArrayList<>();

        for(train train:trainList){
            if(train.getSource().equalsIgnoreCase(source) && train.getDestination().equalsIgnoreCase(destination)){
                res.add(train);
            }
        }
        return res;
    }

    public ticket bookTicket(user user,int trainId,int seatCount){
        for(train train:trainList){
            if(train.getTrainId()==trainId){
                if(train.bookSeats(seatCount))
                {
                    ticket ticket=new ticket(user,train,seatCount);
                    ticketList.add(ticket);
                    return ticket;
                }
                else{
                    System.out.println("No seats available");
                    return null;
                }
            }
        }

        System.out.println("No train Id found");
        return null;
    }


    public List<ticket> getTicketByUser(user user){
        List<ticket> res= new ArrayList<>();

        for(ticket ticket:ticketList){
            if(ticket.getUser().getUsername().equalsIgnoreCase(user.getUsername())){
                res.add(ticket);
            }
        }
        return res;
    }

    public boolean cancelTicket(int ticketId,user user){
        Iterator<ticket> iterator=ticketList.listIterator();
        while(iterator.hasNext()){
            ticket ticket=iterator.next();
            if(ticket.getTicketId()==ticketId && ticket.getUser().getUsername().equalsIgnoreCase(user.getUsername())){
                train train = ticket.getTrain();
                train.cancelSeat(ticket.getSeatBooked());
                iterator.remove();
                System.out.println("Ticketid"+ticketId+"cancelled successfully");
                return true;

            }
        }
        System.out.println("Ticket not found or does not belong to current user");
        return false;
    }

    public void listAllTrains(){
        System.out.println("List of all trains:");
        for(train train : trainList){
            System.out.println(train);
        }
    }

}
