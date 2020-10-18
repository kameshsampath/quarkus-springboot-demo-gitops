package dev.kameshs.demo;

import java.io.FileWriter;
import java.io.IOException;
import org.apache.commons.codec.digest.Md5Crypt;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command()
public class Installer implements Runnable {

  @Option(names = {"-f", "--file-name"},
      description = "The htpasswd file to create",
      defaultValue = "openshift/rbac/users.htpass")
  String htpassFile;

  @Option(names = {"-u", "--username"},
      description = "OpenShift Cluster Administrator",
      defaultValue = "ocpadmin")
  String openshiftAdmin;

  @Option(names = {"-p", "--password"},
      description = "OpenShift Cluster Administrator password",
      defaultValue = "adminPa55word!")
  String openshiftAdminPassword;

  @Override
  public void run() {
    var md5CryptDigest = Md5Crypt.apr1Crypt(openshiftAdminPassword.getBytes());
    try (FileWriter fw = new FileWriter(htpassFile)) {
      fw.write(String.format("%s:%s", openshiftAdmin, md5CryptDigest));
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}
