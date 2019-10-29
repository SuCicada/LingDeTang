package org.subbs.util;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class JavaWebTokenManagerTest {
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(JavaWebTokenManager.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @org.junit.Test
    public void createJavaWebToken() {

    }

    @org.junit.Test
    public void verifyJavaWebToken() {
    }

    public static void main(String[] args) throws Exception {
        Map map = new HashMap();
        map.put("name","qwe");
        map.put("id","324e");
        String a = JavaWebTokenManager.createJavaWebToken(map);
        System.out.println(a);
        Thread.sleep(2000);
        System.out.println(JavaWebTokenManager.verifyJavaWebToken(a));
    }
}
