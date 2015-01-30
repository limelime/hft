package net.xngo.hft;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.PrintWriter;

public class Main
{
  
  /**
   * @param args
   */
  public static void main(String[] args)
  {
    int args_index = 0;
    final String filename         = args[args_index++];
    final double buy_price        = Double.parseDouble(args[args_index++]);
    final double percentage_gain  = Double.parseDouble(args[args_index++]);
    final double sell_price       = buy_price*((percentage_gain/100.0)+1.0);
    
    BufferedReader reader = null;
    
    try
    {
      reader = new BufferedReader(new FileReader(filename));
      String line = "";
      boolean header_line = true;
      boolean bought = false;
      String action_price = buy_price+"";
      int gain = 0;
      while ((line = reader.readLine()) != null)
      {
        if(header_line) // Don't do anything on the header line.
        {
          header_line = false;
          System.out.println(line+",in,out,action");
        }
        else
        {
          String[] tokens = line.split(",");
          if(tokens.length==7)
          {
            int token_idx=0;
            final String date       = tokens[token_idx++];
            final String open       = tokens[token_idx++];
            final double high       = Double.parseDouble(tokens[token_idx++]);
            final double low        = Double.parseDouble(tokens[token_idx++]);
            final String close      = tokens[token_idx++];
            final String volume     = tokens[token_idx++];
            final String adj_close  = tokens[token_idx++];
            
            String bought_price = "";
            String sold_price = "";

            if(!bought)
            {
              if(buy_price>=low && buy_price<=high)
              {
                bought_price = buy_price+"";
                action_price = bought_price;
                bought=true;
              }
            }
            else
            {
              if(sell_price>=low && sell_price<=high)
              {
                sold_price = sell_price+"";
                action_price = sold_price;
                bought=false;
                gain++;
              }
            }
        
            System.out.println(String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s", date, open, high, low, close, volume, adj_close, buy_price, sell_price, action_price));
          }
          
        }

      }
      System.out.println("Gain = "+gain);

      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("log.csv", true)));
      out.println(String.format("%s,%s,%s", filename, buy_price, gain));
      out.close();

    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    finally
    {
      try 
      {
        if (reader != null)
          reader.close();
      } 
      catch (IOException ex)
      {
        ex.printStackTrace();
      }
    }

  }

}
