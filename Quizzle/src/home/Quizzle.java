package home;


import java.util.HashMap;
import java.util.Scanner;

abstract class User{
	String userName;
	String password;
	String userType;
	abstract int dashboard();
	
}

class Student extends User{
	String admin;
	String organization;
	Student(String userName, String password, String admin, String organization){
		this.userName = userName;
		this.password = password;
		this.userType = "Teacher";
		this.admin = admin;
		this.organization = organization;
		
	}
	int dashboard(){
		return 0;
	}
}

class Teacher extends User{
	String admin;
	String organization;
	Teacher(String userName, String password, String admin, String organization){
		this.userName = userName;
		this.password = password;
		this.userType = "Teacher";
		this.admin = admin;
		this.organization = organization;
		
	}
	int dashboard(){
		return 0;
	}
}

class Organization extends User{
	String admin;
	Organization(){
	}
	Organization(String userName, String password, String admin){
		this.userName = userName;
		this.password = password;
		this.userType = "Organization";
		this.admin = admin;
		
	}
	
	void showStudents() {
		System.out.println("STUDENTS");
		System.out.println("=========");
		for (HashMap.Entry<String, User> entry : Quizzle.userList.entrySet()) {
			String key = entry.getKey();
			User value = Quizzle.userList.get(key);
			if(value.userType.equals("Student")) {
				Student student = (Student) Quizzle.userList.get(key);
				if(this.userType.equals("Super Admin")) {
					System.out.println("* "+student.userName);
				}
				else if(student.admin.equals(this.userName) || student.organization.equals(this.userName)){
					System.out.println("* "+student.userName);
				}
			}
		}
	}
	
	void removeStudent() {
		Scanner sc = new Scanner(System.in);
		while(true) {
			int authorizationFlag = 1;
			showTeachers();
			System.out.println("Enter USERNAME of Student to Remove or exit to abort the operation");
			String userName = sc.next();
			if(userName.toLowerCase().equals("exit")) {
				System.out.println("Operation aborted successfully");
				break;
			}
			if(Quizzle.userList.containsKey(userName))
			{	
				User value = Quizzle.userList.get(userName);
				if(value.userType.equals("Student")) {
					Student student = (Student) Quizzle.userList.get(userName);
					if(this.userType.equals("Super Admin")) {
						authorizationFlag = 0;
						
					}
					else if(student.admin.equals(this.userName) || student.organization.equals(this.userName)) {
							authorizationFlag = 0;						
					}
					if(authorizationFlag == 0) {
						System.out.println("Press 0 to Remove Student with USERNAME "+userName+" or any other key to abort the operation");
						String opt = sc.next();
						if(opt.equals("0")) {
							Quizzle.userList.remove(userName);
							System.out.println("Seacher with USERNAME "+userName+ " Removed Successfully");
							break;
						}
						else {
							System.out.println("Operation aborted successfully");
							break;
						}
					}
					else {
						System.out.println("You Don't have permission to perform this operation");
						break;
					}
				}
				else {
					System.out.println("USERNAME "+value.userName+" is not an Student");
					break;
				}
				
			}
			else {
				System.out.println("USERNAME DOESN'T EXIST");
			}
		}
	}
	
	void createStudent() {
		Scanner sc = new Scanner(System.in);
		String userType = "Student";
		while(true) {
			System.out.println("Please enter a USERNAME or exit to abort the operation");
			String userName = sc.next();
			if(userName.toLowerCase().equals("exit")) {
				System.out.println("Operation aborted successfully");
				break;
			}
			else {
				if(Quizzle.userList.containsKey(userName))
				{	
					System.out.println("USERNAME ALREADY EXIST");
				}
				else {
					System.out.println("Please Enter Password: ");
					String password = sc.next();
					System.out.println();
					System.out.println("Student Details:");
					System.out.println("User Name: "+userName);
					System.out.println("Password: "+password);
					System.out.println("User Type: "+userType);
					System.out.println("Press 0 to create Student or any other key to abort the operation");
					String opt = sc.next();
					if(opt.equals("0")) {
						if(this.userType.equals("Super Admin")) {
							Student student = new Student(userName, password, "NA", "NA");
							Quizzle.userList.put(userName, student);
						}
						if(this.userType.equals("Admin")) {
							Student student = new Student(userName, password, this.userName, "NA");
							Quizzle.userList.put(userName, student);
						}
						if(this.userType.equals("Organization")) {
							Student student = new Student(userName, password, this.admin,this.userName);
							Quizzle.userList.put(userName, student);
						}
						System.out.println("Student with USERNAME "+userName+ " Created Successfully");
						break;

					}
					else {
						System.out.println("Operation aborted successfully");
						break;
					}
				}
				
			}
		}
	}
	
