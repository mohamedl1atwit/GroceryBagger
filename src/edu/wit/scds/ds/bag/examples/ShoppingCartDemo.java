/* @formatter:off
 *
 * Dave Rosenberg
 * Comp 2000 - Data Structures
 * Lab: Bag App - Grocery Bagger
 * Spring, 2024
 * 
 * Usage restrictions:
 * 
 * You may use this code for exploration, experimentation, and furthering your
 * learning for this course. You may not use this code for any other
 * assignments, in my course or elsewhere, without explicit permission, in
 * advance, from myself (and the instructor of any other course).
 * 
 * Further, you may not post (including in a public repository such as on github)
 * nor otherwise share this code with anyone other than current students in my 
 * sections of this course. Violation of these usage restrictions will be considered 
 * a violation of the Wentworth Institute of Technology Academic Honesty Policy.
 *
 * Do not remove this notice.
 *
 * @formatter:on
 */


package edu.wit.scds.ds.bag.examples ;

import edu.wit.scds.ds.bag.app.GroceryItemFirmness ;
import edu.wit.scds.ds.bag.app.GroceryItemSize ;
import edu.wit.scds.ds.bag.app.GroceryItemWeight ;

import java.io.File ;
import java.io.FileNotFoundException ;
import java.util.Scanner ;

/**
 * Demo to show how to process the contents of shopping-cart.data
 *
 * @author Dave Rosenberg
 *
 * @version 1.0.0 2023-02-13 Initial implementation
 */
public class ShoppingCartDemo
    {

    /**
     * Demo to show how to process the contents of {@code shopping-cart.data}
     *
     * @param args
     *     -unused-
     */
    public static void main( final String[] args )
        {

        // we'll use a Scanner to read the file line-by-line
        try ( Scanner shoppingCart = new Scanner( new File( "./data/shopping-cart.data" ) ) ; )
            {
            // the first line is column/field headers - skip it
            shoppingCart.nextLine() ;

            // read each item, 'parse' it, display it
            while ( shoppingCart.hasNextLine() )
                {
                final String itemSpecificationLine = shoppingCart.nextLine() ;

                groceryItemConstructorEmulator( itemSpecificationLine ) ;
                }

            }
        catch ( final FileNotFoundException e )
            {
            // can't open the file
            System.out.printf( "Unable to open the shopping cart:%n%s%n", e ) ;

            // get out
            return ;
            }

        }	// end main()


    /**
     * emulate the constructor of the GroceryItem class
     * <ul>
     * <li>parse the full item specification line into its component fields
     * <li>convert each field into a 'usable' form (we're displaying rather than storing the
     * components)
     * </ul>
     *
     * @param fullItemSpecification
     *     the full item specification from the shopping cart
     */
    private static void groceryItemConstructorEmulator( final String fullItemSpecification )
        {
        // split the line into its component fields - they're tab-delimited
        final String[] itemFields = fullItemSpecification.split( "\t" ) ;

        // display it's contents
        System.out.printf( "%s%n\t%s: %d\t%s: %d\t%s: %s\t%s: %b \t%s: %b \t%s: %b%n%n",
                           itemFields[ GroceryItemFieldIds.ITEM.ordinal() ],
                           itemFields[ GroceryItemFieldIds.SIZE.ordinal() ],
                           GroceryItemSize.interpretDescription( itemFields[ GroceryItemFieldIds.SIZE.ordinal() ] ).sizeValue,
                           itemFields[ GroceryItemFieldIds.WEIGHT.ordinal() ],
                           GroceryItemWeight.interpretDescription( itemFields[ GroceryItemFieldIds.WEIGHT.ordinal() ] ).weightValue,
                           itemFields[ GroceryItemFieldIds.FIRMNESS.ordinal() ],
                           GroceryItemFirmness.interpretDescription( itemFields[ GroceryItemFieldIds.FIRMNESS.ordinal() ] ).displayName,
                           "Rigid",
                           itemFields[ GroceryItemFieldIds.RIGIDITY.ordinal() ].equals( "Rigid" ),
                           "Breakable",
                           Boolean.valueOf( itemFields[ GroceryItemFieldIds.BREAKABLE.ordinal() ] ),
                           "Perishable",
                           Boolean.valueOf( itemFields[ GroceryItemFieldIds.PERISHABLE.ordinal() ] ) ) ;

        }   // end groceryItemConstructorEmulator()


    /**
     * example of using a basic enum - the ordinal() for each instance is its position (0-based) in
     * the enum
     * <p>
     * if you wish to use this construct in your solution, move it out of here into a separate file
     * (GroceryItemFieldsId.java) - don't forget to include the appropriate comments!
     */
    private enum GroceryItemFieldIds
        {
        // each enumeration's ordinal is the index into the array from split()ting the item line
        ITEM,
        SIZE,
        WEIGHT,
        FIRMNESS,
        RIGIDITY,
        BREAKABLE,
        PERISHABLE;

        }   // end enum GroceryItemFields

    }   // end class ShoppingCartDemo