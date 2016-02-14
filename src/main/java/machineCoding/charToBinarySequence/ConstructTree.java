package machineCoding.charToBinarySequence;

import java.util.HashMap;
import java.util.Stack;

/**
 * Created by rajesh.kalloor on 14/02/16.
 */
public class ConstructTree {

    //The problem statement:
    //Given 5 characters a, b ,c ,d ,e with weightages 0.4, 0.2, 0.1, 0.1, 0.1 respectively
    //Sort the characters in descending order of weightages
    // If the weightages are same for an characters they should be in ascending order
    // then assign 0 for every left edge and 1 for every right edge.
    // deduce the binary sequemce for each character

//                1
//               /\
//              /  \ 1
//             /    \ ____0.6
//            /     /\
//           /     /  \ 1
//          /     /    \ ____0.4
//         /     /     /\
//        /     /     /  \ 1
//       /     /     /    \ ____0.2
//      /     /     /     /\
//    0/    0/    0/    0/  \ 1
//    /     /     /     /    \
//    a     b     e     c     d
//    0.4   0.2   0.2   0.1   0.1


    public String getBinarySequence(String input) {

        char[] chars = {'a', 'b', 'c', 'd', 'e'};
        //String input = "ababbecdae";
        if (input == "") {
            System.out.println("Empty string entered \n");
            return "Enter a valid string \n";
        }
        HashMap<Character, Double> map = new HashMap<Character, Double>();
        map.put('a', 0.4);
        map.put('b', 0.2);
        map.put('c', 0.1);
        map.put('d', 0.1);
        map.put('e', 0.2);

        char[] inputchars = input.toCharArray();
        char[] sortedchars = modifySort(chars, map);

        Stack<Character> stk = new Stack<Character>();

        System.out.print("the sorted char array in reverse order ::: ");
        for (int i=sortedchars.length-1; i>=0; i--) {
            System.out.print(sortedchars[i]);
            stk.push(chars[i]);
        }
        System.out.print("\n");


        HashMap<Character, String> binMap = new HashMap<Character, String>();
        int flag = 0;
        while (!stk.empty()) {

            char ch1 = stk.pop();
            flag++;
            if (flag>1 && !stk.empty()) {
                String preAppend = "";
                for (int k=1; k<flag; k++) {
                    preAppend = preAppend+"1";

                }
                preAppend = preAppend + "0";
                binMap.put(ch1, preAppend);
            }
            else if (flag>1 && stk.empty()){
                String preAppend = "";
                for (int k=1; k<flag; k++) {
                    preAppend = preAppend+"1";

                }
                binMap.put(ch1, preAppend);
            }
            else if (flag == 1) {

                String preAppend = "0";
                binMap.put(ch1, preAppend);

            }
        }

        System.out.println("char to binary sequence mapping ::: " + binMap);
        String finOP = "";
        for (char ch2 : inputchars) {

            if (map.containsKey(ch2)) {
                finOP = finOP + binMap.get(ch2);
            }
            else {
                System.out.println("entered input should contain characters a,b,c,de only \n");
                return "Enter valid string with mentioned characters";
            }

        }
        System.out.println("encoded binary number of the sequence is ::: " + finOP);
        return finOP;

    }

    public char[] modifySort(char[] chars, HashMap<Character, Double> map) {

        for (int i=0; i<chars.length-1; i++) {
            for (int j=i+1; j<chars.length; j++) {
                if (map.get(chars[i]) < map.get(chars[j])) {
                    char temp = '\0';
                    temp = chars[i];
                    chars[i] = chars[j];
                    chars[j] = temp;
                }
                if (map.get(chars[i]).equals(map.get(chars[j]))) {
                    if (chars[i] > chars[j]) {
                        char temp = '\0';
                        temp = chars[i];
                        chars[i] = chars[j];
                        chars[j] = temp;
                    }

                }
            }
        }

        return chars;
    }

}