	void showTeachers(){
		System.out.println("TEACHERS");
		System.out.println("=========");
		for (HashMap.Entry<String, User> entry : Quizzle.userList.entrySet()) {
			String key = entry.getKey();
			User value = Quizzle.userList.get(key);
			if(value.userType.equals("Teacher")) {
				Teacher teacher = (Teacher) Quizzle.userList.get(key);
				if(this.userType.equals("Super Admin")) {
					System.out.println("* "+teacher.userName);
				}
				else if(teacher.admin.equals(this.userName) || teacher.organization.equals(this.userName)){
					System.out.println("* "+teacher.userName);
				}
			}
		}
	}
	
	void removeTeacher(){
		Scanner sc = new Scanner(System.in);
		while(true) {
			int authorizationFlag = 1;
			showTeachers();
			System.out.println("Enter USERNAME of Teacher to Remove or exit to abort the operation");
			String userName = sc.next();
			if(userName.toLowerCase().equals("exit")) {
				System.out.println("Operation aborted successfully");
				break;
			}
			if(Quizzle.userList.containsKey(userName))
			{	
				User value = Quizzle.userList.get(userName);
				if(value.userType.equals("Teacher")) {
					Teacher teacher = (Teacher) Quizzle.userList.get(userName);
					if(this.userType.equals("Super Admin")) {
						authorizationFlag = 0;
						
					}
					else if(teacher.admin.equals(this.userName) || teacher.organization.equals(this.userName)) {
							authorizationFlag = 0;						
					}
					if(authorizationFlag == 0) {
						System.out.println("Press 0 to Remove Teacher with USERNAME "+userName+" or any other key to abort the operation");
						String opt = sc.next();
						if(opt.equals("0")) {
							Quizzle.userList.remove(userName);
							System.out.println("Teacher with USERNAME "+userName+ " Removed Successfully");
							break;
						}
						else {
							System.out.println("Operation aborted successfully");
							break;
						}
					}
					else {
						System.out.println("You Don't have permission to perform this operation");
						break;
					}
				}
				else {
					System.out.println("USERNAME "+value.userName+" is not an Teacher");
					break;
				}
				
			}
			else {
				System.out.println("USERNAME DOESN'T EXIST");
			}
		}
	}
	
	void createTeacher(){
		Scanner sc = new Scanner(System.in);
		String userType = "Teacher";
		while(true) {
			System.out.println("Please enter a USERNAME or exit to abort the operation");
			String userName = sc.next();
			if(userName.toLowerCase().equals("exit")) {
				System.out.println("Operation aborted successfully");
				break;
			}
			else {
				if(Quizzle.userList.containsKey(userName))
				{	
					System.out.println("USERNAME ALREADY EXIST");
				}
				else {
					System.out.println("Please Enter Password: ");
					String password = sc.next();
					System.out.println();
					System.out.println("Teacher Details:");
					System.out.println("User Name: "+userName);
					System.out.println("Password: "+password);
					System.out.println("User Type: "+userType);
					System.out.println("Press 0 to create Teacher or any other key to abort the operation");
					String opt = sc.next();
					if(opt.equals("0")) {
						if(this.userType.equals("Super Admin")) {
							Teacher teacher = new Teacher(userName, password, "NA", "NA");
							Quizzle.userList.put(userName, teacher);
						}
						if(this.userType.equals("Admin")) {
							Teacher teacher = new Teacher(userName, password, this.userName, "NA");
							Quizzle.userList.put(userName, teacher);
						}
						if(this.userType.equals("Organization")) {
							Teacher teacher = new Teacher(userName, password, this.admin,this.userName);
							Quizzle.userList.put(userName, teacher);
						}
						System.out.println("Teacher with USERNAME "+userName+ " Created Successfully");
						break;

					}
					else {
						System.out.println("Operation aborted successfully");
						break;
					}
				}
				
			}
		}
	}

