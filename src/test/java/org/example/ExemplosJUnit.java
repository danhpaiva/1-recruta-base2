package org.example;

import org.junit.Assert;
import org.junit.Test;

public class ExemplosJUnit {

    @Test
    public void meuPrimeiroTeste(){

    }

    @Test
    public void exemploAssertTrue(){
        String fruta = "pêra";
        Assert.assertTrue(fruta.equals("pêra"));
    }

    @Test
    public void exemploAssertEquals(){
        Assert.assertEquals(4 , 2+2);
    }

    @Test
    public void exemploAssertEquals2(){
        String fruta = "pêra";
        Assert.assertEquals("pêra", fruta);
    }
}
