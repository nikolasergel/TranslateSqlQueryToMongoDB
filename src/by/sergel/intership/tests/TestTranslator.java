package by.sergel.intership.tests;

import by.sergel.intership.Translator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestTranslator {

    private Translator translator;

    @Before
    public void before() { translator = new Translator(); }


    @Test
    public void testSelect1() throws Exception{
        String translate = translator.translate("select * from test");
        Assert.assertEquals("db.test.find({})", translate);
    }

    @Test
    public void testSelect2() throws Exception{
        String translate = translator.translate("select name, a, b from test");
        Assert.assertEquals("db.test.find({}, {name: 1, a: 1, b: 1})", translate);
    }

    @Test
    public void testSelect3() throws Exception{
        String translate = translator.translate("SELECT * FROM user");
        Assert.assertEquals("db.user.find({})", translate);
    }

    @Test
    public void testSelect4() throws Exception{
        String translate = translator.translate("select name, a, b from test where name = \"Bill\"");
        Assert.assertEquals("db.test.find({name: \"Bill\"}, {name: 1, a: 1, b: 1})", translate);
    }

    @Test
    public void testSelect5() throws Exception{
        String translate = translator.translate("select name, a, b from test where a >= 1 and b <= 100 and name = \"Bill\"");
        Assert.assertEquals("Enter the correct SQL request!", translate);
    }

    @Test
    public void testSelect6() throws Exception{
        String translate = translator.translate("select name, a, b from test where a > 1 and name = \"Bill\"");
        Assert.assertEquals("db.test.find({$and: [{a: { $gt : 1 }}, {name: \"Bill\"}]}, {name: 1, a: 1, b: 1})", translate);
    }

    @Test
    public void testSelect7() throws Exception{
        String translate = translator.translate("SELECT name, a, b FROM test WHERE a > 1 AND b < 100 AND name = \"Bill\"");
        Assert.assertEquals("db.test.find({$and: [{a: { $gt : 1 }}, {b: { $lt: 100 }}, {name: \"Bill\"}]}, {name: 1, a: 1, b: 1})", translate);
    }

    @Test
    public void testSelect8() throws Exception{
        String translate = translator.translate("select * from test OFFSET 1");
        Assert.assertEquals("db.test.find({}).skip(1)", translate);
    }

    @Test
    public void testSelect9() throws Exception{
        String translate = translator.translate("select * from test OFFSET 1 LIMIT 2");
        Assert.assertEquals("db.test.find({}).skip(1).limit(2)", translate);
    }

    @Test
    public void testSelect10() throws Exception{
        String translate = translator.translate("select * from test OFFSET 1 LIMIT 0");
        Assert.assertEquals("db.test.find({}).skip(1).limit(0)", translate);
    }

    @Test
    public void testSelect11() throws Exception{
        String translate = translator.translate("select * from test LIMIT 0");
        Assert.assertEquals("db.test.find({}).limit(0)", translate);
    }

    @Test
    public void testSelect12() throws Exception{
        String translate = translator.translate("select");
        Assert.assertEquals("Enter the correct SQL request!", translate);
    }

    @Test
    public void testSelect13() throws Exception{
        String translate = translator.translate("sel");
        Assert.assertEquals("Enter the correct SQL request!", translate);
    }

    @Test
    public void testSelect14() throws Exception{
        String translate = translator.translate(null);
        Assert.assertEquals("Enter the correct SQL request!", translate);
    }

}