	int studentOptions() {
		while(true) {
			int opt = 0;
			System.out.println("1. SHOW STUDENTS");
			System.out.println("2. CREATE STUDENT");
			System.out.println("3. REMOVE STUDENTS");
			System.out.println("8. PREVIOUS MENU");
			System.out.println("SELECT ANY OPTION, 9 TO LOGOUT, 0 TO EXIT THE PROGRAM");
			Scanner sc = new Scanner(System.in);
			int DashboardFlag = 1;
			try {
				opt = sc.nextInt();
				DashboardFlag = 0;
			}
			catch(Exception e) {
				System.out.println("INVALID OPTION SELECTED PLEASE SELECT AN VALID OPTION");
			}	
			if (DashboardFlag==0) {
				switch(opt) {
				case 0:
					return 0;
				case 1:
					showStudents();
					break;
				case 2:
					createStudent();
					break;
				case 3:
					removeStudent();
					break;
				case 8:
					return 8; //Previous Menu
				case 9:
					System.out.println("LOGOUT SUCESSFUL");
					return 9;
				default:
					System.out.println("INVALID OPTION SELECTED PLEASE SELECT AN VALID OPTION");
					break;
				}
			}
		}
	}
	
	int teacherOptions() {
		while(true) {
			int opt = 0;
			System.out.println("1. SHOW TEACHERS");
			System.out.println("2. CREATE TEACHER");
			System.out.println("3. REMOVE TEACHER");
			System.out.println("8. PREVIOUS MENU");
			System.out.println("SELECT ANY OPTION, 9 TO LOGOUT, 0 TO EXIT THE PROGRAM");
			Scanner sc = new Scanner(System.in);
			int DashboardFlag = 1;
			try {
				opt = sc.nextInt();
				DashboardFlag = 0;
			}
			catch(Exception e) {
				System.out.println("INVALID OPTION SELECTED PLEASE SELECT AN VALID OPTION");
			}	
			if (DashboardFlag==0) {
				switch(opt) {
				case 0:
					return 0;
				case 1:
					showTeachers();
					break;
				case 2:
					createTeacher();
					break;
				case 3:
					removeTeacher();
					break;
				case 8:
					return 8; //Previous Menu
				case 9:
					System.out.println("LOGOUT SUCESSFUL");
					return 9;
				default:
					System.out.println("INVALID OPTION SELECTED PLEASE SELECT AN VALID OPTION");
					break;
				}
			}
		}
	}
	
	int dashboard(){
		while(true) {
			int opt = 0;
			int o = -1;
			System.out.println("*******************************WELCOME*****************************");
			System.out.println("Username: "+this.userName+"                         User Type: "+this.userType);
			System.out.println();
			System.out.println("****************************DASHBOARD*****************************");
			System.out.println("1. TEACHERS");
			System.out.println("2. STUDENTS");
			System.out.println("SELECT ANY OPTION, 9 TO LOGOUT, 0 TO EXIT THE PROGRAM");
			Scanner sc = new Scanner(System.in);
			int DashboardFlag = 1;
			try {
				opt = sc.nextInt();
				DashboardFlag = 0;
			}
			catch(Exception e) {
				System.out.println("INVALID OPTION SELECTED PLEASE SELECT AN VALID OPTION");
			}	
			if (DashboardFlag==0) {
				switch(opt) {
				case 0:
					o = 0;
				case 1:
					o = teacherOptions();
					break;
				case 2:
					o = studentOptions();
					break;
				case 9:
					System.out.println("LOGOUT SUCESSFUL");
					o = 9;
				default:
					System.out.println("INVALID OPTION SELECTED PLEASE SELECT AN VALID OPTION");
					break;
				}
				if(o==0||o==9) {
					return o;
				}
			}
		}
	}
	
	
}

class Admin extends Organization{
	Admin(){
	}
	Admin(String userName,String password){
		this.userName = userName;
		this.password = password;
		this.userType = "Admin";
		
	}
	
