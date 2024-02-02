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


package edu.wit.scds.ds.bag.app ;

/**
 * Enumeration of grocery item firmness for the GroceryBagger application.
 *
 * @author David M Rosenberg
 *
 * @version 1.0.0 initial version for GroceryBagger application
 * @version 1.1.0 2023-02-13 updates for spring 2023 assignment
 */
public enum GroceryItemFirmness
    {

    // Firmness - Display Name
    /** Soft item */
    SOFT ( "Soft" ),
    
    /** Firm item */
    FIRM ( "Firm" ),
    
    /** Hard item */
    HARD ( "Hard" ) ;


    /*
     * data fields
     */

    /** nicely formatted name for display */
    public final String displayName ;


    /**
     * @param itemFirmnessDisplayName
     *     nicely formatted name for display
     */
    private GroceryItemFirmness( final String itemFirmnessDisplayName )
        {
        this.displayName = itemFirmnessDisplayName ;

        } // end constructor


    /**
     * Parse a text description of firmness
     * <p>
     * WARNING: This is a very rudimentary implementation and may produce unexpected results.
     *
     * @param firmnessDescription
     *     a name to parse
     *
     * @return the corresponding enum constant or FIRM if the name is unrecognized
     */
    public static GroceryItemFirmness interpretDescription( final String firmnessDescription )
        {
        // This is a very rudimentary implementation

        return switch ( firmnessDescription.toLowerCase().charAt( 0 ) )
                {
                case 's' -> SOFT ;
                case 'f' -> FIRM ;
                case 'h' -> HARD ;
                default -> FIRM ;
                } ;

        }   // end interpretDescription()


    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
        {
        return this.displayName ;

        }   // end toString()


    /**
     * Test driver - displays all constants for this enumeration
     *
     * @param args
     *     -unused-
     */
    public static void main( final String[] args )
        {
        // display introductory message
        System.out.printf( "Members of the %s enumeration%n%n",
                           GroceryItemFirmness.class.getSimpleName() ) ;

        // display column headers
        System.out.printf( "%-5s %-15s %-15s %-15s %-15s%n",
                           "#",
                           "Item Firmness",
                           "Name",
                           "Display Name",
                           "Interpreted Firmness" ) ;

        // display each element of the enumeration
        for ( final GroceryItemFirmness anItemFirmness : GroceryItemFirmness.values() )
            {
            System.out.printf( "%-5d %-15s %-15s %-15s %-15s%n",
                               anItemFirmness.ordinal(),
                               anItemFirmness,
                               anItemFirmness.name(),
                               anItemFirmness.displayName,
                               interpretDescription( anItemFirmness.toString() ) ) ;
            }   // end for()

        }   // end main()

    }   // end enum GroceryItemFirmness