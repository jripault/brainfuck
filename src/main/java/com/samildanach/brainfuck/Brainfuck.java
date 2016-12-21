package com.samildanach.brainfuck;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Brainfuck {
    private int[] registers = new int[8];
    private int head = 0;
    private List<Integer> result = new ArrayList<Integer>();

    public Brainfuck() {
    }

    public List<Integer> execute(String s) {
        char[] chars = s.toCharArray();
        int loopIndex = 0;
        int endloopIndex = 0;
        int iterationCounter = 0;


        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            switch (aChar) {
                case '+':
                    registers[head]++;
                    break;
                case '-':
                    registers[head]--;
                    break;
                case '.':
                    result.add(registers[head]);
                    break;
                case '>':
                    head++;
                    break;
                case '<':
                    head--;
                    break;
                case '[':
                    loopIndex = i;
                    if (registers[head] == 0) {
                        i = endloopIndex;
                        break;
                    }
                    break;
                case ']':
                    if(iterationCounter > 1000){
                        throw new RuntimeException("Too many iterations " + iterationCounter);
                    }
                    endloopIndex = i;
                    if (registers[head] != 0) {
                        i = loopIndex;
                        iterationCounter++;
                        break;
                    }
                    break;
            }
            //System.out.println("i: " + i + " - char: " + aChar + " - head: " + head + " - " + Arrays.toString(registers));
        }
        return result;
    }

    public int register(int index) {
        return registers[index];
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int value : result) {
            builder.append((char)value);
        }
        return builder.toString();
    }
}
