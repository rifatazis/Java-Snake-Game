package com.zetcode;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//  Fokus pada fungsi checkApple() dan locateApple().
 
public class BoardTest {

    private Board board;

    @BeforeEach
    public void setUp() {
        board = new Board();
    }

    @Test
    public void testLocateAppleWithinBounds() {
        // Komentar: Memastikan posisi apel berada dalam batas papan
        board.locateApple();

        int appleX = getPrivateField(board, "apple_x");
        int appleY = getPrivateField(board, "apple_y");

        assertTrue(appleX >= 0 && appleX < 300);
        assertTrue(appleY >= 0 && appleY < 300);
    }

    @Test
    public void testCheckAppleIncreasesDots() throws Exception {
        // Komentar: Memastikan dots bertambah saat ular memakan apel

        // Set koordinat kepala ular dan apel sama
        setPrivateField(board, "x", new int[]{50});
        setPrivateField(board, "y", new int[]{50});
        setPrivateField(board, "apple_x", 50);
        setPrivateField(board, "apple_y", 50);
        setPrivateField(board, "dots", 3);

        board.checkApple();

        int newDots = getPrivateField(board, "dots");
        assertEquals(4, newDots);
    }

    // Utility untuk ambil nilai private
    private int getPrivateField(Board b, String fieldName) {
        try {
            Field field = Board.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.getInt(b);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail("Tidak bisa ambil field: " + fieldName);
            return -1;
        }
    }
    

    // Utility untuk set nilai private (int[] atau int)
    private void setPrivateField(Board b, String fieldName, Object value) {
        try {
            Field field = Board.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(b, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail("Tidak bisa set field: " + fieldName);
        }
    }
}