	void showOrganization() {
		System.out.println("ORGANIZATIONS");
		System.out.println("=========");
		for (HashMap.Entry<String, User> entry : Quizzle.userList.entrySet()) {
			String key = entry.getKey();
			User value = Quizzle.userList.get(key);
			if(value.userType.equals("Organization")) {
				Organization organization = (Organization) Quizzle.userList.get(key);
				if(this.userType.equals("Super Admin")) {
					System.out.println("* "+organization.userName);
				}
				else {
					if(organization.admin.equals(this.userName)) {
						System.out.println("* "+organization.userName);
					}
				}
			}
		}
	}
	
	void removeOrganization() {
		Scanner sc = new Scanner(System.in);
		while(true) {
			int authorizationFlag = 1;
			showOrganization();
			System.out.println("Enter USERNAME of Admin to Remove or exit to abort the operation");
			String userName = sc.next();
			if(userName.toLowerCase().equals("exit")) {
				System.out.println("Operation aborted successfully");
				break;
			}
			if(Quizzle.userList.containsKey(userName))
			{	
				User value = Quizzle.userList.get(userName);
				if(value.userType.equals("Organization")) {
					Organization organization = (Organization) Quizzle.userList.get(userName);
					if(this.userType.equals("Super Admin")) {
						authorizationFlag = 0;
						
					}
					else {
						if(organization.admin.equals(this.userName)) {
							authorizationFlag = 0;						
						}
					}
					if(authorizationFlag == 0) {
						System.out.println("Press 0 to Remove Organization with USERNAME "+userName+" or any other key to abort the operation");
						String opt = sc.next();
						if(opt.equals("0")) {
							Quizzle.userList.remove(userName);
							System.out.println("Organization with USERNAME "+userName+ " Removed Successfully");
							break;
						}
						else {
							System.out.println("Operation aborted successfully");
							break;
						}
					}
					else {
						System.out.println("You Don't have permission to perform this operation");
						break;
					}
				}
				else {
					System.out.println("USERNAME "+value.userName+" is not an Organization");
					break;
				}
				
			}
			else {
				System.out.println("USERNAME DOESN'T EXIST");
			}
		}
	}
	
	void createOrganization() {
		Scanner sc = new Scanner(System.in);
		String userType = "Organization";
		while(true) {
			System.out.println("Please enter a USERNAME or exit to abort the operation");
			String userName = sc.next();
			if(userName.toLowerCase().equals("exit")) {
				System.out.println("Operation aborted successfully");
				break;
			}
			else {
				if(Quizzle.userList.containsKey(userName))
				{	
					System.out.println("USERNAME ALREADY EXIST");
				}
				else {
					System.out.println("Please Enter Password: ");
					String password = sc.next();
					System.out.println();
					System.out.println("Organization Details:");
					System.out.println("User Name: "+userName);
					System.out.println("Password: "+password);
					System.out.println("User Type: "+userType);
					System.out.println("Press 0 to create Organization or any other key to abort the operation");
					String opt = sc.next();
					if(opt.equals("0")) {
						if(this.userType.equals("Super Admin")) {
							Organization org = new Organization(userName, password, "NA");
							Quizzle.userList.put(userName, org);
						}
						if(this.userType.equals("Admin")) {
							Organization org = new Organization(userName, password, this.userName);
							Quizzle.userList.put(userName, org);
						}
						System.out.println("Organization with USERNAME "+userName+ " Created Successfully");
						break;

					}
					else {
						System.out.println("Operation aborted successfully");
						break;
					}
				}
				
			}
		}
	}
	
	int organizationOptions() {
		while(true) {
			int opt = 0;
			System.out.println("1. SHOW ORGANIZATIONS");
			System.out.println("2. CREATE ORGANIZATION");
			System.out.println("3. REMOVE ORGANIZATION");
			System.out.println("8. PREVIOUS MENU");
			System.out.println("SELECT ANY OPTION, 9 TO LOGOUT, 0 TO EXIT THE PROGRAM");
			Scanner sc = new Scanner(System.in);
			int DashboardFlag = 1;
			try {
				opt = sc.nextInt();
				DashboardFlag = 0;
			}
			catch(Exception e) {
				System.out.println("INVALID OPTION SELECTED PLEASE SELECT AN VALID OPTION");
			}	
			if (DashboardFlag==0) {
				switch(opt) {
				case 0:
					return 0;
				case 1:
					showOrganization();
					break;
				case 2:
					createOrganization();
					break;
				case 3:
					removeOrganization();
					break;
				case 8:
					return 8; //Previous Menu
				case 9:
					System.out.println("LOGOUT SUCESSFUL");
					return 9;
				default:
					System.out.println("INVALID OPTION SELECTED PLEASE SELECT AN VALID OPTION");
					break;
				}
			}
		}
	}
	
