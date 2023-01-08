package api;

import org.junit.Test;

import static org.junit.Assert.*;

public class RounderTest {

    @Test
    public void round() {
        assertEquals("3.33",Rounder.round(3.33F));
    }
}