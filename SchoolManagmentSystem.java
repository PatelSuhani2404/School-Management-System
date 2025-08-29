import java.sql.*;
import java.util.HashMap;
import java.util.Scanner;

class SchoolManagementSystem{
    private static HashMap<Integer,Student> studentMap=new HashMap<>();
    private static HashMap<Integer,Staff> staffMap=new HashMap<>();
    private static HashMap<Integer,Subject> subjectMap=new HashMap<>();
    public static void main(String[] args) throws Exception {
        Scanner sc=new Scanner(System.in);
        String driverName="com.mysql.cj.jdbc.Driver";
        Class.forName(driverName);
        System.out.println("Driver loaded successfully.");
        String dburl="jdbc:mysql://localhost:3306/schoolmanagementsystem";
        String dbuser="root";
        String dbpass="";
        Connection con=DriverManager.getConnection(dburl, dbuser, dbpass);
        if(con!=null){
         System.out.println("Connection done successfully.");
        }else{
         System.out.println("Not connected.");
        }
        int choice=0;
        do{
            System.out.println("Press 1 for do operations in student class : ");
            System.out.println("Press 2 for do operations in staff class : ");
            System.out.println("Press 3 for do operations in Subject class : ");
            System.out.println("Press 4 for exit.");
            System.out.println("Enter your choice : ");
            choice=sc.nextInt();
            switch (choice) {
                case 1:
                    studentOperations(sc,con);
                    break;
                case 2:
                    staffOperation(sc, con);
                    break;
                case 3:
                subjectOperations(sc,con);
                    break;
                case 4:
                    System.out.println("you choose exit.");    
                    break;
                default:
                    System.out.println("Enter proper choice.");
                    break;
            }
        }while(choice!=4);
        sc.close();
    }
    static void studentOperations(Scanner sc,Connection con) throws Exception{
        int cs=0;
        do{
            System.out.println("You choose operations in student class.");
                    System.out.println("Press 1 for Add student information.");
                    System.out.println("Press 2 for Update student information based on their rollNo.");
                    System.out.println("Press 3 for Delete student information based on their rollNo.");
                    System.out.println("Press 4 for Search student information based on their rollNo.");
                    System.out.println("Press 5 for get information about Student table.");
                    System.out.println("Press 6 for Exit from Student class.");
                    System.out.print("Enter your choice for do operations in student class : ");
                    cs=sc.nextInt();
                    switch (cs) {
                        case 1:{
                            System.out.print("Enter rollNo of Student : ");
                            int rollNo=sc.nextInt();
                            Student.addStudent(sc,rollNo,con,studentMap);
                        }
                            break;
                        case 2:{
                            Student.updateStudent(sc,con,studentMap);
                        }
                            break;
                        case 3:{
                            Student.deleteStudent(sc,con,studentMap);
                        }
                            break;
                        case 4:{
                            Student.searchStudent(sc,studentMap);
                        }
                            break;
                        case 5:{
                            Student.tableInfo(sc,con);
                        }
                            break;
                        case 6:
                            System.out.println("You choose exit from student class.");
                            break;
                        default:
                            System.out.println("Enter proper choice for do operations in student class.");
                            break;
                    }
        }while(cs!=6);
    }
    static void loadStudent(Connection con) {
        try{
            String sql="select * from student";
            PreparedStatement pst=con.prepareStatement(sql);
            ResultSet rs=pst.executeQuery();
            while (rs.next()) {
                Student st=new Student(rs.getInt(1),rs.getString(2),rs.getString(3),
                rs.getString(4),rs.getString(5),rs.getInt(6),rs.getString(7),rs.getDouble(8),
                rs.getString(9),rs.getString(10),rs.getInt(11),rs.getString(12),
                rs.getString(13),rs.getString(14));
                studentMap.put(st.getRollNo(), st);
            }
            System.out.println("Student loaded into Hash Map.");
        }catch(Exception e){
            System.out.println("Error : "+e.getMessage());
        }
    }
    static void staffOperation(Scanner sc,Connection con) throws Exception{
        int ct=0;
                do{
                    System.out.println("You choose operations in staff class.");
                    System.out.println("Press 1 for Add staff information.");
                    System.out.println("Press 2 for Update staff information based on their id.");
                    System.out.println("Press 3 for Delete staff information based on their id.");
                    System.out.println("Press 4 for Search staff information based on their id.");
                    System.out.println("Press 5 for get information about staff table.");
                    System.out.println("Press 6 for Exit from staff class.");
                    System.out.print("Enter your choice for do operations in staff class : ");
                    ct=sc.nextInt();
                    switch (ct) {
                        case 1:{
                            System.out.print("Enter id of staff : ");
                            int id=sc.nextInt();
                            Staff.addStaff(sc,id,con,staffMap);
                        }
                            break;
                        case 2:{
                            Staff.updateStaff(sc, con,staffMap);
                        }
                            break;
                        case 3:{
                            Staff.deleteStaff(sc,con,staffMap);
                        }
                            break;
                        case 4:{
                            Staff.searchStaff(sc,staffMap);
                        }
                            break;
                        case 5:{
                            Staff.tableInfo(sc,con);
                        }
                            break;
                        case 6:
                            System.out.println("You choose exit from staff class.");
                            break;
                        default:
                            System.out.println("Enter proper choice for do operations in staff class.");
                            break;
                    }
                }while(ct!=6);
    }
    static void loadstaff(Connection con) {
        try{
            String sql="select * from staff";
            PreparedStatement pst=con.prepareStatement(sql);
            ResultSet rs=pst.executeQuery();
            while (rs.next()) {
                Staff t=new Staff(rs.getInt(1),rs.getString(2),rs.getString(3),
                rs.getString(4),rs.getString(5),rs.getDouble(6),rs.getString(7),
                rs.getInt(8),rs.getString(9),rs.getString(10),rs.getString(11),
                rs.getString(12));
                staffMap.put(t.getStaffId(), t);
            }
            System.out.println("staff loaded into Hash Map.");
        }catch(Exception e){
            System.out.println("Error : "+e.getMessage());
        }
    }
    static void subjectOperations(Scanner sc,Connection con) throws Exception{
        int cc=0;
                do{
                    System.out.println("You choose operations in subject class.");
                    System.out.println("Press 1 for Add subject information.");
                    System.out.println("Press 2 for Update subject information based on their id.");
                    System.out.println("Press 3 for Delete subject information based on their id.");
                    System.out.println("Press 4 for Search subject information based on their id.");
                    System.out.println("Press 5 for get information about subject table.");
                    System.out.println("Press 6 for Exit from subject class.");
                    System.out.print("Enter your choice for do operations in subject class : ");
                    cc=sc.nextInt();
                    switch (cc) {
                        case 1:{
                            System.out.print("Enter id of subject : ");
                            int id=sc.nextInt();
                            Subject.addSubject(sc,id,con,subjectMap);
                        }
                            break;
                        case 2:{
                            Subject.updateSubject(sc, con,subjectMap);
                        }
                            break;
                        case 3:{
                            Subject.deleteSubject(sc,con,subjectMap);
                        }
                            break;
                        case 4:{
                            Subject.searchSubject(sc,subjectMap);
                        }
                            break;
                        case 5:{
                            Subject.tableInfo(sc,con);
                        }
                            break;
                        case 6:
                            System.out.println("You choose exit from subject class.");
                            break;
                        default:
                            System.out.println("Enter proper choice for do operations in subject class.");
                            break;
                    }
                }while(cc!=6);
            
    }
    static void loadsubject(Connection con) {
        try{
            String sql="select * from subject";
            PreparedStatement pst=con.prepareStatement(sql);
            ResultSet rs=pst.executeQuery();
            while (rs.next()) {
                Subject s=new Subject(rs.getInt(1),rs.getInt(2),rs.getString(3),
                rs.getString(4));
                subjectMap.put(s.getSubId(), s);
            }
            System.out.println("subject loaded into Hash Map.");
        }catch(Exception e){
            System.out.println("Error : "+e.getMessage());
        }
    }
}
class Student{
    int rollNo;
    String studentfn;
    String studentln;
    String fatherName;
    String Gender;
    int std;
    String div;
    double lastStdPer;
    String mobileNo;
    String DOB;
    int age;
    String address;
    String city;
    String state;
    public Student(int rollNo, String studentfn, String studentln, String fatherName, String Gender, int std, String div,
            double lastStdPer, String mobileNo, String DOB, int age,String address,String city,String state) {
        this.rollNo = rollNo;
        this.studentfn = studentfn;
        this.studentln = studentln;
        this.fatherName = fatherName;
        this.Gender=Gender;
        this.std = std;
        this.div = div;
        this.lastStdPer = lastStdPer;
        this.mobileNo = mobileNo;
        this.DOB = DOB;
        this.age = age;
        this.address=address;
        this.city=city;
        this.state=state;
    }
    public int getRollNo() {
        return rollNo;
    }
    static Student addStudent(Scanner sc,int rollNo,Connection con,HashMap<Integer,Student> studentMap) throws Exception{
        String sql="insert into student values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pst=con.prepareStatement(sql);
        pst.setInt(1, rollNo);
        System.out.print(" Enter Student First Name : ");
        String studentfn=sc.next();
        pst.setString(2, studentfn);
        System.out.print("Enter Student Last Name : ");
        String studentln=sc.next();
        pst.setString(3, studentln);
        System.out.print("Enter Student Father Name : ");
        String fatherName=sc.next();
        pst.setString(4, fatherName);
        System.out.print("Enter Student Gender : ");
        String Gender=sc.next();
        pst.setString(5, Gender);
        System.out.print("Enter Student Std : ");
        int std=sc.nextInt();
        pst.setInt(6, std);
        System.out.print("Enter Student div : ");
        String div=sc.next();
        pst.setString(7, div);
        System.out.print("Enter Student preveious std per : ");
        Double lastStdPer=sc.nextDouble();
        pst.setDouble(8, lastStdPer);
        System.out.print("Enter father mobile no. : ");
            String mobileNo=sc.next();
            for(int i=0;i<mobileNo.length();i++){
                while(mobileNo.charAt(i)<'0'||mobileNo.charAt(i)>'9'||mobileNo.length()<10||mobileNo.length()>10){
                    System.out.println("Invalid mobile number please enter valid mobile number : ");
                    mobileNo=sc.next();
                }
            }
        pst.setString(9, mobileNo);
        System.out.print("Enter Student date of birth : ");
        String DOB=sc.next();
        pst.setString(10, DOB);
        System.out.print("Enter Student Age : ");
        int age=sc.nextInt();
        pst.setInt(11, age);
        System.out.print("Enter Student Address : ");
        String address=sc.next();
        pst.setString(12, address);
        System.out.print("Enter Student city : ");
        String city=sc.next();
        pst.setString(13, city);
        System.out.print("Enter Student state : ");
        String state=sc.next();
        pst.setString(14, state);
        int r=pst.executeUpdate();
        if(r>0){
            System.out.println("Information inserted successfully.");
        }else{
            System.out.println("Not inserted.");
        }
        Student st=new Student(rollNo, studentfn, studentln, fatherName, Gender, std, div, lastStdPer, mobileNo, 
                            DOB, age, address, city, state);
        studentMap.put(rollNo, st);
        return st;
    }
    static Student updateStudent(Scanner sc,Connection con,HashMap<Integer,Student> studentMap) throws Exception{
        System.out.println("Enter Roll No of student Whose data you want to update : ");
        int rollNo=sc.nextInt();
        if(studentMap.containsKey(rollNo)){
            studentMap.remove(rollNo);
            String sql="update student set studentfn=?, studentln=?, fatherName=?, studentGender=?, std=?, div=?, "+
            "lastStdPer=?, mobileNo=?, DOB=?, age=?, address=?, city=?, state=? where rollNo=?";
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setInt(14, rollNo);
        System.out.print(" Enter Student First Name : ");
        String studentfn=sc.next();
        pst.setString(1, studentfn);
        System.out.print("Enter Student Last Name : ");
        String studentln=sc.next();
        pst.setString(2, studentln);
        System.out.println("Enter Student Father Name : ");
        String fatherName=sc.next();
        pst.setString(3, fatherName);
        System.out.print("Enter Student Gender : ");
        String Gender=sc.next();
        pst.setString(4, Gender);
        System.out.println("Enter Student Std : ");
        int std=sc.nextInt();
        pst.setInt(5, std);
        System.out.print("Enter Student div : ");
        String div=sc.next();
        pst.setString(6, div);
        System.out.print("Enter Student preveious std per : ");
        Double lastStdPer=sc.nextDouble();
        pst.setDouble(7, lastStdPer);
        System.out.print("Enter father mobile no. : ");
            String mobileNo=sc.next();
            for(int i=0;i<mobileNo.length();i++){
                while(mobileNo.charAt(i)<'0'||mobileNo.charAt(i)>'9'||mobileNo.length()<10||mobileNo.length()>10){
                    System.out.println("Invalid mobile number please enter valid mobile number : ");
                    mobileNo=sc.next();
                }
            }
        pst.setString(8, mobileNo);
        System.out.print("Enter Student date of birth : ");
        String DOB=sc.next();
        pst.setString(9, DOB);
        System.out.print("Enter Student Age : ");
        int age=sc.nextInt();
        pst.setInt(10, age);
        System.out.print("Enter Student Address : ");
        String address=sc.next();
        pst.setString(11, address);
        System.out.print("Enter Student city : ");
        String city=sc.next();
        pst.setString(12, city);
        System.out.print("Enter Student state : ");
        String state=sc.next();
        pst.setString(13, state);
        int r=pst.executeUpdate();
        if(r>0){
            System.out.println("Information updated successfully.");
        }else{
            System.out.println("Not updated.");
        }
        Student stu=new Student(rollNo, studentfn, studentln, fatherName, Gender, std, div, lastStdPer, mobileNo, 
                            DOB, age, address, city, state);
        studentMap.put(rollNo, stu);
            return stu;
        }else{
            System.out.println("Student not found with this roll No "+rollNo+" .");
            return null;
        }
    }
    static void deleteStudent(Scanner sc,Connection con,HashMap<Integer,Student> studentMap) throws Exception{
        System.out.println("Enter roll No whose details you want to delete : ");
        int rollNo=sc.nextInt();
        if(studentMap.containsKey(rollNo)){
            studentMap.remove(rollNo);
            Statement st=con.createStatement();
            String sql="delete from student where rollNo = "+rollNo;
            int r=st.executeUpdate(sql);
            if(r>0){
                System.out.println("Information deleted successfully based on Student roll no.");
            }else{
                System.out.println("Not deleted.");
            }
        }else{
            System.out.println("Student not found with this roll no. "+rollNo+".");
        }
    }
    static void searchStudent(Scanner sc,HashMap<Integer,Student> studentMap){
        System.out.println("Enter roll no whose data you want to search : ");
        int rollNo=sc.nextInt();
        if(studentMap.containsKey(rollNo)){
            Student st=studentMap.get(rollNo);
            System.out.println("Student Roll No. : "+st.rollNo);
            System.out.println("Student First Name : "+st.studentfn);
            System.out.println("Student Last Name : "+st.studentln);
            System.out.println("Student Father Name : "+st.fatherName);
            System.out.println("Student Gender : "+st.Gender);
            System.out.println("Student std : "+st.std);
            System.out.println("Student div : "+st.div);
            System.out.println("Student Last Std per : "+st.lastStdPer);
            System.out.println("Mobile Father No. : "+st.mobileNo);
            System.out.println("Student Date of Birth : "+st.DOB);
            System.out.println("Student Age : "+st.age);
            System.out.println("Student Address : "+st.address);
            System.out.println("Student City : "+st.city);
            System.out.println("Student State : "+st.state);
        }else{
            System.out.println("Student not found with this roll no. "+rollNo+" .");
        }
    } 
    static void tableInfo(Scanner sc,Connection con) throws Exception{
        String sql="select * from student";
        PreparedStatement pst=con.prepareStatement(sql);
        ResultSet rs=pst.executeQuery();
        ResultSetMetaData rsmd=rs.getMetaData();
        System.out.println("Name of table : "+rsmd.getTableName(1));
        System.out.println("No. of column in Student table : "+rsmd.getColumnCount());
        System.out.println("Enter column no. whose name you want to find : ");
        int cn=sc.nextInt();
        System.out.println("Name of column "+cn+" : "+rsmd.getColumnName(cn));
        System.out.println("Enter column no. whose type you want to find : ");
        int cp=sc.nextInt();
        System.out.println("Datatype of column "+cp+" : "+rsmd.getColumnTypeName(cp));
        System.out.println("Enter column no. whose length you want to find : ");
        int cl=sc.nextInt();
        System.out.println("Length of column "+cl+" : "+rsmd.getColumnDisplaySize(cl));
    }
}
class Staff{
    int staffId;
    String fn;
    String ln;
    String Gender;
    String department;
    double salary;
    String DOJ;
    int yearsOfExperience;
    String mno;
    String address;
    String city;
    String state;
    public Staff(int staffId, String fn, String ln, String Gender, String department, double salary, String DOJ,
            int yearsOfExperience, String mno, String address, String city, String state) {
        this.staffId = staffId;
        this.fn = fn;
        this.ln = ln;
        this.Gender = Gender;
        this.department = department;
        this.salary = salary;
        this.DOJ = DOJ;
        this.yearsOfExperience = yearsOfExperience;
        this.mno = mno;
        this.address = address;
        this.city = city;
        this.state = state;
    }
    public int getStaffId() {
        return staffId;
    }
    static Staff addStaff(Scanner sc,int id,Connection con,HashMap<Integer,Staff> staffMap) throws Exception{
        String sql="insert into staff values(?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pst=con.prepareStatement(sql);
        pst.setInt(1, id);
        System.out.print("Enter staff First name : ");
        String fn=sc.next();
        pst.setString(2, fn);
        System.out.print("Enter staff Last name : ");
        String ln=sc.next();
        pst.setString(3, ln);
        System.out.print("Enter staff Gender : ");
        String Gender=sc.next();
        pst.setString(4, Gender);
        System.out.print("Enter staff Department : ");
        String department=sc.next();
        pst.setString(5, department);
        System.out.print("Enter staff Salary : ");
        double salary=sc.nextDouble();
        pst.setDouble(6, salary);
        System.out.print("Enter date of joining : ");
        String DOJ=sc.next();
        pst.setString(7, DOJ);
        System.out.print("Enter Years of Experience : ");
        int yearsOfExperience=sc.nextInt();
        pst.setInt(8, yearsOfExperience);
        System.out.print("Enter staff mobile no. : ");
        String mno=sc.next();
        for(int i=0;i<mno.length();i++){
            while(mno.charAt(i)<'0'||mno.charAt(i)>'9'||mno.length()<10||mno.length()>10){
                System.out.println("Invalid mobile number please enter valid mobile number : ");
                mno=sc.next();
            }
        }
        pst.setString(9, mno);
        System.out.print("Enter staff Address : ");
        String address=sc.next();
        pst.setString(10, address);
        System.out.print("Enter staff city : ");
        String city=sc.next();
        pst.setString(11, city);
        System.out.print("Enter staff state : ");
        String state=sc.next();
        pst.setString(12, state);
        int r=pst.executeUpdate();
        if(r>0){
            System.out.println("Information inserted successfully.");
        }else{
            System.out.println("Not inserted.");
        }
        Staff s=new Staff(id,  fn,  ln,  Gender,  department,  salary,  DOJ, yearsOfExperience,  mno,  address,  city,  state);
        staffMap.put(id, s);
        return s;
    }
    static Staff updateStaff(Scanner sc,Connection con,HashMap<Integer,Staff> staffMap) throws Exception{
        System.out.println("Enter staff ID whose information you want to update : ");
        int staffId=sc.nextInt();
        if(staffMap.containsKey(staffId)){
            staffMap.remove(staffId);
            String sql="update staff set fn=?,ln=?,Gender=?,department=?,salary=?,DOJ=?,yearsOfExperience=?,mno=?,"+
            "address=?,city=?,state=? where staffId=?";
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setInt(12, staffId);
        System.out.print("Enter staff First name : ");
        String fn=sc.next();
        pst.setString(1, fn);
        System.out.print("Enter staff Last name : ");
        String ln=sc.next();
        pst.setString(2, ln);
        System.out.print("Enter staff Gender : ");
        String Gender=sc.next();
        pst.setString(3, Gender);
        System.out.print("Enter staff Department : ");
        String department=sc.next();
        pst.setString(4, department);
        System.out.print("Enter staff Salary : ");
        double salary=sc.nextDouble();
        pst.setDouble(5, salary);
        System.out.print("Enter date of joining : ");
        String DOJ=sc.next();
        pst.setString(6, DOJ);
        System.out.print("Enter Years of Experience : ");
        int yearsOfExperience=sc.nextInt();
        pst.setInt(7, yearsOfExperience);
        System.out.print("Enter staff mobile no. : ");
        String mno=sc.next();
        for(int i=0;i<mno.length();i++){
            while(mno.charAt(i)<'0'||mno.charAt(i)>'9'||mno.length()<10||mno.length()>10){
                System.out.println("Invalid mobile number please enter valid mobile number : ");
                mno=sc.next();
            }
        }
        pst.setString(8, mno);
        System.out.print("Enter staff Address : ");
        String address=sc.next();
        pst.setString(9, address);
        System.out.print("Enter staff city : ");
        String city=sc.next();
        pst.setString(10, city);
        System.out.print("Enter staff state : ");
        String state=sc.next();
        pst.setString(11, state);
        int r=pst.executeUpdate();
        if(r>0){
            System.out.println("Information updated successfully.");
        }else{
            System.out.println("Not updated.");
        }
        Staff su=new Staff(staffId,  fn,  ln,  Gender,  department,  salary,  DOJ, yearsOfExperience,  mno,  address,  city,  state);
        staffMap.put(staffId, su);
        return su;
        }else{
            System.out.println("Staff not found with id "+staffId+" .");
            return null;
        }
    }
    static void deleteStaff(Scanner sc,Connection con,HashMap<Integer,Staff> staffMap) throws Exception{
        System.out.println("Enter staff ID whose details you want to delete : ");
        int staffId=sc.nextInt();
        if(staffMap.containsKey(staffId)){
            staffMap.remove(staffId);
            Statement st=con.createStatement();
            String sql="delete from staff where staffId = "+staffId;
            int r=st.executeUpdate(sql);
            if(r>0){
                System.out.println("Information deleted successfully based on staff id.");
            }else{
                System.out.println("Not deleted.");
            }
        }else{
            System.out.println("Staff not found with this staff Id. "+staffId+".");
        }
    }
    static void searchStaff(Scanner sc,HashMap<Integer,Staff> staffMap) throws Exception {
        System.out.println("Enter staff is whose data you want to search : ");
        int staffId=sc.nextInt();
        if(staffMap.containsKey(staffId)){
            Staff s=staffMap.get(staffId);
            System.out.println("Staff Roll No. : "+s.staffId);
            System.out.println("Staff First Name : "+s.fn);
            System.out.println("Staff Last Name : "+s.ln);
            System.out.println("Staff Gender : "+s.Gender);
            System.out.println("Staff Department : "+s.department);
            System.out.println("Salary : "+s.salary);
            System.out.println("Date of joining : "+s.DOJ);
            System.out.println("Years of Experience : "+s.yearsOfExperience);
            System.out.println("Mobile No : "+s.mno);
            System.out.println("Staff Address : "+s.address);
            System.out.println("Staff City : "+s.city);
            System.out.println("Staff State : "+s.state);
        }else{
            System.out.println("Staff not found with this staff id "+staffId+" .");
        }
    }
    static void tableInfo(Scanner sc,Connection con) throws Exception{
        String sql="select * from staff";
        PreparedStatement pst=con.prepareStatement(sql);
        ResultSet rs=pst.executeQuery();
        ResultSetMetaData rsmd=rs.getMetaData();
        System.out.println("Name of table : "+rsmd.getTableName(1));
        System.out.println("No. of column in Student table : "+rsmd.getColumnCount());
        System.out.println("Enter column no. whose name you want to find : ");
        int cn=sc.nextInt();
        System.out.println("Name of column "+cn+" : "+rsmd.getColumnName(cn));
        System.out.println("Enter column no. whose type you want to find : ");
        int cp=sc.nextInt();
        System.out.println("Datatype of column "+cp+" : "+rsmd.getColumnTypeName(cp));
        System.out.println("Enter column no. whose length you want to find : ");
        int cl=sc.nextInt();
        System.out.println("Length of column "+cl+" : "+rsmd.getColumnDisplaySize(cl));
    }
}
class Subject{
    int subId;
    int staffId;
    String subName;
    String subCode;
    public Subject(int subId, int staffId, String subName, String subCode) {
        this.subId = subId;
        this.staffId = staffId;
        this.subName = subName;
        this.subCode = subCode;
    }
    public int getSubId() {
        return subId;
    }
    static Subject addSubject(Scanner sc,int id,Connection con,HashMap<Integer,Subject> subjectMap) throws Exception{
        String sql="insert into subject values(?,?,?,?)";
        PreparedStatement pst=con.prepareStatement(sql);
        pst.setInt(1, id);
        System.out.print("Enter Staff Id : ");
        int staffId=sc.nextInt();
        pst.setInt(2, staffId);
        System.out.print("Enter Subject Name : ");
        String subName=sc.next();
        pst.setString(3, subName);
        System.out.println("Enter Subject Code : ");
        String subCode=sc.next();
        pst.setString(4, subCode);
        int r=pst.executeUpdate();
        if(r>0){
            System.out.println("Information inserted successfully.");
        }else{
            System.out.println("Not inserted.");
        }
        Subject s=new Subject(id,staffId,subName,subCode);
        subjectMap.put(id, s);
        return s;
    }
    static Subject updateSubject(Scanner sc,Connection con,HashMap<Integer,Subject> subjectMap) throws Exception{
        System.out.println("Enter Subject ID whose detail you want to update : ");
        int subId=sc.nextInt();
        if(subjectMap.containsKey(subId)){
            String sql="update subject set staffId=?,subName=?,subCode=? where subId=?";
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setInt(4, subId);
            System.out.print("Enter Staff Id : ");
            int staffId=sc.nextInt();
            pst.setInt(1, staffId);
            System.out.print("Enter Subject Name : ");
            String subName=sc.next();
            pst.setString(2, subName);
            System.out.println("Enter Subject Code : ");
            String subCode=sc.next();
            pst.setString(3, subCode);
            int r=pst.executeUpdate();
            if(r>0){
                System.out.println("Information updated successfully.");
            }else{
                System.out.println("Not updated.");
            }
            Subject su=new Subject(subId, staffId, subName, subCode);
            subjectMap.put(subId, su);
            return su;
        }else{
            System.out.println("Subject not found.");
            return null;
        }
    }
    static void deleteSubject(Scanner sc,Connection con,HashMap<Integer,Subject> subjectMap) throws Exception{
        System.out.println("Enter Subject ID whose detail you want to delete : ");
        int subId=sc.nextInt();
        if(subjectMap.containsKey(subId)){
            subjectMap.remove(subId);
            Statement st=con.createStatement();
            String sql="delete from subject where subId = "+subId;
            int r=st.executeUpdate(sql);
            if(r>0){
                System.out.println("Information deleted successfully based on subject id.");
            }else{
                System.out.println("Not deleted.");
            }
        }else{
            System.out.println("Subject not found.");
        }
    }
    static void searchSubject(Scanner sc,HashMap<Integer,Subject> subjectMap) throws Exception{
        System.out.println("Enter Subject ID whose detail you want to search : ");
        int subId=sc.nextInt();
        if(subjectMap.containsKey(subId)){
            Subject s=subjectMap.get(subId);
            System.out.println("Subject ID : "+s.subId);
            System.out.println("Stafft ID : "+s.staffId);
            System.out.println("Subject Name : "+s.subName);
            System.out.println("Subject Code : "+s.subCode);
        }else{
            System.out.println("Subject not found.");
        }
    }
    static void tableInfo(Scanner sc,Connection con) throws Exception{
        String sql="select * from subject";
        PreparedStatement pst=con.prepareStatement(sql);
        ResultSet rs=pst.executeQuery();
        ResultSetMetaData rsmd=rs.getMetaData();
        System.out.println("Name of table : "+rsmd.getTableName(1));
        System.out.println("No. of column in Student table : "+rsmd.getColumnCount());
        System.out.println("Enter column no. whose name you want to find : ");
        int sn=sc.nextInt();
        System.out.println("Name of column "+sn+" : "+rsmd.getColumnName(sn));
        System.out.println("Enter column no. whose type you want to find : ");
        int sp=sc.nextInt();
        System.out.println("Datatype of column "+sp+" : "+rsmd.getColumnTypeName(sp));
        System.out.println("Enter column no. whose length you want to find : ");
        int sl=sc.nextInt();
        System.out.println("Length of column "+sl+" : "+rsmd.getColumnDisplaySize(sl));
    }
}