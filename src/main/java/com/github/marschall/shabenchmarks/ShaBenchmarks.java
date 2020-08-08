package com.github.marschall.shabenchmarks;

import java.security.DigestException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.concurrent.TimeUnit;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
public class ShaBenchmarks {

  static {
    BouncyCastleHelper.ensureInstalled();
  }

  @Param({"SHA-1", "SHA-256"})
  public String algorithm;

  private MessageDigest jdkMessageDigest;
  private byte[] jdkDigest;

  private MessageDigest bcMessageDigest;
  private byte[] bcDigest;

  @Setup
  public void setup() throws GeneralSecurityException {
    this.jdkMessageDigest = MessageDigest.getInstance("SHA-1");
    this.jdkDigest = new byte[this.jdkMessageDigest.getDigestLength()];

    this.bcMessageDigest = MessageDigest.getInstance("SHA-1", BouncyCastleProvider.PROVIDER_NAME);
    this.bcDigest = new byte[this.bcMessageDigest.getDigestLength()];
  }

  @Benchmark
  public byte[] jdkSha1() throws DigestException {
    this.jdkMessageDigest.update((byte) 1);
    this.jdkMessageDigest.digest(this.jdkDigest, 0, this.jdkDigest.length);
    return this.jdkDigest;
  }

  @Benchmark
  public byte[] bcSha1() throws DigestException {
    this.bcMessageDigest.update((byte) 1);
    this.bcMessageDigest.digest(this.bcDigest, 0, this.bcDigest.length);
    return this.bcDigest;
  }

}
