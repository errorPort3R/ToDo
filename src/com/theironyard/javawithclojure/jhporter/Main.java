package com.theironyard.javawithclojure.jhporter;

import java.util.ArrayList;
import java.util.Scanner;

public class Main
{

    public static void createItem(Scanner input, ArrayList<ToDoItem> items)
    {
        System.out.printf("\nPlease enter your to-do item: ");
        String text = input.nextLine();
        ToDoItem item = new ToDoItem(text, false);
        items.add(item);
    }

    public static void toggleItem(Scanner input, ArrayList<ToDoItem> items)
    {
        System.out.printf("\nEnter the number of the item you wish to toggle: ");
        String numStr = input.nextLine();
        try
        {
            int num = Integer.valueOf(numStr);
            ToDoItem tempItem = items.get(num - 1);
            tempItem.setDone(!tempItem.isDone());
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

    public static void showItems(ArrayList<ToDoItem> items)
    {
        int i = 1;
        for( ToDoItem toDoItem : items)
        {
            String checkBox = "[ ]";
            if(toDoItem.isDone()) {
                checkBox = "[x]";
            }
            System.out.printf("\n%s%d. %s", checkBox, i, toDoItem.getText());
            i++;
        }
        System.out.printf("\n\n");
    }

    public static void main(String[] args)
    {
        ArrayList<ToDoItem> items = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        boolean run = true;
        while (run)
        {
            System.out.printf("\n1. Create Item\n2. Toggle Item\n3. List Items\n4. Quit");
            String option = input.nextLine();

            switch(option)
            {
                case "1":
                    createItem(input, items);
                    break;
                case "2":
                    toggleItem(input, items);
                    break;
                case "3":
                    showItems(items);
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