	int dashboard(){
		while(true) {
			int opt = 0;
			int o = -1;
			System.out.println("*******************************WELCOME*****************************");
			System.out.println("Username: "+this.userName+"                         User Type: "+this.userType);
			System.out.println();
			System.out.println("****************************DASHBOARD*****************************");
			System.out.println("1. ORGANIZATIONS");
			System.out.println("2. TEACHERS");
			System.out.println("3. STUDENTS");
			System.out.println("SELECT ANY OPTION, 9 TO LOGOUT, 0 TO EXIT THE PROGRAM");
			Scanner sc = new Scanner(System.in);
			int DashboardFlag = 1;
			try {
				opt = sc.nextInt();
				DashboardFlag = 0;
			}
			catch(Exception e) {
				System.out.println("INVALID OPTION SELECTED PLEASE SELECT AN VALID OPTION");
			}	
			if (DashboardFlag==0) {
				switch(opt) {
				case 0:
					o = 0;
				case 1:
					o = organizationOptions();
					break;
				case 2:
					o = teacherOptions();
					break;
				case 3:
					o = studentOptions();
					break;
				case 9:
					System.out.println("LOGOUT SUCESSFUL");
					o = 9;
				default:
					System.out.println("INVALID OPTION SELECTED PLEASE SELECT AN VALID OPTION");
					break;
				}
				if(o==0||o==9) {
					return o;
				}
			}
		}
	}
}

class SuperAdmin extends Admin{
	SuperAdmin(String userName,String password){
		this.userName = userName;
		this.password = password;
		this.userType = "Super Admin";		
	}
	void showAdmins(){
		System.out.println("ADMINS");
		System.out.println("=========");
		for (HashMap.Entry<String, User> entry : Quizzle.userList.entrySet()) {
			String key = entry.getKey();
			User value = Quizzle.userList.get(key);
			if(value.userType.equals("Admin")) {
				Admin admin = (Admin) Quizzle.userList.get(key);
				System.out.println("* "+admin.userName);
			}
		}
	}
	void removeAdmin(){
		Scanner sc = new Scanner(System.in);
		while(true) {
			showAdmins();
			System.out.println("Enter USERNAME of Admin to Remove or exit to abort the operation");
			String userName = sc.next();
			if(userName.toLowerCase().equals("exit")) {
				System.out.println("Operation aborted successfully");
				break;
			}
			if(Quizzle.userList.containsKey(userName))
			{	
				User value = Quizzle.userList.get(userName);
				if(value.userType.equals("Admin")) {
					System.out.println("Press 0 to Remove Admin with USERNAME "+userName+" or any other key to abort the operation");
					String opt = sc.next();
					if(opt.equals("0")) {
						Quizzle.userList.remove(userName);
						System.out.println("Admin with USERNAME "+userName+ " Removed Successfully");
						break;
					}
					else {
						System.out.println("Operation aborted successfully");
						break;
					}	
				}
				else {
					System.out.println("USERNAME "+value.userName+" is not an Admin");
					break;
				}
			}
			else {
				System.out.println("USERNAME DOESN'T EXIST");
			}
		}
	}
	void createAdmin() {
		Scanner sc = new Scanner(System.in);
		String userType = "Admin";
		while(true) {
			System.out.println("Please enter a USERNAME or exit to abort the operation");
			String userName = sc.next();
			if(userName.toLowerCase().equals("exit")) {
				System.out.println("Operation aborted successfully");
				break;
			}
			else {
				if(Quizzle.userList.containsKey(userName))
				{	
					System.out.println("USERNAME ALREADY EXIST");
				}
				else {
					System.out.println("Please Enter Password: ");
					String password = sc.next();
					System.out.println();
					System.out.println("Admin Details:");
					System.out.println("User Name: "+userName);
					System.out.println("Password: "+password);
					System.out.println("User Type: "+userType);
					System.out.println("Press 0 to create Admin or any other key to abort the operation");
					String opt = sc.next();
					if(opt.equals("0")) {
						Admin admin = new Admin(userName,password);
						Quizzle.userList.put(userName,admin);
						System.out.println("Admin with USERNAME "+userName+ " Created Successfully");
						break;

					}
					else {
						System.out.println("Operation aborted successfully");
						break;
					}
				}
				
			}
		}
	}
	
