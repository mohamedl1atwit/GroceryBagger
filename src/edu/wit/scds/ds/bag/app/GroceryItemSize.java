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
 * Enumeration of grocery item sizes for the GroceryBagger application.
 *
 * @author David M Rosenberg
 *
 * @version 1.0.0 initial version for GroceryBagger application
 * @version 1.1.0 2023-02-13 updates for spring 2023 assignment
 */
public enum GroceryItemSize
    {

    // Size - Display Name - Numeric Value
    /** Small item */
    SMALL ( "Small", 1 ),
    
    /** Medium item */
    MEDIUM ( "Medium", 3 ),
    
    /** Large item */
    LARGE ( "Large", 5 ),
    
    /** Extra Large item */
    EXTRA_LARGE ( "Extra Large", 7 ) ;


    /*
     * data fields
     */

    /** nicely formatted name for display */
    public final String displayName ;

    /** integer equivalent of the size */
    public final int sizeValue ;


    /**
     * @param itemSizeDisplayName
     *     nicely formatted name for display
     * @param itemSizeValue
     *     integer equivalent of the size
     */
    private GroceryItemSize( final String itemSizeDisplayName, final int itemSizeValue )
        {
        this.displayName = itemSizeDisplayName ;
        this.sizeValue = itemSizeValue ;

        } // end constructor


    /**
     * Parse a text description of size
     * <p>
     * WARNING: This is a very rudimentary implementation and may produce unexpected results.
     *
     * @param sizeDescription
     *     a name to parse
     *
     * @return the corresponding enum constant or MEDIUM if the name is unrecognized
     */
    public static GroceryItemSize interpretDescription( final String sizeDescription )
        {
        // This is a very rudimentary implementation

        return switch ( sizeDescription.toLowerCase().charAt( 0 ) )
                {
                case 's' -> SMALL ;
                case 'm' -> MEDIUM ;
                case 'l' -> LARGE ;
                case 'e', 'x' -> EXTRA_LARGE ;
                default -> MEDIUM ;
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
                           GroceryItemSize.class.getSimpleName() ) ;

        // display column headers
        System.out.printf( "%-5s %-15s %-15s %-15s %-15s %-15s%n",
                           "#",
                           "Item Size",
                           "Name",
                           "Display Name",
                           "Size Value",
                           "Interpreted Size" ) ;

        // display each element of the enumeration
        for ( final GroceryItemSize anItemSize : GroceryItemSize.values() )
            {
            System.out.printf( "%-5d %-15s %-15s %-15s %-15d %-15s%n",
                               anItemSize.ordinal(),
                               anItemSize,
                               anItemSize.name(),
                               anItemSize.displayName,
                               anItemSize.sizeValue,
                               interpretDescription( anItemSize.toString() ) ) ;
            }   // end for()

        }   // end main()

    }   // end enum GroceryItemSize