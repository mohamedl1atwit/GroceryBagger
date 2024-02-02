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
 * Enumeration of grocery item weights for the GroceryBagger application.
 *
 * @author David M Rosenberg
 *
 * @version 1.0.0 initial version for GroceryBagger application
 * @version 1.1.0 2023-02-13 updates for spring 2023 assignment
 */
public enum GroceryItemWeight
    {

    // Weight - Display Name - Numeric Value
    /** Light item */
    LIGHT ( "Light", 1 ),
    
    /** Medium item */
    MEDIUM ( "Medium", 3 ),
    
    /** Heavy item */
    HEAVY ( "Heavy", 5 ),
    
    /** Very Heavy item */
    VERY_HEAVY ( "Very Heavy", 7 ) ;


    /*
     * data fields
     */

    /** nicely formatted name for display */
    public final String displayName ;

    /** integer equivalent of the weight */
    public final int weightValue ;


    /**
     * @param itemWeightDisplayName
     *     nicely formatted name for display
     * @param itemWeightValue
     *     integer equivalent of the weight
     */
    private GroceryItemWeight( final String itemWeightDisplayName, final int itemWeightValue )
        {
        this.displayName = itemWeightDisplayName ;
        this.weightValue = itemWeightValue ;

        } // end constructor


    /**
     * Parse a text description of weight
     * <p>
     * WARNING: This is a very rudimentary implementation and may produce unexpected results.
     *
     * @param weightDescription
     *     a name to parse
     *
     * @return the corresponding enum constant or MEDIUM if the name is unrecognized
     */
    public static GroceryItemWeight interpretDescription( final String weightDescription )
        {
        // This is a very rudimentary implementation

        return switch ( weightDescription.toLowerCase().charAt( 0 ) )
                {
                case 'l' -> LIGHT ;
                case 'm' -> MEDIUM ;
                case 'h' -> HEAVY ;
                case 'v' -> VERY_HEAVY ;
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
                           GroceryItemWeight.class.getSimpleName() ) ;

        // display column headers
        System.out.printf( "%-5s %-15s %-15s %-15s %-15s %-15s%n",
                           "#",
                           "Item Weight",
                           "Name",
                           "Display Name",
                           "Weight Value",
                           "Interpreted Weight" ) ;

        // display each element of the enumeration
        for ( final GroceryItemWeight anItemWeight : GroceryItemWeight.values() )
            {
            System.out.printf( "%-5d %-15s %-15s %-15s %-15d %-15s%n",
                               anItemWeight.ordinal(),
                               anItemWeight,
                               anItemWeight.name(),
                               anItemWeight.displayName,
                               anItemWeight.weightValue,
                               interpretDescription( anItemWeight.toString() ) ) ;
            }   // end for()

        }   // end main()

    }   // end enum GroceryItemWeight