	int adminOptions() {
		while(true) {
			int opt = 0;
			System.out.println("1. SHOW ADMINS");
			System.out.println("2. CREATE ADMIN");
			System.out.println("3. REMOVE ADMIN");
			System.out.println("8. PREVIOUS MENU");
			System.out.println("SELECT ANY OPTION, 9 TO LOGOUT, 0 TO EXIT THE PROGRAM");
			Scanner sc = new Scanner(System.in);
			int DashboardFlag = 1;
			try {
				opt = sc.nextInt();
				DashboardFlag = 0;
			}
			catch(Exception e) {
				System.out.println("INVALID OPTION SELECTED PLEASE SELECT AN VALID OPTION");
			}	
			if (DashboardFlag==0) {
				switch(opt) {
				case 0:
					return 0;
				case 1:
					showAdmins();
					break;
				case 2:
					createAdmin();
					break;
				case 3:
					removeAdmin();
					break;
				case 8:
					return 8; //Previous Menu
				case 9:
					System.out.println("LOGOUT SUCESSFUL");
					return 9;
				default:
					System.out.println("INVALID OPTION SELECTED PLEASE SELECT AN VALID OPTION");
					break;
				}
			}
		}
	}
	
	int dashboard(){
		while(true) {
			int opt = 0;
			int o = -1;
			System.out.println("*******************************WELCOME*****************************");
			System.out.println("Username: "+this.userName+"                         User Type: "+this.userType);
			System.out.println();
			System.out.println("****************************DASHBOARD*****************************");
			System.out.println("1. ADMINS");
			System.out.println("2. ORGANIZATIONS");
			System.out.println("3. TEACHERS");
			System.out.println("4. STUDENTS");
			System.out.println("SELECT ANY OPTION, 9 TO LOGOUT, 0 TO EXIT THE PROGRAM");
			Scanner sc = new Scanner(System.in);
			int DashboardFlag = 1;
			try {
				opt = sc.nextInt();
				DashboardFlag = 0;
			}
			catch(Exception e) {
				System.out.println("INVALID OPTION SELECTED PLEASE SELECT AN VALID OPTION");
			}	
			if (DashboardFlag==0) {
				switch(opt) {
				case 0:
					o = 0;
				case 1:
					o = adminOptions();
					break;
				case 2:
					o = organizationOptions();
					break;
				case 3:
					o = teacherOptions();
					break;
				case 4:
					o = studentOptions();
					break;
				case 9:
					System.out.println("LOGOUT SUCESSFUL");
					o = 9;
				default:
					System.out.println("INVALID OPTION SELECTED PLEASE SELECT AN VALID OPTION");
					break;
				}
				if(o==0||o==9) {
					return o;
				}
			}
		}
	}
	
}
public class Quizzle {
	static HashMap<String, User> userList = new HashMap();
	static {	
		SuperAdmin superAdmin = new SuperAdmin("admin","admin");
		userList.put("admin",superAdmin);
		System.out.println("************************************************************");
		System.out.println("************************************************************");
		System.out.println("*********************WELCOME TO QUIZZLE*********************");
		System.out.println("************************************************************");
		System.out.println("************************************************************");
		System.out.println();
		
	}
	
	
	static void login() {
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("****************LOGIN****************");
			System.out.println("Please enter username or exit to exit the program");
			String userName = sc.next();
			if(userName.toLowerCase().equals("exit")) {
				break;
			}
				
			if(userList.containsKey(userName))
			{	
				User user = (User) userList.get(userName);
				System.out.println("Please Enter Password: ");
				String password = sc.next();
				if(user.password.equals(password)) {
					System.out.println("LOGIN SUCCESSFUL");
					int o = user.dashboard();
					if(o==0) {
						break;
					}
				}
				else {
					System.out.println("INVALID PASSWORD");
				}
			}
			else {
				System.out.println("INVALID USERNAME");
			}
		}
	}
	
	public static void main(String[] args) {
		login();
		System.out.println("PROGRAM EXITED SUCCESSFULLY");
		
	}

}
