package de.enwaffel.randomutils.encode;

import java.util.HashMap;
import java.util.Map;

public class EncoderMap {

    private HashMap<Character,String> chars = new HashMap<>();

    public EncoderMap() {
        mapDefault();
    }

    public EncoderMap(HashMap<Character,String> chars) {
        this.chars = chars;
    }

    private void mapDefault() {
        chars.put(' ',"9");
        chars.put('A',"38");
        chars.put('B',"77");
        chars.put('C',"67");
        chars.put('D',"76");
        chars.put('E',"56");
        chars.put('F',"19");
        chars.put('G',"35");
        chars.put('H',"57");
        chars.put('I',"17");
        chars.put('J',"4");
        chars.put('K',"43");
        chars.put('L',"53");
        chars.put('M',"45");
        chars.put('N',"8");
        chars.put('O',"37");
        chars.put('P',"64");
        chars.put('Q',"33");
        chars.put('R',"78");
        chars.put('S',"54");
        chars.put('T',"60");
        chars.put('U',"51");
        chars.put('V',"22");
        chars.put('W',"28");
        chars.put('X',"30");
        chars.put('Y',"71");
        chars.put('Z',"2");
        chars.put('a',"46");
        chars.put('b',"84");
        chars.put('c',"24");
        chars.put('d',"34");
        chars.put('e',"65");
        chars.put('f',"36");
        chars.put('g',"50");
        chars.put('h',"70");
        chars.put('i',"58");
        chars.put('j',"39");
        chars.put('k',"44");
        chars.put('l',"61");
        chars.put('m',"47");
        chars.put('n',"82");
        chars.put('o',"20");
        chars.put('p',"66");
        chars.put('q',"73");
        chars.put('r',"72");
        chars.put('s',"21");
        chars.put('t',"26");
        chars.put('u',"27");
        chars.put('v',"31");
        chars.put('w',"10");
        chars.put('x',"3");
        chars.put('y',"6");
        chars.put('z',"7");
        chars.put('{',"12");
        chars.put('}',"63");
        chars.put('[',"23");
        chars.put(']',"68");
        chars.put('\"',"00");
        chars.put('+',"14");
        chars.put('-',"25");
        chars.put('*',"42");
        chars.put('/',"32");
        chars.put('\\',"80");
        chars.put('0',"83");
        chars.put('1',"49");
        chars.put('2',"79");
        chars.put('3',"13");
        chars.put('4',"62");
        chars.put('5',"5");
        chars.put('6',"29");
        chars.put('7',"16");
        chars.put('8',"52");
        chars.put('9',"81");
        chars.put(':',"15");
        chars.put(';',"40");
        chars.put(',',"48");
        chars.put('.',"69");
        chars.put('_',"1");
        chars.put('!',"11");
        chars.put('?',"41");
        chars.put('=',"55");
        chars.put('(',"74");
        chars.put(')',"59");
        chars.put('\n',"75");
    }

    public String getReplacementCharacter(char c) {
        return chars.getOrDefault(c, "�");
    }

    public Character getOriginalCharacter(String c) {
        for (Map.Entry<Character,String> set : chars.entrySet()) {
            if (set.getValue().equals(c)) {
                return set.getKey();
            }
        }
        return null;
    }

}
