package com.theironyard.javawithclojure.jhporter;

import org.h2.tools.Server;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main
{

    public static void insertTodo(Connection conn, String text) throws SQLException
    {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO todos VALUES(NULL,?,FALSE)");
        stmt.setString(1,text);
        stmt.execute();
    }

    public static ArrayList<ToDoItem> selectTodos(Connection conn) throws SQLException
    {
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM todos");
        ResultSet results = stmt.executeQuery();
        ArrayList<ToDoItem> items = new ArrayList<>();
        while (results.next())
        {
            int id = results.getInt("id");
            String text = results.getString("text");
            boolean isDone = results.getBoolean("is_done");
            ToDoItem item = new ToDoItem(id, text, isDone);
            items.add(item);
        }
        return items;
    }

    public static void toggleToDo(Connection conn, int id) throws SQLException
    {
        PreparedStatement stmt = conn.prepareStatement("UPDATE todos SET is_done = NOT is_done WHERE id = ?");
        stmt.setInt(1,id);
        stmt.execute();

    }


    public static void createItem(Scanner input, Connection conn) throws SQLException
    {
        System.out.printf("\nPlease enter your to-do item: ");
        String text = input.nextLine();
        //ToDoItem item = new ToDoItem(text, false);
        //items.add(item);
        insertTodo(conn, text);
    }

    public static void toggleItem(Scanner input, Connection conn) throws SQLException
    {
        System.out.printf("\nEnter the number of the item you wish to toggle: ");
        String numStr = input.nextLine();
        try
        {
            int num = Integer.valueOf(numStr);
            //ToDoItem tempItem = items.get(num - 1);
            //tempItem.setDone(!tempItem.isDone());
            toggleToDo(conn, num);
        }
        catch (NumberFormatException e)
        {
            System.err.printf("\nYou Didn't Type A Number!!!");
        }
        catch (IndexOutOfBoundsException e)
        {
            System.err.printf("\nItem Does Not Exist!!!");
        }
    }

    public static void showItems(Connection conn) throws SQLException
    {
        //int i = 1;
        System.out.printf("\n%5s|%3s.", "Done", "  Task");
        System.out.printf("\n________________________________");
        for( ToDoItem toDoItem : selectTodos(conn))
        {
            String checkBox = "[ ]";
            if(toDoItem.isDone()) {
                checkBox = "[x]";
            }
            System.out.printf("\n%5s|%3d. %s", checkBox, toDoItem.getId(), toDoItem.getText());
            //i++;
        }
        System.out.printf("\n\n");
    }

    public static void main(String[] args) throws SQLException
    {
        Server.createWebServer().start();
        Connection conn = DriverManager.getConnection("jdbc:h2:./main");

        Statement stmt = conn.createStatement();
        stmt.execute("CREATE TABLE IF NOT EXISTS todos (id IDENTITY, text VARCHAR, is_done BOOLEAN)" );

        //ArrayList<ToDoItem> items = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        boolean run = true;
        while (run)
        {
            System.out.printf("\n1. Create Item\n2. Toggle Item\n3. List Items\n4. Quit");
            String option = input.nextLine();

            switch(option)
            {
                case "1":
                    createItem(input, conn);
                    break;
                case "2":
                    toggleItem(input, conn);
                    break;
                case "3":
                    showItems(conn);
                    break;
                case "4":
                    run = false;
                    break;
                default:
                    System.err.printf("\nInvalid Option!\n");
                    break;
            }
        }
    }
}
