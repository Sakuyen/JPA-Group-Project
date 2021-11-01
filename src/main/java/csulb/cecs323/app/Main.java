/*
 * Licensed under the Academic Free License (AFL 3.0).
 *     http://opensource.org/licenses/AFL-3.0
 *
 *  This code is distributed to CSULB students in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE, other than educational.
 *
 *  2018 Alvaro Monge <alvaro.monge@csulb.edu>
 *
 */

package csulb.cecs323.app;

// Import all of the entity classes that we have written for this application.
import csulb.cecs323.model.*;
import org.eclipse.persistence.oxm.record.WriterRecord;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * A simple application to demonstrate how to persist an object in JPA.
 * <p>
 * This is for demonstration and educational purposes only.
 * </p>
 * <p>
 *     Originally provided by Dr. Alvaro Monge of CSULB, and subsequently modified by Dave Brown.
 * </p>
 */
public class Main {
   /**
    * You will likely need the entityManager in a great many functions throughout your application.
    * Rather than make this a global variable, we will make it an instance variable within the Main
    * class, and create an instance of Main in the main.
    */
   private EntityManager entityManager;

   /**
    * The Logger can easily be configured to log to a file, rather than, or in addition to, the console.
    * We use it because it is easy to control how much or how little logging gets done without having to
    * go through the application and comment out/uncomment code and run the risk of introducing a bug.
    * Here also, we want to make sure that the one Logger instance is readily available throughout the
    * application, without resorting to creating a global variable.
    */
   private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

   /**
    * The constructor for the Main class.  All that it does is stash the provided EntityManager
    * for use later in the application.
    * @param manager    The EntityManager that we will use.
    */
   public Main(EntityManager manager) {
      this.entityManager = manager;
   }

   public static void main(String[] args) {
      LOGGER.fine("Creating EntityManagerFactory and EntityManager");
      EntityManagerFactory factory = Persistence.createEntityManagerFactory("Main");
      EntityManager manager = factory.createEntityManager();
      // Create an instance of Main and store our new EntityManager as an instance variable.
      Main main = new Main(manager);


      // Any changes to the database need to be done within a transaction.
      // See: https://en.wikibooks.org/wiki/Java_Persistence/Transactions

      LOGGER.fine("Begin");
      EntityTransaction tx = manager.getTransaction();
      tx.begin();
      List<Publishers> publisher = new ArrayList<>();
      publisher.add(new Publishers("Publishing Company", "(123)-456-1234", "company@getpublished.com"));
      publisher.add(new Publishers("Publishing Group", "(987)-654-3210", "group@getpublished.com"));
      main.createEntity (publisher);

      List<WritingGroups> group = new ArrayList<>();
      group.add(new WritingGroups("Authoring Company", "company@getauthored.com", "Hed Wright", 1999));
      group.add(new WritingGroups("Authoring Group", "group@getauthored.com", "Writ Heed", 2000));
      main.createEntity (group);

      List<Books> book = new ArrayList<>();
      book.add(new Books(publisher.get(0), group.get(1), 123456789, "A Title", 2012));
      book.add(new Books(publisher.get(1), group.get(0), 987654321, "Another Title", 2020));
      main.createEntity (book);

      // Commit the changes so that the new data persists and is visible to other users.
      tx.commit();
      LOGGER.fine("End");
      boolean done = false;
//      EntityManager em = getEntityManager();
//      Query query = em.createNamedQuery();
      List<Publishers> publishers = manager.createQuery
              ("SELECT a FROM Publishers a", Publishers.class).getResultList();
      List<Books> books = manager.createQuery
              ("SELECT a FROM Books a", Books.class).getResultList();
      List<WritingGroups> groups = manager.createQuery
              ("SELECT a FROM WritingGroups a", WritingGroups.class).getResultList();
      while(!done) {
         // menu
         System.out.println("1. Add a new Object.\n" +
                 "2. Display all information for a specific Object.\n" +
                 "3. Delete a Book\n" +
                 "4. Update a Book\n" +
                 "5. List the primary keys of all rows\n" +
                 "6. Finish");
         Scanner in = new Scanner(System.in);
         int choice = Integer.parseInt(in.next());
         switch(choice)
         {
            case 1:
               break;
            case 2:
               //menu 2
               System.out.println("1. Display all information for a specific Publisher.\n" +
                       "2. Display all information for a specific Book.\n" +
                       "3. Display all information for a specific Writing Group.\n" +
                       "4. Finish");
               Scanner in2 = new Scanner(System.in);
               int choice2 = Integer.parseInt(in2.next());
               Scanner in3 = new Scanner(System.in);
               switch(choice2){
                  case 1:
                     System.out.println("Select a publisher by entering their name:");
                     String name = in3.nextLine();
                     for(Publishers p: publishers) {
                        if(p.getName().equals(name)){
                           System.out.println(p);
                        }
                     }
                     break;
                  case 2:
                     System.out.println("Select a book by entering its isbn:");
                     int isbn = in3.nextInt();
                     for(Books b: books) {
                        if(b.getIsbn() == isbn){
                           System.out.println(b);
                        }
                     }
                     break;
                  case 3:
                     System.out.println("Select a Writing Group by entering their email:");
                     String email = in3.nextLine();
                     for(WritingGroups w: groups) {
                        if(w.getEmail().equals(email)){
                           System.out.println(w);
                        }
                     }
                     break;
                  case 4:
                     System.out.println("Exiting.");
                     break;
               }
               break;
            case 3:
               break;
            case 4:
               break;
            case 5: // Display PK of all rows of a specific table
               System.out.println("1. Display Publisher names of all Publishers.\n" +
                       "2. Display ISBN and title of all Books.\n" +
                       "3. Display email of all Authoring Entities.\n" +
                       "4. Finish");
               Scanner in5 = new Scanner(System.in);
               int choice5 = Integer.parseInt(in5.next());
               switch(choice5) {
                  case 1: // Display publisher names
                     for (Publishers p : publishers) {
                        System.out.println("Publisher Name: " + p.getName());
                     }
                     break;
                  case 2: // Display Book titles and ISBN
                     for (Books b : books) {
                        System.out.println("ISBN: " + b.getIsbn() + ", Title: " + b.getTitle());
                     }
                     break;
                  case 3: // Display Authoring Entity type and their email
                     for (AuthoringEntities a : groups) {
                        System.out.println(a.getAuthoringEntityType() + " Email: " + a.getEmail());
                     }
                     break;
                  case 4:
                     break;
               }
               break;
            case 6:
               System.out.println("Quitting.");
               done = true;
               break;
         }
//         System.out.println(publishers);
      }

   } // End of the main method

   public static String getUserString() {
      Scanner in = new Scanner(System.in);
      return in.nextLine();
   }

   /**
    * Create and persist a list of objects to the database.
    * @param entities   The list of entities to persist.  These can be any object that has been
    *                   properly annotated in JPA and marked as "persistable."  I specifically
    *                   used a Java generic so that I did not have to write this over and over.
    */
   public <E> void createEntity(List <E> entities) {
      for (E next : entities) {
         LOGGER.info("Persisting: " + next);
         // Use the Main entityManager instance variable to get our EntityManager.
         this.entityManager.persist(next);
      }

      // The auto generated ID (if present) is not passed in to the constructor since JPA will
      // generate a value.  So the previous for loop will not show a value for the ID.  But
      // now that the Entity has been persisted, JPA has generated the ID and filled that in.
      for (E next : entities) {
         LOGGER.info("Persisted object after flush (non-null id): " + next);
      }
   } // End of createEntity member method

   public void listInfo(Object o){

   }
} // End of Main class
