/*
 *
 * Lets the user add strings and concatenates it into one string efficiently
 * (similar to the built-in java StringBuilder)
 *
 * @author Harsh Gupta, 300042828 (hgupt033@uottawa.ca)
 *
 */

import java.util.Iterator;

public class ITIStringBuffer {

    private SinglyLinkedList<String> strings;
    private String cache;
    private boolean newAppend;
    private int len;

    /*
     * Initializes the StringBuffer, starts with an empty string and length 0.
     *
     */
    public ITIStringBuffer() {
        this.strings = new SinglyLinkedList<>();
        this.cache = "";
        this.newAppend = false;
        this.len = 0;
    }

    /*
     * Initializes the StringBuffer, starts with the given firstString and length 0.
     *
     * @param firstString
     *      the first string we want to start the StringBuffer with.
     */
    public ITIStringBuffer(String  firstString){
        this.strings = new SinglyLinkedList<>();
        this.cache = "";
        this.newAppend = false;
        this.len = 0;
        this.append(firstString);
    }

    /*
     * Adds the given nextString to the StringBuffer.
     *
     * @param nextString
     *      the next string we want to add to the StringBuffer.
     */
    public void append(String nextString){
        this.strings.add(nextString);
        //this.len += nextString.length();
        this.newAppend = true;
    }

    /*
     * Concatenates all the strings and represents it as one string.
     *
     * @return
     *      a concatenated String of all the strings in this StringBuffer.
     */
    public String toString(){
        if(this.newAppend) {
            this.newAppend = false;
            Iterator<String> iterator = this.strings.iterator();
            this.len = 0;
            while(iterator.hasNext()){
                this.len += iterator.next().length();
            }
            iterator = this.strings.iterator();
            char[] fullChars = new char[this.len];
            int firstNull = 0;
            while(iterator.hasNext()){
                char[] temp = iterator.next().toCharArray();
                for(int i = 0; i < temp.length; i++){
                    fullChars[i+firstNull] = temp[i];
                }
                firstNull += temp.length;
            }
            this.cache = new String(fullChars);
        }
        return this.cache;
    }
}
