package fr.lernejo.navy_battle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class OptionTest {

    @Test
    public void compliteTest()
    {
        Option<Integer> a = new Option<>();
        Assertions.assertEquals(a.isEmpty(),!a.isNotEmpty());
        a.set(5);
        Assertions.assertEquals(5,a.get());
        Option<Integer> b = new Option<>(6);
        Assertions.assertEquals(6,b.get());
    }


}
