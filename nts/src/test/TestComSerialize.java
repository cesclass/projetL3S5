package test;

import java.util.*;

import communications.*;
import exchange.*;
import user.*;

public class TestComSerialize {

	public static void main(String[] args) {
		ComData d = null;
		String s = null;

		Date date = Calendar.getInstance().getTime();
		User ua = new User("a", "A");
		User ub = new User("b", "B");
		Group ga = new Group("G-A", "Groupe A");
		Group gb = new Group("G-B", "Groupe B");
		Message ma = new Message(ua, "message_a");
		Ticket ta = new Ticket("ticket_a", ga, ma);
		Message mb = new Message(42, date, ub, "message_b", StatusType.RECEIVED);
		Ticket tb = new Ticket(69, "ticket_b", date, gb, 4);
		Status sa = new Status(ua, StatusType.AUTHOR);
		Status sb = new Status(ub, StatusType.READ);

		d = new ComData(
			ComType.CONNECT_RQ, 
			new ComLogin("coucou", "coucourge")
		);
		d.getGroups().add(ga);
		d.getGroups().add(gb);
		d.getTickets().add(ta);
		d.getTickets().add(tb);
		d.getMessages().add(ma);
		d.getMessages().add(mb);
		d.getStatuses().add(sa);
		d.getStatuses().add(sb);
		
		s = Serializer.serialize(d);

		System.out.println("PHASE 1");
		System.out.println(d);
		System.out.println(s);

		d = Serializer.deserialize(s);
		s = Serializer.serialize(d);

		System.out.println("PHASE 2");
		System.out.println(d);
		System.out.println(s);

	}
}
