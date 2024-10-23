package iticbcn.xifratge;

import java.util.Arrays;

public class TextXifrat {
    @Override
    public String toString() {
        return "TextXifrat [bytes=" + Arrays.toString(bytes) + "]";
    }

    byte[] bytes;
    public byte[] getBytes(){
        return bytes;
    }
    
    public TextXifrat(byte[] bytes){
        this.bytes = bytes;
    }
}
