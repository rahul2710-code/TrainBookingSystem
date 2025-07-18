public class ticket {
    private int ticketId;

    private user user;

    private train train;

    private int seatBooked;

    private static int counter=1001;

    public ticket(user user, train train, int seatBooked) {
        this.ticketId=counter++;
        this.user = user;
        this.train = train;
        this.seatBooked = seatBooked;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public user getUser() {
        return user;
    }

    public void setUser(user user) {
        this.user = user;
    }

    public train getTrain() {
        return train;
    }

    public void setTrain(train train) {
        this.train = train;
    }

    public int getSeatBooked() {
        return seatBooked;
    }

    public void setSeatBooked(int seatBooked) {
        this.seatBooked = seatBooked;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        ticket.counter = counter;
    }

    @Override
    public String toString() {
        return "Ticket ID: "+ticketId+"| Train:"+train.getName()+" | Route:"+train.getSource()+" -> "+train.getDestination()+
                "| Seats: "+seatBooked+"| Booked By: "+user.getFullname();
    }
}
