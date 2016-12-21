package com.samildanach.brainfuck;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Julien on 21/12/2016.
 */

public class BrainfuckTest {
    @Test
    public void shouldReturnEmptyIfInputIsEmpty(){
        Brainfuck brainfuck = new Brainfuck();
        List<Integer> result = brainfuck.execute("");
        assertThat(result).isEmpty();
    }
    @Test
    public void shouldDisplayACharacter(){
        Brainfuck brainfuck = new Brainfuck();
        List<Integer> result = brainfuck.execute("+.");
        assertThat(result).hasSize(1).contains(1);
    }
    @Test
    public void shouldDisplayTwoCharacters(){
        Brainfuck brainfuck = new Brainfuck();
        List<Integer> result = brainfuck.execute("+++++++++++++++++++++++++++++++++.+++++++++.>>");
        assertThat(result).hasSize(2).contains(33, 42);
        assertThat(brainfuck.register(0)).isEqualTo(42);
        assertThat(brainfuck.register(1)).isEqualTo(0);
        assertThat(brainfuck.register(2)).isEqualTo(0);
        assertThat(brainfuck.toString()).isEqualTo("!*");
    }
    @Test
    public void shouldAddRegisters(){
        Brainfuck brainfuck = new Brainfuck();
        List<Integer> result = brainfuck.execute("+++++++>+++<[->+<]>.");
        assertThat(result).hasSize(1).contains(10);
        assertThat(brainfuck.register(0)).isEqualTo(0);
        assertThat(brainfuck.register(1)).isEqualTo(10);
    }
    @Test
    public void shouldThrowTooManyIterations(){
        Brainfuck brainfuck = new Brainfuck();
        try {
            List<Integer> result = brainfuck.execute("+[]");
        } catch (RuntimeException e) {
            assertThat(e).hasMessage("Too many iterations 1001");
        }
    }
    @Test
    public void shouldBeHelloWorld(){
        Brainfuck brainfuck = new Brainfuck();
        List<Integer> result = brainfuck.execute("++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++.>+.+++++++..+++.>++.<<+++++++++++++++.>.+++.------.--------.>+.>");
        assertThat(result).hasSize(12);
        assertThat(brainfuck.toString()).isEqualTo("Hello World!" +
                "");
    }

}
