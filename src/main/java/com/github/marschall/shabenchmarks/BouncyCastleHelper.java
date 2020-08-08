package com.github.marschall.shabenchmarks;

import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

final class BouncyCastleHelper {

  static synchronized void ensureInstalled() {
    if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
      Security.addProvider(new BouncyCastleProvider());
    }
  }

}
