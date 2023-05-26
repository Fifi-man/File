package org.example;
import java.net.ConnectException;
import java.util.Scanner;

import java.sql.*;

import static java.lang.System.*;

class sql {
    private static String DB_Driver = "org.postgresql.Driver";
    private static String url = "jdbc:postgresql://localhost:5432/postgres";
    private static String user = "postgres";
    private static String password = "postgres";
    static Connection con ;
    static {
        try {
            con = DriverManager.getConnection(url, user, password);
            out.println("DB is Activity!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    static String str;
    static Scanner s = new Scanner(System.in);
    static int index = 0;

    // There are function for work with SQL;
    public static void select(Connection con) throws SQLException {
        List mL = new List();
        out.println("If you want back, say - EXIT");
        out.print("Enter Name List:");
            try {
                String name = s.nextLine();
                    if(name.equals("EXIT")) {
                        mL.Intic();
                    }
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery("select dealing from " + name + ";");
                    while (rs.next()) {
                        str = "|" + rs.getString("dealing");
                        out.println(str);
                    }
                    rs.close();
                    stmt.close();
                    out.println("-------------------------------------");
                    out.println("1 - menu;");
                    out.println("2 - EXIT;");
                    name = s.nextLine();
                    if(name.equals("1")) {
                        mL.Intic();
                    } else { out.println();
                    }
            } catch (SQLException e) {
                out.println(e.getMessage());
            }
    }
    public static void create(Connection con) throws SQLException {
        List mL = new List();
        out.println("If you want back, say - EXIT");
        out.println("Now, just write the Name of table:");
        String name = s.nextLine();
            if (name.equals("EXIT")) {
                mL.Intic();
            } else {
                try {
                    PreparedStatement stmt = con.prepareStatement("create table " + name + " (dealing varchar(100))");
                    stmt.executeUpdate();
                    out.println(" add ");
                    stmt.close();
                } catch (SQLException e) {
                    out.println(e.getMessage());
                } finally {
                    con.close();
                }
                out.println("-------------------------------------");
                out.println("1 - menu;");
                out.println("2 - EXIT;");
                name = s.nextLine();
                if(name.equals("1")) {
                    mL.Intic();
                } else {
                    out.println();
                }
            }
    }

    public static void insert (Connection con) throws SQLException{
        List mL = new List();
        try {
            out.println("From the Beginning, select List:");
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select table_name from information_schema.tables where table_schema = 'public'");
                while(rs.next()) {
                    str = rs.getString("table_name");
                    out.println(str);
                }
                rs.close();
                st.close();
                String name = s.nextLine();
                if(name.equals("EXIT")) {
                    mL.Intic();
                }
                out.println("Now just write:");
                while(true) {
                    String text = s.nextLine();
                    if(text.equals("EXIT")){
                        out.println("Your stuff is save!");
                        mL.Intic();
                    } else {
                        PreparedStatement stmt = con.prepareStatement("insert into " + name + " values(?)");
                        stmt.setString(1, text);
                        stmt.executeUpdate();
                    }
                }
            } catch (SQLException e) {
                out.println(e.getMessage());
        }
    }
    public static void delete (Connection con) throws SQLException{
        List mL = new List();
        out.print("Just say me, what List u wanna delete: ");
        try {
            String name = s.nextLine();
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery("drop table " + name + ";");
                    out.println("Done");
                    out.println();
                    rs.close();
                    stmt.close();
                    out.println("-------------------------------------");
                    out.println("1 - menu;");
                    out.println("2 - EXIT;");
                    name = s.nextLine();
                    if(name.equals("1")) {
                        mL.Intic();
                    } else {
                        out.println();
                    }
        } catch (SQLException e) {
            out.println(e.getMessage());
        }
    }
    public static void look (Connection con) throws SQLException {
        List mL = new List();
        try {
            out.println("There are:");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select table_name from information_schema.tables where table_schema = 'public'");
            while (rs.next()) {
                str = rs.getString("table_name");
                out.println(str);
            }
            rs.close();
            st.close();
            out.println("-------------------------------------");
            out.println("1 - menu;");
            out.println("2 - EXIT;");
            String name = s.nextLine();
            if(name.equals("1")) {
                mL.Intic();
            } else { out.println();
            }
        } catch (SQLException e) {
            out.println(e.getMessage());
        }
    }

    // New Class in Class ;
    static class List {
        Scanner s = new Scanner(System.in);
        String str;
        public void Intic() throws SQLException{
            out.println("---------------------------------------------------------");
            out.println("       | -Press 1 for create table-              |");
            out.println("       | -Press 2 for look at content-           |");
            out.println("       | -Press 3 for select and work with List- |");
            out.println("       | -Press 4 for delete List-               |");
            out.println("       | -Press 5 for look at Lists-             |");
            out.println("       | -Say: EXIT, and you go Back, or Leave-  |");
            out.println("---------------------------------------------------------");
            str = s.nextLine();
            if(str.equals("1")) {
                create(con);
            }
             if(str.equals("2")) {
                select(con);
            }
            if(str.equals("4")) {
                delete(con);
            }
            if(str.equals("5")) {
                look(con);
            }
            else if(str.equals("3")) {
                insert(con);
            }else {
                out.println("B-bye");
            }
        }
    }
    public static void main (String[] args) throws SQLException {
       List go = new List();
        out.println("---------------------------------------------------------");
        out.println("|Hello, now you are working in LIST-of-Stuff by Postgres|");
        out.println("---------------------------------------------------------");
       go.Intic();

    }
}

