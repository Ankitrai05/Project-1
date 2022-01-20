import java.sql.*;
import java.util.*;
public class Student					//Inside Student class
{
	public static void main(String args[])		//Inside Main method
	{
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;
		PreparedStatement ps1 = null,ps2 = null;

	Scanner s1 = new Scanner(System.in);		
	try{
		Class.forName("org.postgresql.Driver");			//Connection and Driver established
		conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/st","postgres","rahul@123");
	}
	catch(ClassNotFoundException | SQLException e)			
                {
                    System.out.println(e);
                }
	while(true)
	 {
		System.out.println("\n 1.Display\t 2.Insert\t 3.Delete\t 4.Update\t 5.Search\t 6.Exit");
		System.out.println("\nEnter choice - ");
		int ch = s1.nextInt();
		switch(ch)
		{
			case 1: try{
				stmt= conn.createStatement();
				rs = stmt.executeQuery("select * from student");
				System.out.println("\n Student_No \t Student_Name \t Student_DOB \t Student_DOJ");
					while(rs.next())
					{
					System.out.println(rs.getInt(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getString(3)+"\t\t"+rs.getString(4));
					}
				}
				catch(SQLException e)
                		{
                 		   System.out.println(e);
                		}break;
			case 2: try{
				System.out.println("\n Enter details of Student- ");
				int no = s1.nextInt();
				String nm = s1.next();
				String dob = s1.next();
				String doj = s1.next();
				ps1 = conn.prepareStatement("insert into student values (?,?,?,?)");
				ps1.setInt(1,no); ps1.setString(2,nm); ps1.setString(3,dob); ps1.setString(4,doj);
				ps1.executeUpdate();
				}
				catch(SQLException e)
                		{
                 		   System.out.println(e);
                		}break;
			case 3: try{
				System.out.println("\n Enter Student_No to delete - ");
				int r = s1.nextInt();
				stmt.executeUpdate("delete from student where student_no = "+r);
				}
				catch(SQLException e)
                		{
                 		   System.out.println(e);
                		}break;	
			case 4: try{
				System.out.println("\n Enter student details to update - ");
				int no = s1.nextInt();
				String nm = s1.next();
				String dob = s1.next();
				String doj = s1.next();
				ps2 = conn.prepareStatement("update student set st_name = ?, st_dob = ?, st_doj = ? where student_no = ?");
				ps2.setInt(4,no); ps2.setString(1,nm); ps2.setString(2,dob); ps2.setString(3,doj);
				ps2.executeUpdate();
				}
				catch(SQLException e)
                		{
                 		   System.out.println(e);
                		}break;
			case 5: try{
				System.out.println("\n Enter Student_No to search details - ");
				int no = s1.nextInt();
				rs = stmt.executeQuery("select * from student where student_no = "+no);
				System.out.println("\n Student_No \t Student_Name \t Student_DOB \t Student_DOJ");
				if(rs.next())
					{
						System.out.println(rs.getInt(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getString(3)+"\t\t"+rs.getString(4));			
					}
				}
				catch(SQLException e)
                		{
                 		   System.out.println(e);
                		}break;
			case 6: System.exit(1);

		}
	 }
	}
}

