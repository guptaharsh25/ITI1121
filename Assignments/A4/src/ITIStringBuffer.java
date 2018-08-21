

import java.util.Iterator;

public class ITIStringBuffer {

    private SinglyLinkedList<String> strings;
    private String cache;
    private boolean newAppend;
    private int len;

    public ITIStringBuffer() {
        this.strings = new SinglyLinkedList<>();
        this.cache = "";
        this.newAppend = false;
        this.len = 0;
    }

    public ITIStringBuffer(String  firstString){
        this.strings = new SinglyLinkedList<>();
        this.cache = "";
        this.newAppend = false;
        this.len = 0;
        this.append(firstString);
    }

    public void append(String nextString){
        this.strings.add(nextString);
        this.len += nextString.length();
        this.newAppend = true;
    }

    public String toString(){
        if(this.newAppend) {
            this.newAppend = false;
            Iterator<String> iterator = this.strings.iterator();
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

    private char[] arrayConcatenate(char[] first, char[] second){
        char[] answer = new char[first.length + second.length];
        for(int i = 0; i < first.length; i++){
            answer[i] = first[i];
        }
        for(int i = 0; i < second.length; i++){
            answer[i+first.length] = second[i];
        }

        return answer;
    }
}
