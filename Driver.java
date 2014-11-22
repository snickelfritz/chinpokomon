   import javax.swing.JFrame;
   public class Driver
   {
      public static void main(String[] args)
      {
         JFrame frame = new JFrame("Pokemon");
         frame.setSize(900, 600);
         frame.setLocation(0, 0);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setContentPane(new Panel());
         frame.setVisible(true);
      }
   }
