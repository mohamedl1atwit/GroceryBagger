/* @formatter:off
 *
 * Dave Rosenberg
 * Comp 2000 - Data Structures
 * Demonstration: Bag ADT
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


package edu.wit.scds.ds.bag.adt ;

import edu.wit.scds.ds.bag.BagInterface ;

import java.util.Arrays ;


/**
 * Resizable array-backed implementation of the unordered list Bag
 *
 * @author David M Rosenberg
 *
 * @version 1.0.0 2024-01-29 Initial implementation based upon the fixed-sized array backed
 *     implementation
 *
 * @param <T>
 *     the type of all elements in an instance of the Bag
 */
public final class ArrayBag<T> implements BagInterface<T>
    {

    /*
     * constants
     */
    /** the default capacity of a Bag if not explicitly specified */
    private final static int DEFAULT_CAPACITY = 10 ;

    /** the largest capacity a Bag can have */
    private final static int MAX_CAPACITY = 10_000 ;

    /*
     * data fields
     */
    private T[] bag ;
    private int numberOfEntries ;

    private boolean integrityOK = false ;


    /*
     * constructors
     */


    /**
     * initialize the state of a Bag with default capacity
     */
    public ArrayBag()
        {
        this( DEFAULT_CAPACITY ) ;

        }   // end no-arg constructor


    /**
     * initialize the state of a Bag with application-specified capacity
     *
     * @param initialCapacity
     *     the initial number of data items the Bag will be able to hold
     */
    public ArrayBag( final int initialCapacity )
        {
        // initially state is not valid
        this.integrityOK = false ;

        // validate the initial capacity
        checkCapacity( initialCapacity ) ;

        // the cast is safe because the new array is null filled
        @SuppressWarnings( "unchecked" )
        final T[] tempBag = (T[]) new Object[ initialCapacity ] ;
        this.bag = tempBag ;

        this.numberOfEntries = 0 ;

        // state is now fully valid
        this.integrityOK = true ;

        }   // end 1-arg constructor


    /*
     * API methods
     */


    @Override
    public boolean add( final T newEntry )
        {
        // make sure the array is intact and usable
        checkIntegrity() ;

        // prevent null entries
        if ( newEntry == null )
            {
            return false ;  // reject null
            }

        // make sure there's room to add the new entry
        ensureCapacity() ;

        // there is room
        this.bag[ this.numberOfEntries ] = newEntry ;
        this.numberOfEntries++ ;

        return true ;   // success

        }   // end add()


    @Override
    public void clear()
        {
        // make sure the array is intact and usable
        checkIntegrity() ;

        while ( !isEmpty() )
            {
            remove() ;
            }

        }   // end clear()


    @Override
    public boolean contains( final T anEntry )
        {
        // make sure the array is intact and usable
        checkIntegrity() ;

        return getIndexOf( anEntry ) >= 0 ;

        }   // end contains()


    @Override
    public int getCurrentSize()
        {
        return this.numberOfEntries ;

        }   // end getCurrentSize()


    @Override
    public int getFrequencyOf( final T anEntry )
        {
        // make sure the array is intact and usable
        checkIntegrity() ;

        // bag can't contain null so don't bother looking for one
        if ( anEntry == null )
            {
            return 0 ;
            }

        int timesSeen = 0 ;     // haven't seen any yet

        // check each element in the array for a match
        for ( int i = 0 ; i < this.numberOfEntries ; i++ )
            {

            if ( this.bag[ i ].equals( anEntry ) )
                {
                timesSeen++ ;
                }

            }	// end for

        return timesSeen ;

        }   // end getFrequencyOf()


    @Override
    public boolean isEmpty()
        {
        return this.numberOfEntries == 0 ;

        }   // end isEmpty()


    @Override
    public T remove()
        {
        // make sure the array is intact and usable
        checkIntegrity() ;

        return removeEntry( this.numberOfEntries - 1 ) ;

        }   // end no-arg/unspecified remove()


    @Override
    public boolean remove( final T anEntry )
        {
        // make sure the array is intact and usable
        checkIntegrity() ;

        // find the first matching entry
        final int index = getIndexOf( anEntry ) ;

        // if found, replace it with the last entry then remove the last entry
        return removeEntry( index ) != null ;   // indicate success/failure

        }   // end 1-arg/specified remove()


    @Override
    public T[] toArray()
        {
        // make sure the array is intact and usable
        checkIntegrity() ;

        return Arrays.copyOf( this.bag, this.numberOfEntries ) ;

        }   // end toArray()


    /*
     * private utility methods
     */


    /**
     * ensure the specified desired capacity is acceptable
     *
     * @param desiredCapacity
     *     the capacity to check
     *
     * @throws IllegalStateException
     *     occurs when the desired capacity is not acceptable
     */
    private static void checkCapacity( final int desiredCapacity ) throws IllegalStateException
        {

        if ( desiredCapacity <= 0 )
            {
            throw new IllegalStateException( String.format( "attempt to create a Bag with too small a capacity: %,d",
                                                            desiredCapacity ) ) ;
            }

        if ( desiredCapacity > MAX_CAPACITY )
            {
            throw new IllegalStateException( String.format( "attempt to create a Bag with too large a capacity: %,d",
                                                            desiredCapacity ) ) ;
            }

        }   // end checkCapacity()


    /**
     * prevent continued execution unless the bag's state is valid
     *
     * @throws SecurityException
     *     indicates the state is invalid
     */
    private void checkIntegrity() throws SecurityException
        {

        if ( !this.integrityOK )
            {
            throw new SecurityException( "invalid Bag state" ) ;
            }

        }   // end checkIntegrity()


    /**
     * make sure there's at least one available element in the bag array
     *
     * @throws IllegalStateException
     *     attempted to grow the array beyond the maximum allowed
     */
    private void ensureCapacity() throws IllegalStateException
        {

        if ( isArrayFull() )
            {
            // allocate an array sized to twice the current size

            final int newCapacity = this.bag.length * 2 ;

            // make sure the larger size is acceptable
            checkCapacity( newCapacity ) ;

            // enable detection of invalid state if the array can't be updated
            this.integrityOK = false ;

            // allocate a larger array and copy the entries from the old array to the new array in
            // corresponding locations
            this.bag = Arrays.copyOf( this.bag, newCapacity ) ;

            // operation was successful and state is valid
            this.integrityOK = true ;
            }

        }   // end ensureCapacity()


    /**
     * locate the first entry that matches the argument
     *
     * @param anEntry
     *     the entry to find
     *
     * @return the index of the first occurrence of {@code anEntry} if found, or -1 if not found
     */
    private int getIndexOf( final T anEntry )
        {

        if ( anEntry == null )
            {
            return -1 ;     // we don't store null
            }

        for ( int i = 0 ; i < this.numberOfEntries ; i++ )
            {

            if ( this.bag[ i ].equals( anEntry ) )
                {
                return i ;  // found it
                }

            }

        return -1 ;  // didn't find it

        }   // end getIndexOf()


    /**
     * test the Bag's capacity for (lack of) room to add another entry
     *
     * @return {@code true} if all elements of the array are in use, {@code false} if there's at
     *     least one unused element
     */
    private boolean isArrayFull()
        {
        return this.bag.length == this.numberOfEntries ;

        }   // end isArrayFull()


    /**
     * remove and return the entry at the specified index
     *
     * @param givenIndex
     *     the index of the entry to remove/return
     *
     * @return the removed entry or {@code null} if {@code givenIndex} is negative
     */
    private T removeEntry( final int givenIndex )
        {
        T result = null ;   // haven't removed anything yet

        if ( !isEmpty() && ( givenIndex >= 0 ) )
            {
            result = this.bag[ givenIndex ] ;

            final int lastIndex = this.numberOfEntries - 1 ;

            this.bag[ givenIndex ] = this.bag[ lastIndex ] ;
            this.bag[ lastIndex ] = null ;

            this.numberOfEntries-- ;
            }

        return result ;

        }   // end removeEntry()

    }   // end class ArrayBag