package com.theironyard.javawithclojure.jhporter;

import java.util.ArrayList;
import java.util.Scanner;

public class Main
{

    public static void main(String[] args)
    {
        ArrayList<ToDoItem> items = new ArrayList<>();
        Scanner input = new Scanner(System.in);

        while (true)
        {
            System.out.printf("\n1. Create Item.\n2. Toggle Item\n3. List Items\n");
            String option = input.nextLine();

            switch(option)
            {
                case "1":
                    System.out.printf("\nPlease enter your to-do item: ");
                    String text = input.nextLine();
                    ToDoItem item = new ToDoItem(text, false);
                    items.add(item);
                    break;
                case "2":
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
                    catch (ArrayIndexOutOfBoundsException e)
                    {
                        System.err.printf("\nItem Does Not Exist!!!!");
                    }

                    break;
                case "3":
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
                    break;
                default:
                    System.err.printf("\nInvalid Option!");
                    break;
            }
        }
    }
}
