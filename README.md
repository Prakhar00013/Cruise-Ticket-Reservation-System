# CruiseBooking Website
Login Credentials: admin/admin

### About:
This project is about the Cruise-Ticket-Reservation-System which is used to view Cruise Schedule, search cruise, Seat availability, cruise timings. We can also enquire about fare of different cruise. We can get information about cruise between two port. We can book seats online. This provides a safe and secure seat reservation system.
### Online Cruise Information and Reservation
<span style="color:blue">**This Website is built for following purpose:-**</span>
- View Cruise Schedule
- Search Cruises
- Seat Availability
- Cruise Timings
- Fare Enquiry
- Cruises Between Stations
- Booking seats online.
- Login and Logout Security

<span style="color:blue">**The Admin have the following access to this website:-**</span>
- Login
- Add Cruises
- Update Cruises
- Remove  or cancle Cruises
- View Cruises
- Profile Edit
- Logout

<span style="color:blue">**The Users have the following Access:-**</span>
- Register
- Login
- View Cruises
- Check Seat Availability
- Search Cruises
- Cruise Avaiablity and Fare Between Stations
- Books Tickets
- View Profile
- Update Profile
- Change Password
- Logout

### Technologies used:-
1. Front-End Development:
- Html
- Css
- Javascript

2. Back-End Development
- Java
- JDBC
- Servlet
- Oracle ( SQL )

### ==== Software And Tools Required ======
- : Oracle SQL
- : Eclipse EE
- : Java JDK 8+
- : Tomcat v8.0
- 
### ========== Dummy Database Initialization ===========

STEP 1: Open Oracle Sql Command Line

STEP 2: Login to administrator User as: ```connect <username>/<password>```

STEP 3 :Copy paste the following SQL Query:

```SQL
create user reservation identified by manager;

grant dba to reservation;

commit;

connect reservation/manager;

create table admin6(uname varchar2(40) primary key,name varchar2(40),
	pword varchar2(50),mail_id varchar2(60),phone_no varchar2(12));
	
create table cruise6(cr_no number(10) primary key,cr_name varchar2(70),
	from_depart varchar2(20),to_dest varchar2(20),available number(5),fare number(5));

create table register(uname varchar2(40) primary key,pword varchar2(50),
	fname varchar2(40),lname varchar2(40),
	addr varchar2(100), phno varchar2(12), mailid varchar2(60));

insert into admin6 values('admin','admin','admin','admin@cruise.com','9874561230');
insert into admin6 values('anshul','anshul','admin','anshul@cruise.com','98323561230');
insert into cruise6 values(10101,'Jodhpur Exp','Howrah','Jodhpur',152,450);
insert into cruise6 values(10102,'Mumbai Mail','Gaya','Mumbai',182,650);
insert into register values('anshul','anshul','Anshul','Gupta','Banglore',954745222,'anshulgupta.972@gmail.com');

commit;
```