maven-resolver-plugin
=====================

Push any application into maven in a repeatable way using it folder structure as the group id.


Configuration defaults:
------------------------
rootDir - [mandatory]
includedRegExp - ".+.(j|e|w)ar$"
targetDir - [mandatory]
versionRegExp - ""
groupPrefix - ""
groupMaskRegExp - ""
excludeRegExp - ""
validTypes - "^[j|e|w]ar$"
repositoryId - mandatory
repositoryUrl - mandatory


Usage:

For JBoss EAP for example, place your files in the src/main/resources/eap/5.1/ folder and the following config will deploy into the maven repository specified

Example values for ${maven.repository.url} can be "file://${env.HOME}/.m2/repository" for local deployment, or "http://server:port/nexus/public/release/jboss-enterprise" for a nexus repository called jboss-enterprise. 


------
<!-- unfortunately the plugin requires twdata libs which are not available in repo1, so add this repo -->
  <repositories>
    <repository>
      <id>twdata</id>
      <url>http://twdata-m2-repository.googlecode.com/svn</url>
    </repository>
  </repositories>

...

      <plugin>
        <groupId>org.jboss.maven.plugins</groupId>
        <artifactId>maven-resolver-plugin</artifactId>
        <version>3.0.2-SNAPSHOT</version>
        <executions>
          <execution>
            <id>deploy-eap-maven</id>
            <phase>initialize</phase>
            <goals>
              <goal>deploy</goal>
            </goals>
            <configuration>
              <rootDir>src/main/resources/eap</rootDir>
              <includedRegExp>.+.(j|e|w)ar$</includedRegExp>
              <targetDir>/tmp/eap</targetDir>
              <versionRegExp>([^/]*)/.*</versionRegExp>
              <groupPrefix>jboss-enterprise.eap</groupPrefix>
              <groupMaskRegExp>[^/]*/(.*)</groupMaskRegExp>
              <excludeRegExp>/samples|/examples|/docs|/tmp|/temp|/work|/src</excludeRegExp>
              <repositoryId>${maven.repository.id}</repositoryId>
              <repositoryUrl>${maven.repository.url}</repositoryUrl>
            </configuration>
          </execution>
          <execution>
            <id>deploy-resteasy-maven</id>
            <phase>initialize</phase>
            <goals>
              <goal>deploy</goal>
            </goals>
            <configuration>
              <rootDir>src/main/resources/resteasy</rootDir>
              <includedRegExp>.+.(j|e|w)ar$</includedRegExp>
              <targetDir>/tmp/resteasy</targetDir>
              <versionRegExp>([^/]*)/.*</versionRegExp>
              <groupPrefix>jboss-enterprise</groupPrefix>
              <groupMaskRegExp>[^/]*/(.*)</groupMaskRegExp>
              <excludeRegExp>/samples|/examples|/docs|/tmp|/temp|/work|/src</excludeRegExp>
              <repositoryId>${maven.repository.id}</repositoryId>
              <repositoryUrl>${maven.repository.url}</repositoryUrl>
            </configuration>
          </execution>
          <execution>
            <id>deploy-jon-maven</id>
            <phase>initialize</phase>
            <goals>
              <goal>deploy</goal>
            </goals>
            <configuration>
              <rootDir>src/main/resources/jon</rootDir>
              <!--validTypes>^[j|e|w]ar$</validTypes-->
              <includedRegExp>.*/lib/rhq-.+.([j|e|w]ar)$</includedRegExp>
              <targetDir>/tmp/jon</targetDir>
              <versionRegExp>([^/]*)/.*</versionRegExp>
              <groupPrefix>jboss-enterprise</groupPrefix>
              <groupMaskRegExp>[^/]*/(.*)</groupMaskRegExp>
              <excludeRegExp>/samples|/examples|/docs|/tmp|/temp|/work|/src</excludeRegExp>
              <repositoryId>${maven.repository.id}</repositoryId>
              <repositoryUrl>${maven.repository.url}</repositoryUrl>
            </configuration>
          </execution>
        </executions>
        <dependencies>
          <dependency>
            <groupId>regexp</groupId>
            <artifactId>regexp</artifactId>
            <version>1.2</version>
          </dependency>
          <dependency>
             <groupId>org.twdata.maven</groupId>
              <artifactId>mojo-executor</artifactId>
              <version>0.1</version>
          </dependency>
          <dependency>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-deploy-plugin</artifactId>
            <version>2.4</version>
          </dependency>
        </dependencies>
      </plugin>