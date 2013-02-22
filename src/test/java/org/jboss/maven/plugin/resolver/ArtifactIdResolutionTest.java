package org.jboss.maven.plugin.resolver;

import java.io.File;

import org.jboss.maven.plugin.resolver.ResolverMojo.ModuleReference;
import org.junit.Assert;
import org.junit.Test;

public class ArtifactIdResolutionTest {
  
  public String test(String path) throws Exception{
    ModuleReference test=new ResolverMojo() {}.new ModuleReference();
    return test.getArtifactIdPath(path, false);
  }
  
  @Test
  public void test1() throws Exception{
    Assert.assertEquals("httpclient", test("5.1.2.GA/httpclient-4.0.jar"));
  }
  @Test
  public void test2() throws Exception{
    Assert.assertEquals("httpclient", test("5.1.2.GA/httpclient-49.21.10.jar"));
  }
  @Test
  public void test3() throws Exception{
    Assert.assertEquals("httpclient", test("5.1.2.GA/httpclient.jar"));
  }
  @Test
  public void test4() throws Exception{
    Assert.assertEquals("jsr250-api", test("5.1.2.GA/jsr250-api.jar"));
  }
}
