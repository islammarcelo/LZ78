package com.example.lz_78.Class;

import android.widget.Button;

import com.example.lz_78.Class.Pair;

import java.util.ArrayList;

public class LZfun_78 {
    public ArrayList<Pair> pair = new ArrayList<Pair>();
    public ArrayList<String> dict = new ArrayList<String>();

    public String result = "";

    public LZfun_78() {
        dict.add("-----");
    }

    public int search(String str) {

        for (int i = 1; i < dict.size(); i++) {
            if (dict.get(i).equals(str)) {
                return i;
            }
        }

        return -1;
    }

    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

    public void compress(String input) {
        String[] split = new String[input.length()];
        for (int i = 0; i < input.length(); i++) {
            split[i] = input.charAt(i) + "";
        }

        String tmp;
        int pos;

        for (int i = 0; i < split.length; i++) {
            tmp = split[i];
            pos = search(tmp);


            if (pos == -1) {
                pair.add(new Pair(0, tmp.charAt(0)));
                dict.add(tmp);
            } else {
                int tmpPos = pos;
                for (int j = i + 1; j < split.length; j++) {
                    tmp += split[j];
                    pos = search(tmp);

                    if (pos == -1) {
                        pair.add(new Pair(tmpPos, tmp.charAt(tmp.length() - 1)));
                        dict.add(tmp);
                    }

                    tmpPos = pos;
                    if (tmpPos == -1)
                        break;
                }

                if (tmpPos != -1) {
                    pair.add(new Pair(tmpPos, '*'));
                }

                i += tmp.length() - 1;
            }
        }
    }


            //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@




    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

    public void decompress() {
        // clears dictionary content
        dict.subList(1, dict.size()).clear();



        for (int i = 0; i < pair.size(); i++) {
            int key = pair.get(i).getKey();
            String value = Character.toString(pair.get(i).getValue());

            if (key == 0) {
                dict.add(value);
                result += value;
            } else {
                if (value.equals("*")) {
                    result += dict.get(key);
                } else {
                    if(i+1 < pair.size()) {
                        String tmp = dict.get(key) + value;
                        result += tmp;

                        dict.add(tmp);
                    }
                    else if (i+1 >= pair.size()) {
                        String tmp = dict.get(key) + value;
                        result += tmp;
                    }
                }
            }
        }

    }
}
